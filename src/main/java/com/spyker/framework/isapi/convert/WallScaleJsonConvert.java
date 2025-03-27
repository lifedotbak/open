package com.spyker.framework.isapi.convert;

public class WallScaleJsonConvert {

    String ID;
    int rowNum;
    int colNum;

    public WallScaleJsonConvert(String ID, int rowNum, int colNum) {
        this.ID = ID;
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    public String getJson() {
        return "{\"ID\": " + ID + ",\"rowNum\": " + rowNum + ",\"colNum\": " + colNum + "}";
    }
}