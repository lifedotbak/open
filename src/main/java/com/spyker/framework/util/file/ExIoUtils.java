package com.spyker.framework.util.file;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * IO 工具类，用于 {@link IoUtil} 缺失的方法
 *
 * @author spyker
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExIoUtils {

    /**
     * 从流中读取 UTF8 编码的内容
     *
     * @param in 输入流
     * @param isClose 是否关闭
     * @return 内容
     * @throws IORuntimeException IO 异常
     */
    public static String readUtf8(InputStream in, boolean isClose) throws IORuntimeException {
        return StrUtil.utf8Str(IoUtil.read(in, isClose));
    }
}