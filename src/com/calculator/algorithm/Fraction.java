package com.calculator.algorithm;

import java.math.BigInteger;
import java.util.StringTokenizer;

import com.calculator.util.Recline;

public class Fraction {

private BigInteger num,den;
private static StringTokenizer st;
private static Fraction f1,f2;
private static char way;

//分子分母
private  Fraction(String x,String y){
	this.num=new BigInteger(x); this.den=new BigInteger(y);
}
private Fraction(BigInteger resultNum, BigInteger resultDen) {
	this.num=resultNum; this.den=resultDen;
}
//返回分子与分母
private BigInteger getDen(){
	return den;
}
private BigInteger getNum(){
	return num;
}
//约分
private Fraction simplify(){
	BigInteger gcd=num.gcd(den);
	num=num.divide(gcd);
	den=den.divide(gcd);
	if(den.signum()<0){
		den=den.abs();
		num=num.negate();
	}
	return this;
}
//分数的加减乘除
private Fraction add(Fraction fraction){
	BigInteger resultDen=this.den.multiply(fraction.getDen());
	BigInteger resultNum=this.num.multiply(fraction.getDen()).add(this.den.multiply(fraction.getNum()));
	Fraction result=new Fraction(resultNum,resultDen);
	return result.simplify();
}
private Fraction sub(Fraction fraction){
	BigInteger resultDen=this.den.multiply(fraction.getDen());
	BigInteger resultNum=this.num.multiply(fraction.getDen()).subtract(this.den.multiply(fraction.getNum()));
	Fraction result=new Fraction(resultNum,resultDen);
	return result.simplify();
}
private Fraction mul(Fraction fraction){
	BigInteger resultDen=this.den.multiply(fraction.getDen());
	BigInteger resultNum=this.num.multiply(fraction.getNum());
	Fraction result=new Fraction(resultNum,resultDen);
	return result.simplify();
}
private Fraction div(Fraction fraction){
	BigInteger resultDen=this.den.multiply(fraction.getNum());
	BigInteger resultNum=this.num.multiply(fraction.getDen());
	Fraction result=new Fraction(resultNum,resultDen);
	return result.simplify();
}

//基本运算
private static String Basis(char util,Fraction x,Fraction y){
  Fraction s =null;
	switch(util){
	case '+':s=x.add(y);break;
	case '-':s=x.sub(y);break;
	case '*':s=x.mul(y);break;
	case '/':s=x.div(y);break;
   }
	return s.num+"/"+s.den;
}


private static  String priority(String str) {
int mark = 0;
/*循环到没括号即跳出*/
while(str.indexOf('(')!=-1&&str.indexOf(')')!=-1){
	
	st=new StringTokenizer(str,"()",true);
	int counts=st.countTokens();
    String []strMiddle=new String[counts];
	//mark记住最后一个“（”的下一位序号
	for(int i=0;i<counts;i++){
		strMiddle[i]=st.nextToken();
		if(strMiddle[i].indexOf('(')!=-1)
			mark=i+1;
	}
    //括号内的运算结果
	strMiddle[mark]=unit(strMiddle[mark]);
	if(strMiddle[mark].indexOf('.')==-1&&strMiddle[mark].indexOf('/')==-1)
		strMiddle[mark]=strMiddle[mark]+"/1";
	//处理完括号内的结果重新连成新的字符串
	str="";
	for(int i=0;i<counts;i++)
		str=str+strMiddle[i];
	
	//重来一次
	{
	st=new StringTokenizer(str,"*/+-.()",true);
	counts=st.countTokens();
	strMiddle=new String[counts];
	for(int i=0;i<counts;i++){
		strMiddle[i]=st.nextToken();
		if(strMiddle[i].indexOf('(')!=-1)
			mark=i;
	}	
	}
	
	str="";
	//括号前除号处理以及相应字符串连接
	if(mark-1>0&&strMiddle[mark-1].charAt(0)=='/'&&strMiddle[mark+1].charAt(0)=='-'){
		strMiddle[mark-1]="*-";
		String temp=strMiddle[mark+4];
		strMiddle[mark+4]=strMiddle[mark+2];
		strMiddle[mark+2]=temp;
		
		for(int i=0;i<mark;i++)
			str=str+strMiddle[i];
		str=str+strMiddle[mark+2]+strMiddle[mark+3]+strMiddle[mark+4];
		for(int i=mark+6;i<counts;i++)
			str=str+strMiddle[i];
	}
	else if(mark-1>0&&strMiddle[mark-1].charAt(0)=='/'){
		strMiddle[mark-1]="*";
		String temp=strMiddle[mark+3];
		strMiddle[mark+3]=strMiddle[mark+1];
		strMiddle[mark+1]=temp;
		
		for(int i=0;i<mark;i++)
			str=str+strMiddle[i];
		str=str+strMiddle[mark+1]+strMiddle[mark+2]+strMiddle[mark+3];
		for(int i=mark+5;i<counts;i++)
			str=str+strMiddle[i];
	}
	else if(strMiddle[mark+1].charAt(0)=='-'){
		for(int i=0;i<mark;i++)
			str=str+strMiddle[i];
		str=str+strMiddle[mark+1]+strMiddle[mark+2]+strMiddle[mark+3]+strMiddle[mark+4];
		for(int i=mark+6;i<counts;i++)
			str=str+strMiddle[i];
	}
	else{
		for(int i=0;i<mark;i++)
			str=str+strMiddle[i];
		str=str+strMiddle[mark+1]+strMiddle[mark+2]+strMiddle[mark+3];
		for(int i=mark+5;i<counts;i++)
			str=str+strMiddle[i];
	}

	System.out.println("一轮去括号后："+str);       
}
return str;
}


public static String unit(String str) {
	
String strMiddle[];
while(str.indexOf('-',1)!=-1||str.indexOf('+',1)!=-1||str.indexOf('*')!=-1||str.indexOf('/')!=-1){
	int mark=0,counts = 0;
	
	//括号处理
	if(str.indexOf('(')!=-1&&str.indexOf(')')!=-1)
		str=priority(str);
	
	str=Recline.transform(str);
    
    //小数点处理
	if(str.indexOf('.')!=-1)
		str=Recline.checkDot(str);
	
    //乘法与连除的处理
	if(str.indexOf('*')!=-1||str.indexOf('/')!=str.lastIndexOf('/'))
			str=mulDiv(str);
	
    if(str.indexOf('-',1)==-1&&str.indexOf('+',1)==-1)
    	break;
   
    // 将整数化成分数
    {
    st=new StringTokenizer(str,"+-",true);
    counts=st.countTokens();
    strMiddle=new String[counts];
    for(int i=0;i<counts;i++)
    	strMiddle[i]=st.nextToken();
    for(int i=counts-1;i>0;i--)
    	if(strMiddle[i].charAt(0)=='+'||strMiddle[i].charAt(0)=='-')
    		mark=i;
    if(strMiddle[mark-1].indexOf('/')==-1)
    	strMiddle[mark-1]=strMiddle[mark-1]+"/1";
    if(strMiddle[mark+1].indexOf('/')==-1)
    	strMiddle[mark+1]=strMiddle[mark+1]+"/1";
    str="";
    for(int i=0;i<counts;i++)
    	str=str+strMiddle[i];
    }

    st=new StringTokenizer(str,"+-/",true);
    counts=st.countTokens();
    strMiddle=new String[counts];
    for(int i=0;i<counts;i++)
    	strMiddle[i]=st.nextToken();
    for(int i=counts-1;i>0;i--)
    	if(strMiddle[i].charAt(0)=='+'||strMiddle[i].charAt(0)=='-')
    		mark=i;

    //标记f1、f2的分子分母和计算方法
    if(str.charAt(0)=='-')
    	f1=new Fraction("-"+strMiddle[mark-3],strMiddle[mark-1]);
    else
    	f1=new Fraction(strMiddle[mark-3],strMiddle[mark-1]);
    
    f2=new Fraction(strMiddle[mark+1],strMiddle[mark+3]);
    way=strMiddle[mark].charAt(0);
    
    //运算
    str=Basis(way,f1,f2);
    //连接后面的区域
    for(int i=mark+4;i<counts;i++)
    	str=str+strMiddle[i];
}
return str;
}


private static String mulDiv(String str) {

 String strMiddle[];
 while(str.indexOf('*')!=-1||str.indexOf('/')!=-1){
	/*
	 * bingo:标记连除情况
	 * out:记录跳出循环
	 * zmark1,zmark2:f1与f2在是否连除时的位置标记
	 */ 
	boolean bingo=false,out=true; 
	int mark=0,counts = 0,zmark1=0,zmark2=0;

    //将乘法两边整数化成分数
    if(str.indexOf('*')!=-1){
		{
			st=new StringTokenizer(str,"*+-",true);
			counts=st.countTokens();
			strMiddle=new String[counts];
			for(int i=0;i<counts;i++)
				strMiddle[i]=st.nextToken();
		}

      for(int i=counts-1;i>=0;i--){
	    	  if(strMiddle[i].charAt(0)=='*')
	    		mark=i;
	  }
      if(strMiddle[mark-1].indexOf('/')==-1)
    	  strMiddle[mark-1]=strMiddle[mark-1]+"/1";
      if(strMiddle[mark+1].indexOf('/')==-1)
    	  strMiddle[mark+1]=strMiddle[mark+1]+"/1";
      str="";
      for(int i=0;i<counts;i++)
    	  str=str+strMiddle[i];
    }

    {
    st=new StringTokenizer(str,"+-*/",true);
	counts=st.countTokens();
	strMiddle=new String[counts];
	for(int i=0;i<counts;i++)
		strMiddle[i]=st.nextToken();
    }
    
	for(int i=counts-1;i>=0;i--){
		if(strMiddle[i].charAt(0)=='*'){
			mark=i;
		    out=false;
		}
		else if(i>1&&strMiddle[i].charAt(0)=='/'&&strMiddle[i-2].charAt(0)=='/'){
			mark=i;
		    bingo=true;
		    out=false;
		}
	}
	//无乘和连除即跳出
	if(out)
		break;
	
	//标记f1,f2
	if(bingo==false){
		zmark1=mark-2;
		zmark2=mark+2;
    	f2=new Fraction(strMiddle[zmark2-1],strMiddle[zmark2+1]);
	}else {
		zmark1=mark-2;
	 	f2=new Fraction(strMiddle[mark+1],"1");
	}
  
	f1=new Fraction(strMiddle[zmark1-1],strMiddle[zmark1+1]);
    way=strMiddle[mark].charAt(0);
    
    str="";
    //连接前面的字符串
    for(int i=0;i<mark-3;i++)
    	str=str+strMiddle[i];
    //运算
    try {
    	str=str+Basis(way,f1,f2);
    }catch(ArithmeticException e){
    	str="NaN";break;
    }
    //连接后面的字符串
    if(bingo==false)
    	for(int i=mark+4;i<counts;i++)
    		str=str+strMiddle[i];
    else 
    	for(int i=mark+2;i<counts;i++)
    		str=str+strMiddle[i];
   }
return str;
	
}
   public static void main(String args[]) {
	   System.out.print(unit("8/4"));
   }

}
