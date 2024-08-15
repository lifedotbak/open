package com.spyker.flowable.web.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;

import com.spyker.flowable.biz.service.IFileService;
import com.spyker.flowable.biz.vo.FileVO;
import com.spyker.flowable.common.config.NotWriteLogAnno;
import com.spyker.flowable.common.dto.R;

import jakarta.servlet.http.HttpServletResponse;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import javax.annotation.Resource;

/** 文件控制器 */
@RestController
@RequestMapping(value = {"file"})
public class FlowableFileController {

    @Resource private IFileService fileService;

    @Value("${file.dir}")
    private String fileDir;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("upload")
    @NotWriteLogAnno(exclude = false, all = true)
    public R<String> upload(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        return fileService.save(file.getBytes(), originalFilename);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("uploadBase64")
    @NotWriteLogAnno(exclude = false, all = true)
    public R<String> uploadBase64(@RequestBody FileVO file) {

        File f =
                new File(StrUtil.format("/tmp/{}_{}", IdUtil.fastSimpleUUID(), file.getFileName()));

        try {
            Base64.decodeToFile(file.getBase64(), f);

            return fileService.save(FileUtil.readBytes(f), file.getFileName());
        } finally {
            FileUtil.del(f);
        }
    }

    /**
     * 显示文件
     *
     * @param key
     */
    @SneakyThrows
    @GetMapping("/show/{key}")
    @NotWriteLogAnno(
            exclude = false,
            all = false,
            printResultLog = false,
            paramsExclude = "response")
    public void show(@PathVariable String key, HttpServletResponse response) {
        String format = StrUtil.format("{}/{}", fileDir, key);
        IoUtil.write(response.getOutputStream(), true, FileUtil.readBytes(format));
    }

    /**
     * 显示文件
     *
     * @param key
     */
    @SneakyThrows
    @GetMapping("/show/{s}/{key}")
    @NotWriteLogAnno(
            exclude = false,
            all = false,
            printResultLog = false,
            paramsExclude = "response")
    public void show(
            @PathVariable String key, @PathVariable String s, HttpServletResponse response) {
        String format = StrUtil.format("{}/{}/{}", fileDir, s, key);
        IoUtil.write(response.getOutputStream(), true, FileUtil.readBytes(format));
    }
}