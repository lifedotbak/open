package com.spyker.commons.controller;

import com.google.gson.Gson;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysOperLog;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@Slf4j
public class SysOperLogControllerTest extends BaseTest {

    private static final String BASE_URL = "/commons/sys-oper-log";

    private static final String LOGIN_URL = BASE_URL + "/sys/login/login";

    /*分页查询*/
    private static final String LIST_PAGE_URL = BASE_URL + "/list_page";

    /*查询*/
    private static final String LIST_URL = BASE_URL + "/list";

    /*详情*/
    private static final String DETAIL_URL = BASE_URL + "/detail";

    /*删除*/
    private static final String DELETE_URL = BASE_URL + "/delete";

    /*修改*/
    private static final String UPDATE_URL = BASE_URL + "/update";

    /*新增*/
    private static final String ADD_URL = BASE_URL + "/add";

    //  @Autowired
    //   private MockMvc mockMvc;

    //  @Autowired
    private MockMvc mockMvc;
    //
    @Autowired private WebApplicationContext webApplicationContext;

    private Cookie[] cookies;

    //  private WebTestClient client;

    @Test
    @SneakyThrows
    public void add() {
        SysOperLog add = new SysOperLog();

        add.setTitle("title");

        add.setBusinessType(1);

        add.setMethod("method");

        add.setRequestMethod("requestMethod");

        add.setOperatorType(1);

        add.setDeptName("deptName");

        add.setOperUrl("operUrl");

        add.setOperIp("operIp");

        add.setOperParam("operParam");

        add.setJsonResult("jsonResult");

        add.setStatus(1);

        add.setErrorMsg("errorMsg");

        Gson gson = new Gson();

        String jsonString = gson.toJson(add);

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.post(ADD_URL)
                                        .cookie(cookies)
                                        .content(jsonString)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void delete() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("id", "1");

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.delete(DELETE_URL)
                                        .cookie(cookies)
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

        params.add("id", "1");

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.get(DETAIL_URL)
                                        .cookie(cookies)
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
                                        .cookie(cookies)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .params(params))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void list_page() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.get(LIST_PAGE_URL)
                                        .cookie(cookies)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .params(params))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    public void update() {
        SysOperLog update = new SysOperLog();

        update.setId("id");

        update.setTitle("title");

        update.setBusinessType(1);

        update.setMethod("method");

        update.setRequestMethod("requestMethod");

        update.setOperatorType(1);

        update.setDeptName("deptName");

        update.setOperUrl("operUrl");

        update.setOperIp("operIp");

        update.setOperParam("operParam");

        update.setJsonResult("jsonResult");

        update.setStatus(1);

        update.setErrorMsg("errorMsg");

        Gson gson = new Gson();

        String jsonString = gson.toJson(update);

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.put(UPDATE_URL)
                                        .cookie(cookies)
                                        .content(jsonString)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }

    @BeforeEach
    void setUp() {
        //    client =
        // MockMvcWebTestClient.bindToApplicationContext(this.webApplicationContext).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        cookies = getLoginCookies(LOGIN_URL, mockMvc);
    }
}
