package com.spyker.flowable.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.spyker.flowable.entity.TableDataInfo;
import com.spyker.framework.util.text.ExStringUtils;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.editor.constants.ModelDataJsonConstants;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

/** 流程管理 */
@Tag(name = "部署管理接口", description = "动态流程接口")
@Controller
@RequestMapping("/flow/manage")
public class FlowController {

    private final String prefix = "flowable/manage";
    @Autowired RepositoryService repositoryService;
    @Autowired ProcessEngineConfiguration configuration;
    @Autowired private RuntimeService runtimeService;
    @Autowired private TaskService taskService;

    @GetMapping("")
    public String processList() {
        return prefix + "/processList";
    }

    @GetMapping("deploy")
    public String deploy() {
        return prefix + "/deployProcess";
    }

    @Operation(summary = "上传一个工作流文件", description = "上传一个工作流文件")
    @RequestMapping(value = "/uploadworkflow", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse fileupload(@RequestParam MultipartFile uploadfile) {
        try {
            String filename = uploadfile.getOriginalFilename();
            InputStream is = uploadfile.getInputStream();
            if (filename.endsWith("zip")) {
                repositoryService
                        .createDeployment()
                        .name(filename)
                        .addZipInputStream(new ZipInputStream(is))
                        .deploy();
            } else if (filename.endsWith("bpmn") || filename.endsWith("xml")) {
                repositoryService
                        .createDeployment()
                        .name(filename)
                        .addInputStream(filename, is)
                        .deploy();
            } else {
                return RestResponse.error(-1, "文件格式错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.error(-1, "部署失败");
        }
        return RestResponse.success("部署成功");
    }

    @Operation(summary = "查询已部署工作流列表", description = "查询已部署工作流列表")
    @RequestMapping(value = "/getprocesslists", method = RequestMethod.POST)
    @ResponseBody
    public TableDataInfo getlist(
            @RequestParam(required = false) String key,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean latest,
            Integer pageSize,
            Integer pageNum) {
        ProcessDefinitionQuery queryCondition = repositoryService.createProcessDefinitionQuery();
        if (ExStringUtils.isNotEmpty(key)) {
            queryCondition.processDefinitionKey(key);
        }
        if (ExStringUtils.isNotEmpty(name)) {
            queryCondition.processDefinitionName(name);
        }
        if (latest) {
            queryCondition.latestVersion();
        }
        int total = queryCondition.list().size();
        int start = (pageNum - 1) * pageSize;
        List<ProcessDefinition> pageList =
                queryCondition.orderByDeploymentId().desc().listPage(start, pageSize);
        List<com.spyker.flowable.entity.Process> mylist = new ArrayList<>();
        for (int i = 0; i < pageList.size(); i++) {
            com.spyker.flowable.entity.Process p = new com.spyker.flowable.entity.Process();
            p.setDeploymentId(pageList.get(i).getDeploymentId());
            p.setId(pageList.get(i).getId());
            p.setKey(pageList.get(i).getKey());
            p.setName(pageList.get(i).getName());
            p.setResourceName(pageList.get(i).getResourceName());
            p.setDiagramresourceName(pageList.get(i).getDiagramResourceName());
            p.setSuspended(pageList.get(i).isSuspended());
            p.setVersion(pageList.get(i).getVersion());
            mylist.add(p);
        }
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(mylist);
        rspData.setTotal(total);
        return rspData;
    }

    @Operation(summary = "删除一次部署的工作流", description = "删除一次部署的工作流")
    @RequestMapping(value = "/remove/{deploymentId}", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse remove(@PathVariable String deploymentId) {
        repositoryService.deleteDeployment(deploymentId, true);
        return RestResponse.success();
    }

    @Operation(summary = "查看工作流图片", description = "查看工作流图片")
    @RequestMapping(value = "/showresource", method = RequestMethod.GET)
    public void showresource(@RequestParam("pdid") String pdid, HttpServletResponse response)
            throws Exception {
        response.setContentType("image/jpeg;charset=UTF-8");
        response.setHeader("Content-Disposition", "inline;filename=process.jpg");
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pdid);
        ProcessDiagramGenerator diagramGenerator = configuration.getProcessDiagramGenerator();
        InputStream is =
                diagramGenerator.generateDiagram(
                        bpmnModel, "png", "宋体", "宋体", "宋体", configuration.getClassLoader(), true);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is, output);
    }

    @Operation(summary = "查看工作流定义", description = "查看工作流定义")
    @RequestMapping(value = "/showProcessDefinition/{pdid}/{resource}", method = RequestMethod.GET)
    public void showProcessDefinition(
            @PathVariable("pdid") String pdid,
            @PathVariable(value = "resource") String resource,
            HttpServletResponse response)
            throws Exception {
        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "inline;filename=process.bpmn20.xml");
        InputStream is = repositoryService.getResourceAsStream(pdid, resource);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is, output);
    }

    @Operation(summary = "将流程定义转为模型", description = "将流程定义转为模型")
    @RequestMapping(value = "/exchangeProcessToModel/{pdid}", method = RequestMethod.GET)
    @ResponseBody
    public String exchangeProcessToModel(
            @PathVariable("pdid") String pdid, HttpServletResponse response) throws Exception {
        ProcessDefinition definition =
                repositoryService
                        .createProcessDefinitionQuery()
                        .processDefinitionId(pdid)
                        .singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(definition.getId());
        ObjectNode objectNode = new BpmnJsonConverter().convertToJson(bpmnModel);
        Model modelData = repositoryService.newModel();
        modelData.setKey(definition.getKey());
        modelData.setName(definition.getName());
        modelData.setCategory(definition.getCategory());
        ObjectNode modelJson = new ObjectMapper().createObjectNode();
        modelJson.put(ModelDataJsonConstants.MODEL_NAME, definition.getName());
        modelJson.put(ModelDataJsonConstants.MODEL_DESCRIPTION, definition.getDescription());
        List<Model> models =
                repositoryService.createModelQuery().modelKey(definition.getKey()).list();
        if (models.size() > 0) {
            Integer version = models.get(0).getVersion();
            version++;
            modelJson.put(ModelDataJsonConstants.MODEL_REVISION, version);
            // 删除旧模型
            repositoryService.deleteModel(models.get(0).getId());
            modelData.setVersion(version);
        } else {
            modelJson.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        }
        modelData.setMetaInfo(modelJson.toString());
        modelData.setDeploymentId(definition.getDeploymentId());
        // 保存新模型
        repositoryService.saveModel(modelData);
        // 保存模型json
        repositoryService.addModelEditorSource(
                modelData.getId(), objectNode.toString().getBytes(StandardCharsets.UTF_8));
        return objectNode.toString();
    }

    @Operation(summary = "挂起一个流程定义", description = "挂起一个流程定义")
    @RequestMapping(value = "/suspendProcessDefinition", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse suspendProcessDefinition(
            @RequestParam("pdid") String pdid,
            @RequestParam("flag") Boolean flag,
            @RequestParam(value = "date", required = false) String date)
            throws Exception {
        if (ExStringUtils.isNotEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            repositoryService.suspendProcessDefinitionById(pdid, flag, sdf.parse(date));
        } else {
            repositoryService.suspendProcessDefinitionById(pdid, flag, null);
        }
        return RestResponse.success();
    }

    @Operation(summary = "激活一个流程定义", description = "激活一个流程定义")
    @RequestMapping(value = "/activateProcessDefinition", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse activateProcessDefinition(
            @RequestParam("pdid") String pdid,
            @RequestParam("flag") Boolean flag,
            @RequestParam(value = "date", required = false) String date)
            throws Exception {
        if (ExStringUtils.isNotEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            repositoryService.activateProcessDefinitionById(pdid, flag, sdf.parse(date));
        } else {
            repositoryService.activateProcessDefinitionById(pdid, flag, null);
        }
        return RestResponse.success();
    }
}