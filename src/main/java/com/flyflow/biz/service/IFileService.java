package com.flyflow.biz.service;

import com.flyflow.common.dto.R;

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