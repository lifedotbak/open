package com.spyker.framework.util.number;

import java.text.DecimalFormat;

public final class DecimalFormatUtils {

    public enum Format {

        /**
         * 十分位
         */
        tenth("###.#")

        /**
         * 百分位
         */
        ,
        percentile("###.##");

        private final String format;

        Format(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }

    }

    public static float format(double value, Format format) {
        DecimalFormat df = new DecimalFormat(format.getFormat());

        float any = Float.parseFloat(df.format(value));

        return any;
    }

    /**
     * 四舍五入保留一位小数
     *
     * @param a
     * @return
     */
    public static float keepOneDecimal(float a) {
        return (float) (Math.round(a * 10)) / 10;
    }

    /**
     * 如果只是用于程序中的格式化数值然后输出，那么这个方法还是挺方便的。
     * 应该是这样使用：System.out.println(String.format("%.2f", d));
     *
     * @param f
     * @return
     */
    public static String formatFloat(float f, String format) {
        return String.format(format, f);
    }
}