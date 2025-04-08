package com.spyker;

import jakarta.servlet.http.Cookie;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(
        classes = OpenApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
// @Rollback(false)
@Rollback
// Testing With a Mock Environment
@AutoConfigureMockMvc
@Slf4j
public class BaseTest {

    /**
     * BeforeAll修饰在方法上，使用该注解的方法在当前整个测试类中所有的测试方法之前执行，每个测试类运行时只会执行一次。
     *
     * @throws Exception
     */
    protected Cookie[] getLoginCookies(String loginUrl, MockMvc mockMvc) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("userName", "admin");
        params.add("password", "123456");

        try {
            MvcResult result =
                    mockMvc.perform(
                                    MockMvcRequestBuilders.post(loginUrl)
                                            .accept(MediaType.APPLICATION_JSON)
                                            .params(params))
                            .andExpect(MockMvcResultMatchers.status().isOk())
                            .andDo(MockMvcResultHandlers.print())
                            .andReturn();

            Cookie[] cookies = result.getResponse().getCookies();

            return cookies;

        } catch (Exception e) {
            log.error("--->{}", e.getMessage());
        }
        return null;
    }
}
