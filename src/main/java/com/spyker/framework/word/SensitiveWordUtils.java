package com.spyker.framework.word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 18-6-4 Time: 上午9:47 To
 * change this template use File | Settings | File Templates.
 */
public class SensitiveWordUtils {

	// 判断是不是数字
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	// 判断是不是固定电话
	public static boolean isPhone(String str) {
		Pattern pattern = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	// 判断是不是电子邮箱
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern
				.compile("^[A-Z0-9a-zd]+([-_.][A-Za-zd]+)*@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{1,5}$");
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	// 判断是不是中文姓名
	public static boolean isChineseName(String str) {
//        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5]+$");
		str = str.replace("·", "•");
		Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5]+(•[\\u4e00-\\u9fa5]+)*$");
		Matcher isChinese = pattern.matcher(str);
		return isChinese.matches();
	}

	// 判断是不是汉字
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5]+$");
		Matcher isChinese = pattern.matcher(str);
		return isChinese.matches();
	}

	// 判断8位数字
	public static boolean isNum(String str) {
		return Pattern.matches("^\\d{8}$", str);
	}

	// 校验护照
	public static boolean isPassCard(String en) {
		return Pattern.matches("^[A-Za-z]{1}[0-9]{8}+$", en);
	}

	// 台胞证校验
	public static boolean taiWanCard(String num) {
		return Pattern.matches("^[0-9]{8}+$", num);
	}

	// 车牌号校验
	public static boolean carId(String str) {
		return Pattern.matches("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4,5}[A-Z0-9挂学警港澳]{1}$", str);
	}

	// 车架号校验
	public static boolean carVin(String str) {
		return Pattern.matches("[A-Za-z0-9]{11}[0-9]{6}$", str);
	}

	// 银行卡号校验
	public static boolean bankCardId(String str) {
		return Pattern.matches("[0-9]{13,19}$", str);
	}

	// 存折账号校验
	public static boolean bankBookId(String str) {
		return Pattern.matches("[0-9]{14,19}$", str);
	}

	// 增值税税号校验
	public static boolean vatNumberId(String str) {
		return Pattern.matches("[0-9A-Z]{15,20}$", str);
	}

	// 增值税账户校验
	public static boolean vatAccountId(String str) {
		return Pattern.matches("[0-9]{8,28}$", str);
	}

}
