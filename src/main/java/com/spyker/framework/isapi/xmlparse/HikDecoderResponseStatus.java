package com.spyker.framework.isapi.xmlparse;

import lombok.Data;

@Data
public class HikDecoderResponseStatus {
    String requestURL;
    String statusCode;
    String statusString;
    String subStatusCode;
    String ID;
}
