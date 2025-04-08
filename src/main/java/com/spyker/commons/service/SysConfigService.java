package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysConfig;
import com.spyker.commons.search.SysConfigSearch;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/** 参数配置表 服务类 */
public interface SysConfigService extends IService<SysConfig> {

    boolean delete(String id);

    SysConfig get(String id);

    boolean insert(SysConfig SysConfig);

    List<SysConfig> query(SysConfigSearch search);

    IPage<SysConfig> queryPage(IPage<SysConfig> page, @Param("search") SysConfigSearch search);

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    boolean selectCaptchaEnabled();

    boolean update(SysConfig SysConfig);
}
