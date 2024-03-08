package com.spyker.commons.controller;

import com.google.gson.Gson;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysOssConfig;

import jakarta.servlet.http.Cookie;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
public class SysOssConfigControllerTest extends BaseTest {

    private static String LOGIN_URL = "/sys/login/login";

    private static String BASE_URL = "/sys/sys-oss-config";

    /* 分页查询 */
    private static String LIST_PAGE_URL = BASE_URL + "/list_page";

    /* 查询 */
    private static String LIST_URL = BASE_URL + "/list";

    /* 详情 */
    private static String DETAIL_URL = BASE_URL + "/detail";

    /* 删除 */
    private static String DELETE_URL = BASE_URL + "/{ossConfigId}";

    /* 修改 */
    private static String UPDATE_URL = BASE_URL + "/update";

    /* 新增 */
    private static String ADD_URL = BASE_URL + "/add";

    @Autowired private MockMvc mockMvc;

    //	private WebTestClient client;

    // BeforeEach修饰在方法上，在每一个测试方法（所有@Test、@RepeatedTest、@ParameterizedTest或者@TestFactory注解的方法）之前执行一次。
    // 例如：一个测试类有2个测试方法testA()和testB()，还有一个@BeforeEach的方法，执行这个测试类
    // @BeforeEach的方法会在testA()之前执行一次，在testB()之前又执行一次。@BeforeEach的方法一共执行了2次。
    private Cookie[] cookies;

    @BeforeEach
    public void setUp() {
        cookies = getLoginCookies(LOGIN_URL, mockMvc);
    }

    //	@Before
    //	public void setup() {
    //		// 实例化方式一
    ////		mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    //		// 实例化方式二
    //		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    //	}

    @Test
    @SneakyThrows
    public void list_page() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.get(LIST_PAGE_URL)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .params(params))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void list() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.get(LIST_URL)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .params(params))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void detail() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("ossConfigId", "1");

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.get(DETAIL_URL)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .params(params))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void delete() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        //		MvcResult mvcResult = mockMvc
        //
        //	.perform(MockMvcRequestBuilders.delete(DELETE_URL).accept(MediaType.APPLICATION_JSON).params(params))
        //
        //	.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.delete(DELETE_URL, 1)
                                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void add() {
        SysOssConfig add = new SysOssConfig();

        add.setConfigKey("configKey");

        add.setAccessKey("accessKey");

        add.setSecretKey("secretKey");

        add.setBucketName("bucketName");

        add.setPrefix("prefix");

        add.setEndpoint("endpoint");

        add.setDomain("domain");

        add.setIsHttps("isHttps");

        add.setRegion("region");

        add.setAccessPolicy("accessPolicy");

        add.setStatus("status");

        add.setExt1("ext1");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        Gson gson = new Gson();

        String jsonString = gson.toJson(add);

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.post(ADD_URL)
                                        .content(jsonString)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void update() {
        SysOssConfig update = new SysOssConfig();

        update.setOssConfigId("ossConfigId");

        update.setConfigKey("configKey");

        update.setAccessKey("accessKey");

        update.setSecretKey("secretKey");

        update.setBucketName("bucketName");

        update.setPrefix("prefix");

        update.setEndpoint("endpoint");

        update.setDomain("domain");

        update.setIsHttps("isHttps");

        update.setRegion("region");

        update.setAccessPolicy("accessPolicy");

        update.setStatus("status");

        update.setExt1("ext1");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        Gson gson = new Gson();

        String jsonString = gson.toJson(update);

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.put(UPDATE_URL)
                                        .content(jsonString)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }
}