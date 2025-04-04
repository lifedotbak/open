package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysConfig;
import com.spyker.commons.search.SysConfigSearch;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 参数配置表 服务类
 *
 * @author 121232224@qq.com
 * @since 2023-09-28
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    boolean selectCaptchaEnabled();

    List<SysConfig> query(SysConfigSearch search);

    IPage<SysConfig> queryPage(IPage<SysConfig> page, @Param("search") SysConfigSearch search);

    SysConfig get(String id);

    boolean insert(SysConfig SysConfig);

    boolean update(SysConfig SysConfig);

    boolean delete(String id);
}
