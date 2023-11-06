package com.spyker.commons.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spyker.commons.entity.SysConfig;
import com.spyker.commons.search.SysConfigSearch;
import com.spyker.framework.response.RestResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author CodeGenerator
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

    IPage<SysConfig> queryPage(IPage<SysConfig> page,@Param("search") SysConfigSearch search);

    SysConfig get(String id);

    RestResponse<?> insert(SysConfig SysConfig);

    RestResponse<?> update(SysConfig SysConfig);

    RestResponse<?> delete(String id);

}