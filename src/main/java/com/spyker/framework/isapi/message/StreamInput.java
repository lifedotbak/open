package com.spyker.framework.isapi.message;

import lombok.Data;

@Data
public class StreamInput {

    String streamInputMode;
    String streamEncryptEnable;
    StreamInputRealtime StreamInputRealtime;
}