package com.spyker.commons.controller;

import com.google.gson.Gson;
import com.spyker.BaseTest;
import com.spyker.commons.entity.RInfo;
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
public class RInfoControllerTest extends BaseTest {

    private static String URL = "/commons/r-info";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void list_page() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(URL + "/list_page").accept(MediaType.APPLICATION_JSON).params(params)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

        log.info(mvcResult.getResponse().getContentAsString());

    }

    @Test
    @SneakyThrows
    public void list() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(URL + "/list").accept(MediaType.APPLICATION_JSON).params(params)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

        log.info(mvcResult.getResponse().getContentAsString());

    }

    @Test
    @SneakyThrows
    public void detail() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("id", "1");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get(URL + "/detail").accept(MediaType.APPLICATION_JSON).params(params)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

        log.info(mvcResult.getResponse().getContentAsString());

    }

    @Test
    @SneakyThrows
    public void delete() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("id", "1");

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/delete").accept(MediaType.APPLICATION_JSON).params(params)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

        log.info(mvcResult.getResponse().getContentAsString());

    }

    @Test
    @SneakyThrows
    public void add() {
        RInfo add = new RInfo();

        add.setName("name");

        add.setInfo("info");

        Gson gson = new Gson();

        String jsonString = gson.toJson(add);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.post(URL + "/add").content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

        log.info(mvcResult.getResponse().getContentAsString());

    }

    @Test
    @SneakyThrows
    public void update() {
        RInfo update = new RInfo();

        update.setId("id");

        update.setName("name");

        update.setInfo("info");

        Gson gson = new Gson();

        String jsonString = gson.toJson(update);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.put(URL + "/update").content(jsonString).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

        log.info(mvcResult.getResponse().getContentAsString());

    }

}