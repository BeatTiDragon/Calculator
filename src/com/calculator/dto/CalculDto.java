package com.calculator.dto;

import java.awt.Point;
import java.io.Serializable;
import java.math.BigDecimal;

import com.calculator.algorithm.SystemType;
import com.calculator.io.Record;
/**
 * 数据传输对象
 * @author .C
 *
 */
public class CalculDto  implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private boolean Click;
    private String fraction;
    private String decimal;
    private int tip;
    private Point location;
    private int view = 4;
    private int panel = 0;
    private int skin;
    private int theme;
    private BigDecimal memory;
    private Record[] records;
    private SystemType type = SystemType.DEC;
    
    public CalculDto() {
    	//第一次启动默认创建10个历史
    	if(records == null){
    	location = new Point(0,0);
    	records = new Record[10];
    	memory = new BigDecimal(0);
        for(int i=0;i<records.length;i++)
        	records[i] = new Record();
    	}
    } 
    /**@param type 记录进制枚举类型*/
    public void setType(SystemType type) {
    	this.type = type;
    }
    /**@param type 记录进制枚举类型*/
    public SystemType getType() {
    	return type;
    }
    /**@param memory 记录M运算结果*/
    public void setMemory(BigDecimal memory) {
    	this.memory = memory;
    }
    /**@return memory 记录M运算结果*/
    public BigDecimal getMemory() {
    	return memory;
    }
    /**@param skin 记录皮肤*/
    public void setSkin(int skin) {
    	this.skin = skin;
    }
    /**@return skin 记录皮肤*/
    public int getSkin() {
    	return skin;
    }
    /**@param theme 记录主题*/
    public void setTheme(int theme) {
    	this.theme = theme;
    }
    /**@param theme 记录主题*/
    public int getTheme() {
    	return theme;
    }
    /**@param fraction 记录分数结果*/
    public void setFraction(String fraction) {
    	this.fraction = fraction;
    }
    /**@param fraction 记录分数结果*/
    public String getFraction() {
    	return this.fraction;
    }
    /**@param decimal 记录小数结果*/
    public void setDecimal(String decimal) {
    	this.decimal = decimal;
    }
    /**@param decimal 记录小数结果*/
    public String getDecimal() {
    	return this.decimal;
    }
	/**@param Click 标记是否点击等号，点击非等号取反*/
    public void setIsClick(boolean Click) {
    	this.Click = Click;
    }
	/**@param Click 标记是否点击等号，点击非等号取反*/
    public boolean getIsClick() {
    	return Click;
    }
    /**@param tip 记录剩余括号匹配数*/
    public void setTip(int tip) {
    	this.tip = tip;
    }
    /**@param tip 记录剩余括号匹配数*/
    public int getTip() {
    	return this.tip;
    }
    /**@param location 记录窗口的坐标位置*/
    public void setLocation(int x,int y) {
    	this.location.x = x;
    	this.location.y = y;
    }
    /**@param location 记录窗口的坐标位置*/
    public Point getLocation() {
    	return location;
    }

    /**@param view 记录显示方式,默认右显示*/
    public void setView(int view) {
    	this.view = view;
    }
    /**@param view 记录显示方式,默认右显示*/
    public int getView() {
    	return this.view;
    }
    /**@param panel 记录计算面板，0代表标准面板,1代表进制转换面板*/
    public void setPanel(int panel) {
    	this.panel = panel;
    }
    /**@return panel 记录计算面板，0代表标准面板,1代表进制转换面板*/
    public int getPanel() {
    	return panel;
    }
    /**@param records 历史记录*/
    public void setRecord(Record[] records) {
    	this.records = records;
    }
    /**@param records 历史记录*/
	public Record[] getRecord() {
		return this.records;
	}
    /**@param records 历史记录*/
	public Record getRecord(int index) {
		return this.records[index];
	}
}
