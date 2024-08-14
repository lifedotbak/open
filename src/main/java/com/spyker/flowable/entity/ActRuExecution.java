package com.spyker.flowable.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.util.Date;

/**
 * 执行实例对象 act_ru_execution
 *
 * @date 2022-07-13
 */
@Data
public class ActRuExecution {
    private static final long serialVersionUID = 1L;

    /** */
    private String id;

    /** */
    private Long rev;

    /** */
    private String procInstId;

    /** */
    private String businessKey;

    /** */
    private String parentId;

    /** */
    private String procDefId;

    /** */
    private String superExec;

    /** */
    private String rootProcInstId;

    /** */
    private String actId;

    /** */
    private Integer isActive;

    /** */
    private Integer isConcurrent;

    /** */
    private Integer isScope;

    /** */
    private Integer isEventScope;

    /** */
    private Integer isMiRoot;

    /** */
    private Long suspensionState;

    /** */
    private Long cachedEntState;

    /** */
    private String tenantId;

    /** */
    private String name;

    /** */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** */
    private String startUserId;

    /** */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lockTime;

    /** */
    private Integer isCountEnabled;

    /** */
    private Long evtSubscrCount;

    /** */
    private Long taskCount;

    /** */
    private Long jobCount;

    /** */
    private Long timerJobCount;

    /** */
    private Long suspJobCount;

    /** */
    private Long deadletterJobCount;

    /** */
    private Long varCount;

    /** */
    private Long idLinkCount;
}