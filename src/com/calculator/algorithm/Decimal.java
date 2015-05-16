package com.calculator.algorithm;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.math.RoundingMode;

import com.calculator.dto.CalculDto;
import com.calculator.string.BasicString;
import com.calculator.util.Recline;

public class Decimal {

private static StringTokenizer st;
private static String one,two;
private static char way;


private static String Basis(char util,String x,String y,CalculDto dto) {
	
	BigDecimal b1 = new BigDecimal(x); 
	BigDecimal b2 = new BigDecimal(y);
    BigDecimal result = null;
	DecimalFormat df = new DecimalFormat("0.###############E0");
	df.setRoundingMode(RoundingMode.HALF_UP);
	
    switch(util) {
    
	     case '+':  result = b1.add(b2);	  
	                break;   
	     case '-':  result = b1.subtract(b2); 
	                break;  
	     case '*':  result = b1.multiply(b2);
	                break;
		 case '/':  result = b1.divide(b2,20,BigDecimal.ROUND_HALF_UP);
	                break;
		 case '√':  result = new BigDecimal(Math.sqrt(Double.parseDouble(x)));
	                break;
		 case '^':  { 
			         if (y.indexOf('.') == -1 && y.indexOf('-') == -1) {
				         if (Long.parseLong(y) > 30000)
				        	  return "∞";
			        	 result = BigDecimal.ONE;
				         for(double i = Double.parseDouble(y); i > 0; i--)
				        	 result = result.multiply(new BigDecimal(x + ""));
			         }
			         else {
			        	 try{
			        	 result = new BigDecimal(Math.pow(Double.parseDouble(x),Double.parseDouble(y))); 
			        	 } catch (NumberFormatException e){
			        		  return "Infinity";
			        	 }
			        	  
			         }            
		 }
		             break;
		 case '!':  { 
			          if (Long.parseLong(x) > 30000)
			        	  return "∞";
			          result = BigDecimal.ONE;
			          for(long i = 1; i <= Long.parseLong(x); i++)
			        	  result = result.multiply(new BigDecimal(i + ""));
		 }        
    }

    if(String.valueOf(result.stripTrailingZeros()).length() > 25)
    	return df.format(result);
    else
    	return String.valueOf(result.doubleValue());

}


private static  String priority(String str,CalculDto dto){
	
int mark = 0,counts;
String strMiddle[];
/*循环到没括号即跳出*/
while(str.indexOf('(')!=-1&&str.indexOf(')')!=-1){
	
	st=new StringTokenizer(str,"()",true);
	counts=st.countTokens();
	strMiddle=new String[counts];
	
	//mark记住最后一个“（”的下一位序号
	for(int i=0;i<counts;i++){
		strMiddle[i]=st.nextToken();
		if(strMiddle[i].indexOf('(')!=-1)
			mark=i+1;
	}
	
	//括号内的运算结果
	String baby=unit(strMiddle[mark],dto);
	
	//处理幂
	if(mark!=counts-2&&strMiddle[mark+2].charAt(0)=='^'){
	    st=new StringTokenizer(strMiddle[mark+2],"+-*/^!√",true);
	    int box=st.countTokens();
	    String []strNew=new String[box];
	    for(int i=0;i<box;i++)
	    	strNew[i]=st.nextToken();
	    if(strMiddle[mark+2].charAt(1)=='-'){
	    	baby=Basis('^',baby,"-"+strNew[2],dto)+"";
		    strMiddle[mark+2]="";
		    for(int i=3;i<box;i++)
		    	strMiddle[mark+2]=strMiddle[mark+2]+strNew[i];
	    }else{
	    	baby=Basis('^',baby,strNew[1],dto)+"";
		    strMiddle[mark+2]="";
	        for(int i=2;i<box;i++)
		    strMiddle[mark+2]=strMiddle[mark+2]+strNew[i];
	    }
	}
	
	str="";
	/*hero前面的式子全部连接*/
	for(int i=0;i<mark-1;i++)
		str=str+strMiddle[i];
	
    str=str+baby;
    
	//连接后面的字符串
	for(int i=mark+2;i<counts;i++)
		str=str+strMiddle[i];
	
   System.out.println("一轮去括号后："+str);
}
return str;
}

/**
*@param mark 标记操作计算方式的数组下标变量
*@param counts 记录StringTokenizer对象的令牌标记数
*@param strMiddle 暂存每一个令牌的字符串数组
*@param emark1,emark2  分别记录第一位和第二位E-级科学计数数
*@param bingo 记录是否记录有emark1的逻辑判断变量
*/
public static String unit(String str,CalculDto dto) {

while(str.indexOf('+')!=-1||(str.length()>1&&str.indexOf('-',1)!=-1)||str.indexOf('*')!=-1
    		||str.indexOf('/')!=-1||str.indexOf('^')!=-1||str.indexOf('!')!=-1||str.indexOf('√')!=-1){

    boolean bingo=false;
    int mark= 0,counts,emark1 = 0,emark2 = 0;
    String strMiddle[];
    
	if(str.indexOf('(')!=-1&&str.indexOf(')')!=-1)
		str=Recline.transform(unit(priority(str,dto),dto));
	
    if(str.indexOf('*')!=-1||str.indexOf('/')!=-1||str.indexOf('^')!=-1||str.indexOf('!')!=-1||str.indexOf('√')!=-1)
    	str=mulDiv(str,dto);

	str=Recline.transform(str);

    {
	st=new StringTokenizer(str,"+-",true);
	counts=st.countTokens();
	strMiddle=new String[counts];
	for(int i=0;i<counts;i++)
		strMiddle[i]=st.nextToken();
    }
    
    //标记操作计算方式的数组下标变量“+”或“-”,作为emark1与emark2两个科学计数数分水岭
	for(int i=counts-1;i>0;i--)
		if((strMiddle[i].charAt(0)=='+'||strMiddle[i].charAt(0)=='-')&&strMiddle[i-1].charAt(strMiddle[i-1].length()-1)!='E')
			mark=i;
	
	//无操作时跳出
    if((str.indexOf('+',1)==-1&&str.indexOf('-',1)==-1)
			||(str.indexOf("E-")!=-1&&mark==0))
		break;
    
	//标出带有E-级的科学计数法的数
	if(str.indexOf("E-")!=-1){
		for(int i=0;i<mark;i++)
			if(strMiddle[i].charAt(strMiddle[i].length()-1)=='E'&&strMiddle[i+1].charAt(0)=='-'){
			emark1=i;bingo=true;
			}
		for(int i=counts-1;i>=mark;i--)
			if(strMiddle[i].charAt(strMiddle[i].length()-1)=='E'&&strMiddle[i+1].charAt(0)=='-')
			emark2=i;
	}

	//找出第一个参数和第二个参数和计算方式
    if(str.charAt(0) == '-'){
		if(bingo)
			one = "-"+strMiddle[emark1] + "-" + strMiddle[emark1+2];
		else if(str.charAt(1) == 'π')
    		one = "-" + Math.PI;
    	else 
    		one = "-" + strMiddle[mark-1];
    }
    else if(bingo)
    	one = strMiddle[emark1] + "-" + strMiddle[emark1+2];
	else if(str.charAt(0) == 'π')
		one = Math.PI + "";
	else 
		one = strMiddle[mark-1];

    if(emark2 != 0)
    	two = strMiddle[emark2] + "-" + strMiddle[emark2+2];
    else if(strMiddle[mark+1].charAt(0) == 'π')
    	two = Math.PI+"";
    else 
    	two = strMiddle[mark+1];
    
	way = strMiddle[mark].charAt(0);

    //基本运算
    str = Basis(way,one,two,dto);
    
    //连接后面的字符串
    if(emark2 != 0)
    	for(int i = mark + 4; i < counts; i++)
    		str = str + strMiddle[i];
    else 
    	for(int i = mark + 2; i < counts; i++)
    		str = str + strMiddle[i];
    
    System.out.println(str);
	   
}

	return str;
}

public static  String mulDiv(String str,CalculDto dto) {
int mark = 0,counts;
String strMiddle[];
while(str.indexOf('*')!=-1||str.indexOf('/')!=-1||str.indexOf('^')!=-1||str.indexOf('!')!=-1||str.indexOf('√')!=-1){
	{
	st=new StringTokenizer(str,"+-*/^!√",true);	
	counts=st.countTokens();
	strMiddle=new String[counts];
	for(int i=0;i<counts;i++)
		strMiddle[i]=st.nextToken();
	}
	
	//选择标记计算方式
   if(str.indexOf('!') != -1||str.indexOf('√') != -1){
		for(int i = counts - 1; i >= 0; i--)
			if(strMiddle[i].charAt(0) == '!'||strMiddle[i].charAt(0) == '√')
				mark = i;
	}
	else if(str.indexOf('^') != -1){
		for(int i = counts - 1; i >= 0; i--)
			if(strMiddle[i].charAt(0) == '^')
				mark = i;
	}
	else {
		for(int i = counts - 1; i >= 0; i--)
			if(strMiddle[i].charAt(0) == '*'||strMiddle[i].charAt(0) == '/')
				mark = i;
	}
  // TODO Auto-generated method stub 
	//选出one和two两个操作数和way的计算方式
	
		way = strMiddle[mark].charAt(0);
		
		if(way!='√'){
			if(mark>=3&&strMiddle[mark-3].charAt(strMiddle[mark-3].length()-1)=='E'&&strMiddle[mark-2].charAt(0)=='-')
				one = strMiddle[mark-3] + "-" + strMiddle[mark-1];
			else if(strMiddle[mark-1].charAt(0)=='π')
				one = Math.PI+"";
			else 
				one = strMiddle[mark-1];
		}
	    if(way!='!'){ 
	    	if(counts-mark>3&&strMiddle[mark+1].charAt(strMiddle[mark+1].length()-1)=='E'&&strMiddle[mark+2].charAt(0)=='-')
	    		two = strMiddle[mark + 1] + "-" + strMiddle[mark + 3];
	    	else if(strMiddle[mark].charAt(0)=='^'&&strMiddle[mark+1].charAt(0)=='-')
	    		two = "-" + strMiddle[mark+2];
	    	else if(strMiddle[mark + 1].charAt(0)=='π')
	    		two = Math.PI + "";
	    	else 
	    		two = strMiddle[mark + 1];
	    }
    
    
	str="";
    //连接前面的字符串
    if(mark >= 3 && strMiddle[mark - 3].charAt(strMiddle[mark - 3].length() - 1) == 'E'
    		&& strMiddle[mark - 2].charAt(0) == '-')
    	for(int i=0;i<mark-3;i++)
    		str=str+strMiddle[i];
    else if(way=='√')
    	for(int i=0;i<mark;i++)
    		str=str+strMiddle[i];
    else 
    	for(int i=0;i<mark-1;i++)
    		str=str+strMiddle[i];

    //运算
    try{
    	//单目运算以及连接后面字符串
	    if(way=='!'){
	    	if(one.indexOf('.') != -1)
	    		throw new IllegalArgumentException(BasicString.FACTORIALERROR + "\n" + "For input string: " + one);
	    	str = str + Basis(way,one,"0",dto);
	    	for(int i=mark+1;i<counts;i++)
	    			str=str+strMiddle[i];
	    }
	    else if(way=='√'){
	    	str = str + Basis(way,two,"0",dto);
	    	for(int i=mark+2;i<counts;i++)
	    		str=str+strMiddle[i];
	    }
	    //双目运算和连接后面字符串的操作
	    else {
    		str=str+String.valueOf(Basis(way,one,two,dto));
    	    if(counts>=3&&way=='^'&&strMiddle[mark+1].charAt(0)=='-')
    	    	for(int i=mark+3;i<counts;i++)
    	    		str=str+strMiddle[i];
    	    else if(counts-mark>3&&strMiddle[mark+1].charAt(strMiddle[mark+1].length()-1)=='E'&&strMiddle[mark+2].charAt(0)=='-')
    	    	for(int i=mark+4;i<counts;i++)
    	    		str=str+strMiddle[i];
            else 
    	    	for(int i=mark+2;i<counts;i++)
    	    		str=str+strMiddle[i];
    	}
    }catch(ArithmeticException e){
    	if(mark > 1&&strMiddle[mark-2].charAt(0) == '-')
    		str = "-∞";
    	else if(one.equals("0") == false&&two.equals("0"))
    		str = "∞";
    	else if(e.getMessage().equals("Overflow"))
    		str = "溢出";
    	else
    		str = "NaN";
    	break;
    }

    
    //无穷转换
	if(str.indexOf("Infinity")!=-1){
		str="∞";break;
	}
	System.out.println(str);
}
return str;
}

	
	
	
}
