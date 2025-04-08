package com.spyker.framework.isapi.message;

import lombok.Data;

import java.util.List;

@Data
public class StreamInputRealtime {
    String durationInUnit;

    List<StreamRealtimeUnit> StreamRealtimeUnitList;
}
