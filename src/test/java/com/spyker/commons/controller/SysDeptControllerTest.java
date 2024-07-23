package com.spyker.commons.controller;

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
import lombok.RequiredArgsConstructor;

import jakarta.servlet.http.Cookie;

import com.spyker.BaseTest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.google.gson.Gson;

import com.spyker.commons.entity.SysDept;

@Slf4j
public class SysDeptControllerTest extends BaseTest {

    private static String BASE_URL = "/commons/sys-dept";

    private static final String LOGIN_URL = BASE_URL + "/sys/login/login";

    /*分页查询*/
    private static String LIST_PAGE_URL = BASE_URL + "/list_page";

    /*查询*/
    private static String LIST_URL = BASE_URL + "/list";

    /*详情*/
    private static String DETAIL_URL = BASE_URL + "/detail";

    /*删除*/
    private static String DELETE_URL = BASE_URL + "/delete";

    /*修改*/
    private static String UPDATE_URL = BASE_URL + "/update";

    /*新增*/
    private static String ADD_URL = BASE_URL + "/add";

    //  @Autowired
    //   private MockMvc mockMvc;

    //  @Autowired
    private MockMvc mockMvc;
    //
    @Autowired private WebApplicationContext webApplicationContext;

    private Cookie[] cookies;

    //  private WebTestClient client;

    @BeforeEach
    void setUp() {
        //    client =
        // MockMvcWebTestClient.bindToApplicationContext(this.webApplicationContext).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        cookies = getLoginCookies(LOGIN_URL, mockMvc);
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
    public void add() {
        SysDept add = new SysDept();

        add.setName("name");

        add.setParentId("parentId");

        add.setDelFlag(1);

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setStatus(1);

        add.setDeptSort(1);

        add.setTenantId("tenantId");

        add.setAncestors("ancestors");

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
    public void update() {
        SysDept update = new SysDept();

        update.setId("id");

        update.setName("name");

        update.setParentId("parentId");

        update.setDelFlag(1);

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setStatus(1);

        update.setDeptSort(1);

        update.setTenantId("tenantId");

        update.setAncestors("ancestors");

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
}