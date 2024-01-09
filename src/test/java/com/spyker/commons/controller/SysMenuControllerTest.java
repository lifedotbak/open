package com.spyker.commons.controller;

import com.google.gson.Gson;
import com.spyker.BaseTest;
import com.spyker.commons.entity.SysMenu;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
public class SysMenuControllerTest extends BaseTest {

    private static final String URL = "/application/sys-menu";

    @Autowired private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void list_page() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.get(URL + "/list_page")
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
                                MockMvcRequestBuilders.get(URL + "/list")
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

        params.add("menuId", "1");

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.get(URL + "/detail")
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

        params.add("menuId", "1");

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.delete(URL + "/delete")
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
        SysMenu add = new SysMenu();

        add.setMenuName("menuName");

        add.setParentId("parentId");

        add.setOrderNum(1);

        add.setPath("path");

        add.setComponent("component");

        add.setQuery("query");

        add.setIsFrame(1);

        add.setIsCache(1);

        add.setMenuType("menuType");

        add.setVisible("visible");

        add.setStatus("status");

        add.setPerms("perms");

        add.setIcon("icon");

        add.setCreateBy("createBy");

        add.setUpdateBy("updateBy");

        add.setRemark("remark");

        Gson gson = new Gson();

        String jsonString = gson.toJson(add);

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.post(URL + "/add")
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
        SysMenu update = new SysMenu();

        update.setMenuId("menuId");

        update.setMenuName("menuName");

        update.setParentId("parentId");

        update.setOrderNum(1);

        update.setPath("path");

        update.setComponent("component");

        update.setQuery("query");

        update.setIsFrame(1);

        update.setIsCache(1);

        update.setMenuType("menuType");

        update.setVisible("visible");

        update.setStatus("status");

        update.setPerms("perms");

        update.setIcon("icon");

        update.setCreateBy("createBy");

        update.setUpdateBy("updateBy");

        update.setRemark("remark");

        Gson gson = new Gson();

        String jsonString = gson.toJson(update);

        MvcResult mvcResult =
                mockMvc.perform(
                                MockMvcRequestBuilders.put(URL + "/update")
                                        .content(jsonString)
                                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andReturn();

        log.info(mvcResult.getResponse().getContentAsString());
    }
}