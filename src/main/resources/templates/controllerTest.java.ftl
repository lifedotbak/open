package ${package.Controller};


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
import lombok.RequiredArgsConstructor;

import com.spyker.application.BaseTest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.google.gson.Gson;

import ${package.Entity}.${entity};

@Slf4j
public class ${table.controllerName}Test extends BaseTest {

    private static String URL ="<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>";
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @SneakyThrows
    public void list_page() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
      
        MvcResult mvcResult = mockMvc
            .perform(MockMvcRequestBuilders.get(URL+"/list_page")
                .accept(MediaType.APPLICATION_JSON).params(params))
            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
      
        log.info(mvcResult.getResponse().getContentAsString());
    
    }
    
    
    @Test
    @SneakyThrows
    public void list() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
      
        MvcResult mvcResult = mockMvc
            .perform(MockMvcRequestBuilders.get(URL+"/list")
                .accept(MediaType.APPLICATION_JSON).params(params))
            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
      
        log.info(mvcResult.getResponse().getContentAsString());
    
    }
    
    
    @Test
    @SneakyThrows
    public void detail() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
      
        params.add("id","1");
      
        MvcResult mvcResult = mockMvc
            .perform(MockMvcRequestBuilders.get(URL+"/detail")
                .accept(MediaType.APPLICATION_JSON).params(params))
            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
      
        log.info(mvcResult.getResponse().getContentAsString());
    
    }
    
    @Test
    @SneakyThrows
    public void delete() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
      
        params.add("id","1");
      
        MvcResult mvcResult = mockMvc
            .perform(MockMvcRequestBuilders.delete(URL+"/delete")
                .accept(MediaType.APPLICATION_JSON).params(params))
            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
      
        log.info(mvcResult.getResponse().getContentAsString());
    
    }
    
    
    @Test
    @SneakyThrows
    public void add() {
        ${entity} add = new ${entity}();
      
        Gson gson = new Gson();
      
        String jsonString = gson.toJson(add);
      
        MvcResult mvcResult = mockMvc
            .perform(MockMvcRequestBuilders.post(URL + "/add").content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
      
        log.info(mvcResult.getResponse().getContentAsString());
    
    }
    
    
    @Test
    @SneakyThrows
    public void update() {
        ${entity} update = new ${entity}();
      
        update.setId(1L);
      
        Gson gson = new Gson();
      
        String jsonString = gson.toJson(update);
      
        MvcResult mvcResult = mockMvc
            .perform(MockMvcRequestBuilders.put(URL + "/update").content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();    
            
      
        log.info(mvcResult.getResponse().getContentAsString());
    
    }
 
}

