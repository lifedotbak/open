package com.spyker.framework.third.xxxjob;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.spyker.framework.third.xxxjob.model.XxlJobGroup;
import com.spyker.framework.third.xxxjob.model.XxlJobInfo;
import com.spyker.framework.third.xxxjob.properties.XxlServerProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ConditionalOnClass(XxlServerProperties.class)
@AutoConfiguration
@Slf4j
@RequiredArgsConstructor
public class XxlJobUtils {

    //    private static final String XXL_JOB_SERVER = "http://192.168.200.65:9900";
    //    private static final String USER_NAME = "admin";
    //    private static final String PASSWORD = "123456";
    private final XxlServerProperties xxlServerProperties;

    public static void main(String[] args) {
        //        getLoginCookie();

        //        removeJobInfo(1);

        //        getJobGroup("grid-link-executor", "link服务执行器");
        //        getJobInfo(3, "link服务执行器");
        //
        XxlJobInfo xxlJobInfo = new XxlJobInfo();

        xxlJobInfo.setJobGroup(3);
        xxlJobInfo.setAuthor("system");
        xxlJobInfo.setJobDesc("system create");

        xxlJobInfo.setScheduleType("CRON");
        xxlJobInfo.setGlueType("BEAN");
        xxlJobInfo.setExecutorHandler("collectHandler");
        xxlJobInfo.setExecutorRouteStrategy("FIRST");
        xxlJobInfo.setMisfireStrategy("DO_NOTHING");
        xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        xxlJobInfo.setScheduleConf("0 0 0 * * ? *");
        //
        //        addJobInfo(xxlJobInfo);
        //
        //        xxlJobInfo = new XxlJobInfo();
        //
        //        xxlJobInfo.setJobGroup(3);
        //        xxlJobInfo.setAuthor("system");
        //        xxlJobInfo.setJobDesc("system create");
        //
        //        xxlJobInfo.setScheduleType("FIX_RATE");
        //        xxlJobInfo.setGlueType("BEAN");
        //        xxlJobInfo.setExecutorHandler("collect2Handler");
        //        xxlJobInfo.setExecutorRouteStrategy("FIRST");
        //        xxlJobInfo.setMisfireStrategy("DO_NOTHING");
        //        xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        //        xxlJobInfo.setScheduleConf("99");
        //
        //        addJobInfo(xxlJobInfo);
    }

    public List<XxlJobInfo> getJobInfo(Integer jobGroupId, String executorHandler) {
        String url = xxlServerProperties.getUrl() + "/jobinfo/pageList";
        HttpResponse response =
                HttpRequest.post(url)
                        .form("jobGroup", jobGroupId)
                        //            .form("executorHandler", executorHandler)
                        .form("triggerStatus", -1)
                        .cookie(login())
                        .execute();

        String body = response.body();
        JSONArray array = JSONUtil.parse(body).getByPath("data", JSONArray.class);
        List<XxlJobInfo> list =
                array.stream()
                        .map(o -> JSONUtil.toBean((JSONObject) o, XxlJobInfo.class))
                        .collect(Collectors.toList());

        for (XxlJobInfo xxlJobInfo : list) {
            log.info("xxlJobInfo----->{}", xxlJobInfo);
        }

        return list;
    }

    public String login() {

        Map<String, String> loginCookie = new HashMap<>();
        String url = xxlServerProperties.getUrl() + "/domain";

        HttpResponse response =
                HttpRequest.post(url)
                        .form("userName", xxlServerProperties.getUserName())
                        .form("password", xxlServerProperties.getPassword())
                        .execute();
        List<HttpCookie> cookies = response.getCookies();

        Optional<HttpCookie> cookieOpt =
                cookies.stream()
                        .filter(cookie -> cookie.getName().equals("XXL_JOB_LOGIN_IDENTITY"))
                        .findFirst();
        if (!cookieOpt.isPresent()) {
            throw new RuntimeException("get xxl-job cookie error!");
        }

        String value = cookieOpt.get().getValue();
        loginCookie.put("XXL_JOB_LOGIN_IDENTITY", value);

        return "XXL_JOB_LOGIN_IDENTITY=" + value;
    }

    /**
     * 注册一个新任务，最终返回创建的新任务的id
     *
     * @param xxlJobInfo
     * @return
     */
    public Integer add(XxlJobInfo xxlJobInfo) {

        fillInitData(xxlJobInfo);

        String url = xxlServerProperties.getUrl() + "/jobinfo/add";
        Map<String, Object> paramMap = BeanUtil.beanToMap(xxlJobInfo);
        HttpResponse response = HttpRequest.post(url).form(paramMap).cookie(login()).execute();

        JSON json = JSONUtil.parse(response.body());

        log.info("response--->{}", response);

        Object code = json.getByPath("code");
        if (code.equals(200)) {
            return Convert.toInt(json.getByPath("content"));
        } else {
            return -1;
        }
        //        throw new RuntimeException("add jobInfo error!");
    }

    private void fillInitData(XxlJobInfo xxlJobInfo) {

        if (null != xxlJobInfo) {
            xxlJobInfo.setAuthor(xxlServerProperties.getAuthor());
            xxlJobInfo.setJobGroup(xxlServerProperties.getJobGroup());
            xxlJobInfo.setJobDesc(xxlServerProperties.getJobDesc());
            xxlJobInfo.setGlueType(xxlServerProperties.getGlueType());
            xxlJobInfo.setExecutorRouteStrategy(xxlServerProperties.getExecutorRouteStrategy());
            xxlJobInfo.setMisfireStrategy(xxlServerProperties.getMisfireStrategy());
            xxlJobInfo.setExecutorBlockStrategy(xxlServerProperties.getExecutorBlockStrategy());
            xxlJobInfo.setTriggerStatus(1);
        }
    }

    /**
     * 我们在后面要根据配置文件中的appName和title判断当前执行器是否已经被注册到调度中心过，如果已经注册过那么则跳过，而/jobgroup/pageList
     * 接口是一个模糊查询接口，所以在查询列表的结果列表中，还需要再进行一次精确匹配。
     *
     * @param appName
     * @param title
     * @return
     */
    public boolean preciselyCheck(String appName, String title) {
        List<XxlJobGroup> jobGroup = getJobGroup(appName, title);
        Optional<XxlJobGroup> has =
                jobGroup.stream()
                        .filter(
                                xxlJobGroup ->
                                        xxlJobGroup.getAppname().equals(appName)
                                                && xxlJobGroup.getTitle().equals(title))
                        .findAny();
        return has.isPresent();
    }

    /**
     * 根据appName和执行器名称title查询执行器列表：
     *
     * @param appName
     * @param title
     * @return
     */
    public List<XxlJobGroup> getJobGroup(String appName, String title) {
        String url = xxlServerProperties.getUrl() + "/jobgroup/pageList";
        HttpResponse response =
                HttpRequest.post(url)
                        .form("appname", appName)
                        .form("title", title)
                        .cookie(login())
                        .execute();

        String body = response.body();
        JSONArray array = JSONUtil.parse(body).getByPath("data", JSONArray.class);
        List<XxlJobGroup> list =
                array.stream()
                        .map(o -> JSONUtil.toBean((JSONObject) o, XxlJobGroup.class))
                        .collect(Collectors.toList());

        for (XxlJobGroup xxlJobGroup : list) {
            log.info("xxlJobGroup----->{}", xxlJobGroup);
        }

        return list;
    }

    public boolean start(int id) {
        String url = xxlServerProperties.getUrl() + "/jobinfo/start";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        HttpResponse response = HttpRequest.post(url).form(paramMap).cookie(login()).execute();

        JSON json = JSONUtil.parse(response.body());

        log.info("response--->{}", response);

        Object code = json.getByPath("code");
        return code.equals(200);
        //        throw new RuntimeException("add jobInfo error!");
    }

    public boolean remove(int id) {
        String url = xxlServerProperties.getUrl() + "/jobinfo/remove";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        HttpResponse response = HttpRequest.post(url).form(paramMap).cookie(login()).execute();

        JSON json = JSONUtil.parse(response.body());

        log.info("response--->{}", response);

        Object code = json.getByPath("code");
        return code.equals(200);
        //        throw new RuntimeException("add jobInfo error!");
    }

    /**
     * 注册新executor到调度中心：
     *
     * @param appName
     * @param title
     * @return
     */
    public boolean autoRegisterGroup(String appName, String title) {
        String url = xxlServerProperties.getUrl() + "/jobgroup/save";
        HttpResponse response =
                HttpRequest.post(url)
                        .form("appname", appName)
                        .form("title", title)
                        .cookie(login())
                        .execute();
        Object code = JSONUtil.parse(response.body()).getByPath("code");

        return code.equals(200);
    }

    public boolean update(XxlJobInfo xxlJobInfo) {

        fillInitData(xxlJobInfo);

        String url = xxlServerProperties.getUrl() + "/jobinfo/update";
        Map<String, Object> paramMap = BeanUtil.beanToMap(xxlJobInfo);
        HttpResponse response = HttpRequest.post(url).form(paramMap).cookie(login()).execute();

        JSON json = JSONUtil.parse(response.body());

        log.info("response--->{}", response);

        Object code = json.getByPath("code");

        return code.equals(200);
        //        throw new RuntimeException("update jobInfo error!");
    }
}