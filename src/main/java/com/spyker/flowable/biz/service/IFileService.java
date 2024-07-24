package com.spyker.flowable.biz.service;

import com.spyker.flowable.common.dto.R;

/**
 * @author Huijun Zhao
 * @description
 * @date 2023-11-02 09:54
 */
public interface IFileService {
    /**
     * 保存文件
     *
     * @param fileName
     * @return
     */
    R<String> save(byte[] data, String fileName);
}