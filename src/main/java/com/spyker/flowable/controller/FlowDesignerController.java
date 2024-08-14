package com.spyker.flowable.controller;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.editor.constants.ModelDataJsonConstants;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Model;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/app/rest/")
public class FlowDesignerController {

    @Autowired protected ObjectMapper objectMapper;
    @Autowired RepositoryService repositoryService;
    @Autowired IdentityService identityService;
    @Autowired RuntimeService runtimeService;
    @Autowired TaskService taskService;

    /**
     * 获得
     *
     * @param modelId
     * @return
     */
    @RequestMapping(
            value = "/models/{modelId}/editor/json",
            method = RequestMethod.GET,
            produces = "application/json")
    public ObjectNode getModelJSON(@PathVariable String modelId) {
        Model model = repositoryService.getModel(modelId);
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put("modelId", model.getId());
        modelNode.put("name", model.getName());
        modelNode.put("key", model.getKey());
        modelNode.put(
                "description",
                JSONObject.parseObject(model.getMetaInfo()).getString("description"));
        modelNode.putPOJO("lastUpdated", model.getLastUpdateTime());
        byte[] modelEditorSource = repositoryService.getModelEditorSource(modelId);
        if (null != modelEditorSource && modelEditorSource.length > 0) {
            try {
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(modelEditorSource);
                editorJsonNode.put("modelType", "model");
                modelNode.put("model", editorJsonNode);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            ObjectNode editorJsonNode = objectMapper.createObjectNode();
            editorJsonNode.put("id", "canvas");
            editorJsonNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorJsonNode.put("modelType", "model");
            modelNode.put("model", editorJsonNode);
        }
        return modelNode;
    }

    /**
     * 保存
     *
     * @param modelId
     * @param values
     */
    @RequestMapping(value = "models/{modelId}/editor/json", method = RequestMethod.POST)
    public void saveModel(
            @PathVariable String modelId, @RequestBody MultiValueMap<String, String> values) {

        String json = values.getFirst("json_xml");
        String name = values.getFirst("name");
        String description = values.getFirst("description");
        String key = values.getFirst("key");

        Model modelData = repositoryService.getModel(modelId);
        if (null == modelData) {
            modelData = repositoryService.newModel();
        }

        ObjectNode modelNode = null;
        try {
            modelNode = (ObjectNode) new ObjectMapper().readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        description = StringUtils.defaultString(description);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelData.setMetaInfo(modelObjectNode.toString());
        modelData.setName(name);
        modelData.setKey(StringUtils.defaultString(key));
        // 显示发布按钮
        modelData.setDeploymentId(null);
        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(
                modelData.getId(), modelNode.toString().getBytes(StandardCharsets.UTF_8));
    }

    /** 校验流程图 */
    @PostMapping(value = "/model/validate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ValidationError> validate(@RequestBody JsonNode body) {
        if (body != null && body.has("stencilset")) {
            BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(body);
            ProcessValidator validator =
                    new ProcessValidatorFactory().createDefaultProcessValidator();
            List<ValidationError> errors = validator.validate(bpmnModel);
            return errors;
        }
        return Collections.emptyList();
    }
}