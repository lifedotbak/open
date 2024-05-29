package com.spyker.framework.minio;

import lombok.Getter;

@Getter
public enum ContentTypeEnum {
    DEFAULT("default", "application/octet-stream"),
    JPG("jpg", "image/jpeg"),
    TIFF("tiff", "image/tiff"),
    GlF("gif", "image/gif"),
    JFIF("jfif", "image/jpeg"),
    PNG("png", "image/png"),
    TIF("tif", "image/tiff"),
    lCo("ico", "image/x-icon"),
    JPEG("jpeg", "image/jpeg"),
    WBMP("wbmp", "image/vnd.wap.wbmp"),
    FAX("fax", "image/fax"),
    NET("net", "image/pnetvue"),
    JPE("jpe", "image/jpeg"),
    RP("rp", "image/vnd.rn-realpix");

    String fileType;
    String content;

    ContentTypeEnum(String fileType, String content) {
        this.fileType = fileType;
        this.content = content;
    }
}