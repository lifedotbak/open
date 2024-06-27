package com.spyker.commons.controller.common;

import io.github.lunasaw.zlm.entity.ServerResponse;
import io.github.lunasaw.zlm.entity.Version;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "zlm-spring-boot-starter接口", description = "zlm-spring-boot-starter接口")
@RequestMapping("/zlm")
@RestController
public class ZlmRestController {

    //    @Autowired private ZlmProperties zlmProperties;

    //    @GetMapping("/getProperties")
    //    public ServerResponse<ZlmProperties> getProperties() {
    //        ServerResponse<ZlmProperties> result = new ServerResponse<>();
    //        result.setData(zlmProperties);
    //        return result;
    //    }

    @GetMapping("/getApiList")
    public ServerResponse<List<String>> getApiList() {

        ServerResponse<List<String>> result =
                io.github.lunasaw.zlm.api.ZlmRestService.getApiList(
                        "http://192.168.200.81:8080", "035c73f7-bb6b-4889-a715-d9eb2d1925cc");

        return result;
    }

    @GetMapping("/getVersion")
    public ServerResponse<Version> getVersion() {

        ServerResponse<Version> result =
                io.github.lunasaw.zlm.api.ZlmRestService.getVersion(
                        "http://192.168.200.81:8080", "035c73f7-bb6b-4889-a715-d9eb2d1925cc");

        return result;
    }
}