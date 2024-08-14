package com.spyker.flowable.entity;

import lombok.Data;

@Data
public class TaskInfo {

    String taskId;

    String processInstanceId;

    String executionId;

    String businessKey;

    String processName;

    String taskName;

    String starter;

    String assignee;

    String startTime;

    String endTime;

    String createTime;

    String formKey;

    String comment;

    Integer pageSize;

    Integer pageNum;
}