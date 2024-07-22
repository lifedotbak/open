package com.flyflow.biz.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.lock.annotation.Lock4j;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flyflow.biz.entity.*;
import com.flyflow.biz.entity.Process;
import com.flyflow.biz.mapper.ProcessMapper;
import com.flyflow.biz.service.*;
import com.flyflow.biz.utils.CoreHttpUtil;
import com.flyflow.biz.vo.ProcessVO;
import com.flyflow.common.dto.R;
import com.flyflow.common.dto.flow.FormItemVO;
import com.flyflow.common.dto.flow.Node;
import com.flyflow.common.dto.flow.NodeUser;
import com.flyflow.common.dto.flow.node.RootNode;
import com.flyflow.common.utils.JsonUtil;
import com.flyflow.common.utils.NodeUtil;
import com.flyflow.common.utils.TenantUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 服务实现类
 *
 * @author xiaoge
 * @since 2023-05-25
 */
@Slf4j
@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process>
        implements IProcessService, IClearService {
    @Resource public IProcessMainService processMainService;
    @Resource private IProcessStarterService processStarterService;
    @Resource @Lazy private IProcessFormService processFormService;

    @PostConstruct
    public void init() {}

    /**
     * 根据流程唯一标识查询流程列表
     *
     * @param uniqueId
     * @return
     */
    @Override
    public R<ProcessVO> getListByUniqueId(String uniqueId) {
        List<Process> processList =
                this.lambdaQuery()
                        .eq(Process::getUniqueId, uniqueId)
                        .eq(Process::getTenantId, TenantUtil.get())
                        .eq(Process::getHidden, false)
                        .orderByDesc(Process::getCreateTime)
                        .list();

        List<ProcessVO> processVOS = BeanUtil.copyToList(processList, ProcessVO.class);

        return R.success(processVOS);
    }

    /**
     * 设置主流程
     *
     * @param flowId
     * @return
     */
    @Override
    public R setMainProcess(String flowId) {
        Process byFlowId = this.getByFlowId(flowId);
        // 先停用了
        this.lambdaUpdate()
                .set(Process::getStop, true)
                .eq(Process::getUniqueId, byFlowId.getUniqueId())
                .eq(Process::getTenantId, TenantUtil.get())
                .update(new Process());
        // 设置为主流程
        byFlowId.setStop(false);
        this.updateById(byFlowId);

        // 修改主流程
        ProcessMain processMain = BeanUtil.copyProperties(byFlowId, ProcessMain.class, "id");
        processMainService
                .lambdaUpdate()
                .eq(ProcessMain::getUniqueId, byFlowId.getUniqueId())
                .eq(ProcessMain::getTenantId, TenantUtil.get())
                .update(processMain);

        return R.success();
    }

    /**
     * 获取详细数据
     *
     * @param flowId
     * @return
     */
    @Override
    public R<ProcessVO> getDetail(String flowId) {
        ProcessVO processVO = this.getProcessVO(flowId);
        if (processVO == null) {
            return R.fail("流程不存在");
        }
        return R.success(processVO);
    }

    private ProcessVO getProcessVO(String flowId) {
        Process oaForms = getByFlowId(flowId);
        if (oaForms == null) {
            return null;
        }
        String formItems = oaForms.getFormItems();

        List<FormItemVO> formItemVOList = JsonUtil.parseArray(formItems, FormItemVO.class);

        oaForms.setFormItems(JsonUtil.toJSONString(formItemVOList));

        ProcessVO processVO = BeanUtil.copyProperties(oaForms, ProcessVO.class);

        // 发起人范围
        List<ProcessStarter> processStarterList =
                processStarterService
                        .lambdaQuery()
                        .eq(ProcessStarter::getProcessId, oaForms.getId())
                        .eq(ProcessStarter::getTenantId, TenantUtil.get())
                        .list();
        List<NodeUser> rangeList = new ArrayList<>();
        for (ProcessStarter processStarter : processStarterList) {
            NodeUser nodeUser = JsonUtil.parseObject(processStarter.getData(), NodeUser.class);
            rangeList.add(nodeUser);
        }
        processVO.setRangeList(rangeList);

        return processVO;
    }

    @Override
    public Process getByFlowId(String flowId) {
        return this.lambdaQuery().eq(Process::getFlowId, flowId).one();
    }

    /**
     * 查询流程数据 包括已经被删除的
     *
     * @param flowId
     * @return
     */
    @Override
    public Process getByFlowIdContainDeleted(String flowId) {
        return this.baseMapper.selectByFlowId(flowId);
    }

    @Override
    public Process getByUniqueId(String uniqueId) {
        return this.lambdaQuery()
                .eq(Process::getUniqueId, uniqueId)
                .eq(Process::getHidden, false)
                .eq(Process::getStop, false)
                .one();
    }

    @Override
    public void updateByFlowId(Process process) {
        this.lambdaUpdate().eq(Process::getFlowId, process.getFlowId()).update(process);
    }

    @Override
    public void stop(String flowId) {
        this.lambdaUpdate()
                .set(Process::getStop, true)
                .eq(Process::getFlowId, flowId)
                .update(new Process());
    }

    /**
     * 创建流程
     *
     * @param processVO
     * @return
     */
    @Transactional
    @Lock4j(
            keys = {"#processVO.uniqueId"},
            expire = 60000,
            acquireTimeout = 60000)
    @Override
    public R create(ProcessVO processVO) {

        String paramUniqueId = processVO.getUniqueId();

        {
            // 名字唯一
            String name = processVO.getName();
            List<Process> processList =
                    this.lambdaQuery()
                            .eq(Process::getName, name)
                            .eq(Process::getTenantId, TenantUtil.get())
                            .eq(Process::getHidden, false)
                            .list();
            if (StrUtil.isNotBlank(processVO.getFlowId())) {
                Process process = this.getByFlowId(processVO.getFlowId());
                if (process == null || process.getHidden()) {
                    return R.fail("流程不存在，请退出流程重新打开编辑");
                }

                String uniqueId = process.getUniqueId();
                if (!StrUtil.equals(uniqueId, paramUniqueId)) {
                    return R.fail("流程id不匹配");
                }
                if (process.getStop()) {
                    // 已停用了 但是其他的未停用 那该流程不能修改
                    Long count =
                            this.lambdaQuery()
                                    .eq(Process::getTenantId, TenantUtil.get())
                                    .eq(Process::getUniqueId, uniqueId)
                                    .eq(Process::getStop, false)
                                    .eq(Process::getHidden, false)
                                    .count();
                    if (count > 0) {
                        return R.fail("流程状态已变更，请退出流程重新打开编辑");
                    }
                }

                long count =
                        processList.stream()
                                .filter(w -> !StrUtil.equals(w.getUniqueId(), paramUniqueId))
                                .count();
                if (count > 0) {
                    return R.fail("流程名字已存在，不能重复");
                }
            } else {
                if (!processList.isEmpty()) {
                    return R.fail("流程名字已存在，不能重复");
                }
                // 判断unqiueId
                boolean exists =
                        this.lambdaQuery().eq(Process::getUniqueId, paramUniqueId).exists();
                if (exists) {
                    return R.fail("流程id已存在");
                }
            }
        }

        String processStr = processVO.getProcess();

        Node node = JsonUtil.parseObject(processStr, Node.class);
        NodeUtil.handleParentId(node, null);
        // 处理发起人节点
        com.flyflow.biz.utils.NodeUtil.handleStarterForm(
                (RootNode) node, JsonUtil.parseArray(processVO.getFormItems(), FormItemVO.class));
        // 处理审批节点表单
        com.flyflow.biz.utils.NodeUtil.handleApproveForm(
                node, JsonUtil.parseArray(processVO.getFormItems(), FormItemVO.class));
        // 处理抄送节点表单
        com.flyflow.biz.utils.NodeUtil.handleCopyForm(
                node, JsonUtil.parseArray(processVO.getFormItems(), FormItemVO.class));
        com.flyflow.biz.utils.NodeUtil.handleApprove(node);
        com.flyflow.biz.utils.NodeUtil.handEmptyNode(node);

        // SuperNode superNode = JsonUtil.parseObject(JsonUtil.toJSONString(node), SuperNode.class);

        R<String> r =
                CoreHttpUtil.createFlow(
                        node, StpUtil.getLoginIdAsString(), processVO.getName(), TenantUtil.get());
        if (!r.isOk()) {
            return R.fail(r.getMsg());
        }
        String flowId = r.getData();

        NodeUser nodeUser = JsonUtil.parseArray(processVO.getAdmin(), NodeUser.class).get(0);

        if (StrUtil.isNotBlank(processVO.getFlowId())) {

            Process oldProcess = this.getByFlowId(processVO.getFlowId());
            if (processVO.getPublish()) {
                // 如果是发布
                this.stop(processVO.getFlowId());
            }
            // 修改所有的管理员
            this.lambdaUpdate()
                    .set(Process::getAdminId, nodeUser.getId())
                    .eq(Process::getUniqueId, oldProcess.getUniqueId())
                    .eq(Process::getTenantId, TenantUtil.get())
                    .update(new Process());
        }

        // 发起人范围
        List<NodeUser> nodeUserList = processVO.getRangeList();

        StringBuilder stringBuilder = new StringBuilder();
        if (CollUtil.isNotEmpty(nodeUserList)) {
            int index = 0;

            for (NodeUser user : nodeUserList) {
                if (index > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(user.getName());
                index++;
                if (index > 5) {
                    break;
                }
            }
        }

        int version = 1;

        if (StrUtil.isNotBlank(paramUniqueId)) {
            List<Process> processList =
                    this.lambdaQuery()
                            .eq(Process::getUniqueId, paramUniqueId)
                            .eq(Process::getTenantId, TenantUtil.get())
                            .orderByDesc(Process::getVersion)
                            .list();
            if (CollUtil.isNotEmpty(processList)) {
                version = processList.get(0).getVersion() + 1;
            }
        }

        Process p = new Process();
        p.setTenantId(TenantUtil.get());
        p.setFlowId(flowId);
        p.setName(processVO.getName());
        p.setLogo(processVO.getLogo());

        p.setGroupId(processVO.getGroupId());
        p.setFormItems(processVO.getFormItems());
        p.setFormItemsPc(processVO.getFormItemsPc());
        p.setProcess(JsonUtil.toJSONString(node));
        p.setRemark(processVO.getRemark());
        p.setSort(0);
        p.setHidden(false);
        p.setStop(true);
        if (processVO.getPublish()) {
            p.setStop(false);
        }
        p.setAdminId(nodeUser.getId());
        p.setUniqueId(paramUniqueId);
        p.setAdmin(processVO.getAdmin());
        p.setRangeShow(stringBuilder.toString());
        p.setVersion(version);

        this.save(p);

        // 保存到主表
        ProcessMain processMain = BeanUtil.copyProperties(p, ProcessMain.class, "id");
        boolean mainExist =
                processMainService
                        .lambdaQuery()
                        .eq(ProcessMain::getUniqueId, paramUniqueId)
                        .exists();
        if (!mainExist) {
            processMainService.save(processMain);
        } else if (processVO.getPublish()) {
            processMainService
                    .lambdaUpdate()
                    .eq(ProcessMain::getUniqueId, paramUniqueId)
                    .eq(ProcessMain::getTenantId, TenantUtil.get())
                    .update(processMain);
        }

        // 保存范围

        if (CollUtil.isNotEmpty(nodeUserList)) {
            for (NodeUser nodeUserDto : nodeUserList) {
                ProcessStarter processStarter = new ProcessStarter();
                processStarter.setTenantId(TenantUtil.get());
                processStarter.setContainChildrenDept(nodeUserDto.getContainChildrenDept());
                processStarter.setProcessId(p.getId());
                processStarter.setFlowId(p.getFlowId());
                processStarter.setTypeId((nodeUserDto.getId()));
                processStarter.setType(nodeUserDto.getType());
                processStarter.setData(JsonUtil.toJSONString(nodeUserDto));
                processStarterService.save(processStarter);
            }
        }

        // 保存表单
        List<FormItemVO> formItemVOS =
                JsonUtil.parseArray(processVO.getFormItems(), FormItemVO.class);
        for (FormItemVO formItemVO : formItemVOS) {

            ProcessForm processForm = new ProcessForm();
            processForm.setUniqueId(p.getUniqueId());
            processForm.setFlowId(p.getFlowId());
            processForm.setFormName(formItemVO.getName());
            processForm.setFormId(formItemVO.getId());
            processForm.setFormType(formItemVO.getType());
            processForm.setProps(JsonUtil.toJSONString(formItemVO.getProps()));
            processForm.setTenantId(p.getTenantId());
            processFormService.save(processForm);
        }

        return R.success();
    }

    /**
     * 编辑表单
     *
     * @param flowId 摸板ID
     * @param type 类型 stop using delete
     * @param groupId
     * @return 操作结果
     */
    @Override
    public R update(String flowId, String type, Long groupId) {
        Process process = new Process();
        process.setFlowId(flowId);
        process.setStop("stop".equals(type));
        process.setHidden("delete".equals(type));
        process.setGroupId(groupId);

        this.updateByFlowId(process);

        return R.success();
    }

    /**
     * 查询所有关联的流程id
     *
     * @param flowIdList
     * @return
     */
    @Override
    public R<List<String>> getAllRelatedFlowId(List<String> flowIdList) {
        List<Process> list =
                this.lambdaQuery()
                        .eq(Process::getTenantId, TenantUtil.get())
                        .in(Process::getFlowId, flowIdList)
                        .list();
        Set<String> uniqueIdSet =
                list.stream().map(w -> w.getUniqueId()).collect(Collectors.toSet());
        List<Process> processList =
                this.lambdaQuery()
                        .eq(Process::getTenantId, TenantUtil.get())
                        .in(Process::getUniqueId, uniqueIdSet)
                        .list();
        List<String> collect =
                processList.stream().map(w -> w.getFlowId()).collect(Collectors.toList());
        return R.success(collect);
    }

    /**
     * 清理数据
     *
     * @param uniqueId 流程唯一id
     * @param flowIdList process表 流程id集合
     * @param processIdList process表的注解id集合
     * @param tenantId 租户id
     */
    @Override
    public void clearProcess(
            String uniqueId, List<String> flowIdList, List<Long> processIdList, String tenantId) {

        this.lambdaUpdate()
                .eq(Process::getUniqueId, uniqueId)
                .eq(Process::getTenantId, tenantId)
                .remove();
    }
}