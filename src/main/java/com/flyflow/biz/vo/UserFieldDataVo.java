package com.flyflow.biz.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户字段-数据
 *
 * @author xiaoge
 * @since 2023-05-17
 */
@Getter
@Setter
public class UserFieldDataVo implements Serializable {

    /** 数据 */
    private String data;

    private String name;

    /** 字段 */
    private String key;

    /** 字段类型 */
    private String type;

    /** 是否必填 */
    private Boolean required;

    /** 配置json字符串 */
    private String props;
}