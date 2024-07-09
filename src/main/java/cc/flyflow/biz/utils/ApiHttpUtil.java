package cc.flyflow.biz.utils;

import cc.flyflow.common.dto.R;
import cc.flyflow.common.dto.third.UserQueryDto;
import cc.flyflow.common.utils.HttpUtil;
import cc.flyflow.common.utils.JsonUtil;
import cc.flyflow.common.utils.TenantUtil;

import cn.hutool.extra.spring.SpringUtil;

import java.util.Map;

public class ApiHttpUtil {

    public static String getBaseUrl() {
        return SpringUtil.getProperty("api.http.baseUrl");
    }

    public static String post(Object object, String url) {

        String baseUrl = getBaseUrl();

        return HttpUtil.post(object, url, baseUrl, null);
    }

    public static String get(String url) {

        String baseUrl = getBaseUrl();

        return HttpUtil.get(url, baseUrl, TenantUtil.get());
    }

    /**
     * 查询当前存在的任务变量 全部都是
     *
     * <p>如果任务完成了 返回错误码
     *
     * @param taskId
     * @param keyList
     * @return
     */
    public static R<Map<String, Object>> queryCurrentTaskVariables(UserQueryDto userQueryDto) {

        String post = post(userQueryDto, "userList");
        return JsonUtil.parseObject(post, new JsonUtil.TypeReference<R<Map<String, Object>>>() {});
    }
}