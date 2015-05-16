package com.calculator.io;

import java.io.Serializable;

public class Record implements Serializable{

	private static final long serialVersionUID = 1L;
    /**
     * 式子记录
     */
	private  String formula;
	/**
	 * 结果记录
	 */
	private  String result;
	
	public String getFormula(){
		return this.formula;
	}
	
	public void setFormula(String formula){
		this.formula=formula;
	}
	
	public String getResult(){
		return this.result;
	}
 
	public void setResult(String result){
		this.result=result;
	}
	public void setRecord(String formula,String result){
		this.formula=formula;
		this.result=result;
	}

}
