package com.calculator.string;

public class BasicString {
	/**
	 * 数据存放位置
	 */
	public final static String CONFIG_PATH = "data/config.data";
	/**
	 * 帮助文档
	 */
	public final static String HELP = 
			"1.按钮“x/y”用来将运算结果转化为分数形式,\n支持切换，但不支持:"
			+ "根号运算(√),阶乘运算(!)\n,幂运算(^),E级数运算(带有E的式子),π运算。\n\n"
			+ "2.“C”,“MR”,“^2”,“^”,“/”,“*”,\n“9”,“6”,“3”键右键实现特定功能，停留\n相应键片刻即可获取提示。\n\n"
			+ "3.支持简单键盘输入，但提倡通过更强大的按键\n输入。" ;
	/**
	 * 关于
	 */
	public final static String ABOUT = "计算器\nVersion:2.0\nTime:2014.8.29";
	/**
	 * 错误输入提示
	 */
	public final static String ERROR = "输入有误，请输入正确的表达式";
	/**
	 * 进制错误
	 */
	public final static String SYSTEMERROR = "进制转换不能计算小数，或者一条式子，且只支持十进制与其他进制的转换，请修正";
	/**
	 * 分数与小数转换错误
	 */
	public final static String TRANSFROMERROR = "不支持:\n根号运算(√),阶乘运算(!),幂运算(^),E级数运算(带有E的式子),π运算\n\n"
	 		+ "请先按等号进行计算再进行分式转化";
	/**
	 * 阶乘错误
	 */
	public final static String FACTORIALERROR = "阶乘不能计算小数和E级数，请不要在\"!\"前出现小数点\"E\"";
}
