package com.spyker.flowable.biz.vo.third;

import com.spyker.flowable.biz.vo.UserFieldDataVo;
import com.spyker.flowable.common.dto.third.UserDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class UserDtoExtension extends UserDto {

    private String deptName;

    private Set<String> roles;
    private Set<String> perms;
    // 扩展字段
    private List<UserFieldDataVo> userFieldDataList;

    private Map<String, Object> fieldData;

    private String verifyCode;

    private String verifyCodeKey;

    private List<Long> roleIds;
}