package com.spyker.framework.util;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ZZF on 2012-5-14
 */
public final class FileUtil {

    /**
     * 创建临时文件
     * 该文件会在 JVM 退出时，进行删除
     *
     * @param data 文件内容
     * @return 文件
     */
    @SneakyThrows
    public static File createTempFile(String data) {
        File file = createTempFile();
        // 写入内容
        cn.hutool.core.io.FileUtil.writeUtf8String(data, file);
        return file;
    }

    /**
     * 创建临时文件
     * 该文件会在 JVM 退出时，进行删除
     *
     * @param data 文件内容
     * @return 文件
     */
    @SneakyThrows
    public static File createTempFile(byte[] data) {
        File file = createTempFile();
        // 写入内容
        cn.hutool.core.io.FileUtil.writeBytes(data, file);
        return file;
    }

    /**
     * 创建临时文件，无内容
     * 该文件会在 JVM 退出时，进行删除
     *
     * @return 文件
     */
    @SneakyThrows
    public static File createTempFile() {
        // 创建文件，通过 UUID 保证唯一
        File file = File.createTempFile(IdUtil.simpleUUID(), null);
        // 标记 JVM 退出时，自动删除
        file.deleteOnExit();
        return file;
    }

    /**
     * 生成文件路径
     *
     * @param content      文件内容
     * @param originalName 原始文件名
     * @return path，唯一不可重复
     */
    public static String generatePath(byte[] content, String originalName) {
        String sha256Hex = DigestUtil.sha256Hex(content);
        // 情况一：如果存在 name，则优先使用 name 的后缀
        if (StrUtil.isNotBlank(originalName)) {
            String extName = FileNameUtil.extName(originalName);
            return StrUtil.isBlank(extName) ? sha256Hex : sha256Hex + "." + extName;
        }
        // 情况二：基于 content 计算
        return sha256Hex + '.' + FileTypeUtil.getType(new ByteArrayInputStream(content));
    }

    public static void write(InputStream inputStream, File toFile) throws IOException {

        OutputStream outputStream = new FileOutputStream(toFile);

        IOUtils.copy(inputStream, outputStream);

        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }

    public static String getFileType(String fileName) {

        final String[] splits = fileName.split("\\.");

        if (splits.length > 1) {
            return "." + splits[splits.length - 1];
        }

        return "";
    }

    public static String getPathFromDate() {

        String format = "/" + "yyyy" + "/" + "MM" + "/" + "dd" + "/";

        final SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
        final String result = simpleFormat.format(new Date(System.currentTimeMillis()));

        return result;
    }

    public static void delete(String filePath) {
        final File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }

    public static void delete(File deleteFile) {

        if (deleteFile.exists()) {
            deleteFile.delete();
        }
    }

    public static boolean exist(File file) {

        return file.exists() && file.isFile();
    }

    public static void createDir(String destDirName) {
        File dir = new File(destDirName);

        if (!dir.exists()) {

            if (!destDirName.endsWith(File.separator)) {
                destDirName = destDirName + File.separator;
            }

            dir.mkdirs();
        }
    }
}