package com.spyker.framework.isapi.message;

import lombok.Data;

@Data
public class StreamInputChannel {

    String id;
    String name;
    String group;
    String startDecoding;

    StreamInput StreamInput;
}
