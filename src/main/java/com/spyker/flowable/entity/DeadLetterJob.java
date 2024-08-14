package com.spyker.flowable.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DeadLetterJob {

    String id;

    Date dueDate;

    String exceptionMessage;

    String jobHandlerType;

    String jobType;

    String processDefId;

    String processInstanceId;

    String executionId;
}