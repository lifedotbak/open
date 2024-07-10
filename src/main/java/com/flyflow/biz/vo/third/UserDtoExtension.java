package com.flyflow.biz.vo.third;

import com.flyflow.biz.vo.UserFieldDataVo;
import com.flyflow.common.dto.third.UserDto;
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