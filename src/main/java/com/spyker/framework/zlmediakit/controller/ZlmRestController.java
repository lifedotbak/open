package com.spyker.framework.zlmediakit.controller;

import com.spyker.framework.zlmediakit.config.ExZlmProperties;

import io.github.lunasaw.zlm.api.ZlmRestService;
import io.github.lunasaw.zlm.entity.ServerResponse;
import io.github.lunasaw.zlm.entity.Version;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "zlm-Service", description = "zlm-Service")
@RequestMapping("/zlm")
@RestController
public class ZlmRestController {

    @Autowired private ExZlmProperties exZlmProperties;

    @GetMapping("/getProperties")
    public ServerResponse<ExZlmProperties> getProperties() {
        ServerResponse<ExZlmProperties> result = new ServerResponse<>();
        result.setData(exZlmProperties);
        return result;
    }

    @GetMapping("/getApiList")
    public ServerResponse<List<String>> getApiList() {

        ServerResponse<List<String>> result =
                ZlmRestService.getApiList(
                        "http://192.168.200.81:8080", "035c73f7-bb6b-4889-a715-d9eb2d1925cc");

        return result;
    }

    @GetMapping("/getVersion")
    public ServerResponse<Version> getVersion() {

        ServerResponse<Version> result =
                ZlmRestService.getVersion(
                        "http://192.168.200.81:8080", "035c73f7-bb6b-4889-a715-d9eb2d1925cc");

        return result;
    }
}