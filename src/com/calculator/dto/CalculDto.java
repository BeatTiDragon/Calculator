package com.calculator.dto;

import java.awt.Point;
import java.io.Serializable;
import java.math.BigDecimal;

import com.calculator.algorithm.SystemType;
import com.calculator.io.Record;
/**
 * ���ݴ������
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
    	//��һ������Ĭ�ϴ���10����ʷ
    	if(records == null){
    	location = new Point(0,0);
    	records = new Record[10];
    	memory = new BigDecimal(0);
        for(int i=0;i<records.length;i++)
        	records[i] = new Record();
    	}
    } 
    /**@param type ��¼����ö������*/
    public void setType(SystemType type) {
    	this.type = type;
    }
    /**@param type ��¼����ö������*/
    public SystemType getType() {
    	return type;
    }
    /**@param memory ��¼M������*/
    public void setMemory(BigDecimal memory) {
    	this.memory = memory;
    }
    /**@return memory ��¼M������*/
    public BigDecimal getMemory() {
    	return memory;
    }
    /**@param skin ��¼Ƥ��*/
    public void setSkin(int skin) {
    	this.skin = skin;
    }
    /**@return skin ��¼Ƥ��*/
    public int getSkin() {
    	return skin;
    }
    /**@param theme ��¼����*/
    public void setTheme(int theme) {
    	this.theme = theme;
    }
    /**@param theme ��¼����*/
    public int getTheme() {
    	return theme;
    }
    /**@param fraction ��¼�������*/
    public void setFraction(String fraction) {
    	this.fraction = fraction;
    }
    /**@param fraction ��¼�������*/
    public String getFraction() {
    	return this.fraction;
    }
    /**@param decimal ��¼С�����*/
    public void setDecimal(String decimal) {
    	this.decimal = decimal;
    }
    /**@param decimal ��¼С�����*/
    public String getDecimal() {
    	return this.decimal;
    }
	/**@param Click ����Ƿ����Ⱥţ�����ǵȺ�ȡ��*/
    public void setIsClick(boolean Click) {
    	this.Click = Click;
    }
	/**@param Click ����Ƿ����Ⱥţ�����ǵȺ�ȡ��*/
    public boolean getIsClick() {
    	return Click;
    }
    /**@param tip ��¼ʣ������ƥ����*/
    public void setTip(int tip) {
    	this.tip = tip;
    }
    /**@param tip ��¼ʣ������ƥ����*/
    public int getTip() {
    	return this.tip;
    }
    /**@param location ��¼���ڵ�����λ��*/
    public void setLocation(int x,int y) {
    	this.location.x = x;
    	this.location.y = y;
    }
    /**@param location ��¼���ڵ�����λ��*/
    public Point getLocation() {
    	return location;
    }

    /**@param view ��¼��ʾ��ʽ,Ĭ������ʾ*/
    public void setView(int view) {
    	this.view = view;
    }
    /**@param view ��¼��ʾ��ʽ,Ĭ������ʾ*/
    public int getView() {
    	return this.view;
    }
    /**@param panel ��¼������壬0�����׼���,1�������ת�����*/
    public void setPanel(int panel) {
    	this.panel = panel;
    }
    /**@return panel ��¼������壬0�����׼���,1�������ת�����*/
    public int getPanel() {
    	return panel;
    }
    /**@param records ��ʷ��¼*/
    public void setRecord(Record[] records) {
    	this.records = records;
    }
    /**@param records ��ʷ��¼*/
	public Record[] getRecord() {
		return this.records;
	}
    /**@param records ��ʷ��¼*/
	public Record getRecord(int index) {
		return this.records[index];
	}
}
