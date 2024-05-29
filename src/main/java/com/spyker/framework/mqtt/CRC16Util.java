package com.spyker.framework.mqtt;

import com.github.sftwnd.crayfish.common.crc.CRC;
import com.github.sftwnd.crayfish.common.crc.CrcModel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

public final class CRC16Util {

    private CRC16Util() {}

    // {"msg":"C0C0C0FF48444C4D495241434C45AAAA0FFDFEFFFE00312802010000007A83"}
    public static void main(String[] args) {

        System.out.println(crc16ModbusLowHigh("01 03 00 01 00 02"));
    }

    /**
     * CRC16_MODBUS 低位在前
     *
     * @param validationData
     * @return
     */
    public static String crc16ModbusLowHigh(String validationData) {

        String result = crc(CrcModel.CRC16_MODBUS, validationData);

        return convertLowHigh(result);
    }

    /**
     * crc校验 高位字节在前
     *
     * @param useModel crc校验模式
     * @param validationData crc 校验值
     * @return 检验结果 高位字节在前
     */
    private static String crc(CrcModel useModel, String validationData) {

        CrcModel crcModel = CrcModel.lookUp(useModel.getName());

        CRC crc = crcModel.getCRC(hexStr2Bytes(validationData));

        long crcValue = crc.getCrc();

        //        return Long.toHexString(crcValue).toUpperCase();

        String result = Long.toHexString(crcValue).toUpperCase();

        if (result.length() < 4) {
            result = StringUtils.leftPad(result, 4, "0");
        }

        return result;
    }

    /**
     * 转换为低位在前
     *
     * @param crcValue
     * @return
     */
    private static String convertLowHigh(@NonNull String crcValue) {

        if (null == crcValue || crcValue.length() > 4) {
            return "";
        }

        crcValue = StringUtils.leftPad(crcValue, 4, "0");

        String high = crcValue.substring(0, 2);
        String low = crcValue.substring(2, 4);

        return low + high;
    }

    private static byte[] hexStr2Bytes(String src) {
        src = src.replaceAll(" ", "");
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            String sss = "0x" + src.substring(i * 2, m) + src.substring(m, n);
            try {
                ret[i] = Byte.decode(sss);
            } catch (Exception e) {
                // TODO: handle exception
                int s = Integer.decode(sss);
                ret[i] = (byte) s;
            }
        }
        return ret;
    }

    public static String crc16Xmodem(String validationData) {

        return crc(CrcModel.CRC16_XMODEM, validationData);
    }
}