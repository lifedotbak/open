package com.spyker.framework.easyexcel.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.spyker.framework.util.ExJsonUtils;

/**
 * Excel Json 转换器
 *
 * @author spyker
 */
public class JsonConvert implements Converter<Object> {

    @Override
    public Class<?> supportJavaTypeKey() {
        throw new UnsupportedOperationException("暂不支持，也不需要");
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        throw new UnsupportedOperationException("暂不支持，也不需要");
    }

    @Override
    public WriteCellData<String> convertToExcelData(
            Object value,
            ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) {
        // 生成 Excel 小表格
        return new WriteCellData<>(ExJsonUtils.toJsonString(value));
    }
}