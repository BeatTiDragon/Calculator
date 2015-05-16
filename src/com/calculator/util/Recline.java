package com.calculator.util;

import java.math.BigInteger;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import com.calculator.algorithm.Decimal;
import com.calculator.algorithm.Fraction;
import com.calculator.dto.CalculDto;
import com.calculator.string.BasicString;
import com.calculator.ui.MainFrame;

public class Recline {
	
private static StringTokenizer st;
/**
 * 最基础的方法，控制输入输出以及分数小数运算
 * @param str
 */
public static String foundation (String str,CalculDto dto){
	
	String formula=str;
	
	System.out.println("\n"+"你输入的式子是："+"\n\n"+formula+"\n\n"+"计算过程：");
	
	//删除多余的操作符
	if(str.charAt(str.length()-1) == '+' || str.charAt(str.length()-1) == '-'
	  || str.charAt(str.length()-1) == '*' || str.charAt(str.length()-1) == '/'
	  || str.charAt(str.length()-1) == '^')
		str = str.substring(0, str.length()-1);
	
	//补齐缺少的括号和乘号
	dto.setTip(Recline.checkTip(str));
	if(dto.getTip()!=0){
		for(int i=dto.getTip();i>0;i--)
			str=str+')';
		dto.setTip(0);
	}
	str=Recline.checkStar(str);

    //一切皆无穷
    if(str.indexOf("-∞")!=-1)
    	str="-∞";
    else if(str.indexOf('∞')!=-1)
    	str="∞";
    else if(str.indexOf("NaN")!=-1)
    	str="NaN";

    //分数形式计算还是小数形式计算
    try{
    if(str.indexOf('^')==-1&&str.indexOf('!')==-1&&str.indexOf('√')==-1&&str.indexOf('π')==-1&&str.indexOf('E')==-1){
    	str=Fraction.unit(str);
        if(str.equals("")==false&&str.charAt(0)=='+')
    		str=str.substring(1,str.length());
    	dto.setFraction(str);
    	System.out.println("PS:你可以尝试点击“X/Y”按钮将其转化为分数形式");
    	str=Decimal.mulDiv(str,dto);
    }
    else {
    	str=Decimal.unit(str,dto);
    	dto.setFraction("");
    }
 	}catch(ArrayIndexOutOfBoundsException e){
		JOptionPane.showMessageDialog(null,BasicString.ERROR+" :\n"+e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
	}catch(IllegalArgumentException e){
 		JOptionPane.showMessageDialog(null,BasicString.ERROR+" :\n"+e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    }
    
	//输出结果处理
	if(str.length()>1&&str.charAt(str.length()-2)=='.'&&str.charAt(str.length()-1)=='0')
		str=String.valueOf(Double.parseDouble(str)).substring(0, str.length()-2);
	if(str.equals("")==false&&str.charAt(0)=='+')
		str=str.substring(1,str.length());
	if(str.indexOf("Infinity")!=-1)
		str="∞";
	
	//正确运行的式子才记录历史
	if(formula.equals(str)==false){
	    //记录历史
	    for(int i = dto.getRecord().length-2; i >= 0;i--){
	    	dto.getRecord(i+1).setRecord(dto.getRecord(i).getFormula(), dto.getRecord(i).getResult());
	    	if(i==0)
	    		dto.getRecord(i).setRecord(MainFrame.text.getText(), str);
	    }
	
	   //更新历史
        for(int i = 0;i < MainFrame.jmi_edit_history.length;i++){
        	if(i%2 != 0)
        		MainFrame.jmi_edit_history[i].setText("="+dto.getRecord(i/2).getResult());
    	    else
    	    	MainFrame.jmi_edit_history[i].setText(dto.getRecord(i/2).getFormula());
	    
	    }
	}
	dto.setDecimal(str);
	return dto.getDecimal();

}

/**
 * 检测是否漏了"*"号，漏了插入补齐
 */
public static  String checkStar(String str){
	//去除首位加号
	if(str.equals("")==false&&str.charAt(0)=='+')
		str=str.substring(1,str.length());
	
	StringBuffer buffer=new StringBuffer(str);

	for(int i=1;i<buffer.length();i++){
		if(buffer.charAt(i)=='('&&")!π0123456789".indexOf(buffer.charAt(i-1))!=-1)
			buffer=buffer.insert(i, '*');
		else if(buffer.charAt(i-1)==')'&&"π0123456789".indexOf(buffer.charAt(i))!=-1)
			buffer=buffer.insert(i, '*');
		else if(buffer.charAt(i-1)=='π'&&"0123456789".indexOf(buffer.charAt(i))!=-1)
			buffer=buffer.insert(i, '*');
		else if(buffer.charAt(i)=='π'&&"!π0123456789".indexOf(buffer.charAt(i-1))!=-1)
			buffer=buffer.insert(i, '*');
		else if(buffer.charAt(i-1)=='!'&&"0123456789".indexOf(buffer.charAt(i))!=-1)
			buffer=buffer.insert(i, '*');
		else if(buffer.charAt(i)=='√'&&".)!π0123456789".indexOf(buffer.charAt(i-1))!=-1)
			buffer=buffer.insert(i, '*');
  }
	str=new String(buffer);
	return str;
	
}


/**
 * 小数转化为分数
 */
public static String checkDot(String str){
    //首尾小数缺零补零
    if(str.charAt(0)=='.')
    	str="0"+str;
    if(str.charAt(str.length()-1)=='.')
    	str=str+"0";

    //小数补齐0，例如“。3”补成“0.3”，“2.”补成“2.0”
    StringBuffer buffer=new StringBuffer(str);
    for(int i=1;i<buffer.length()-1;i++){
    	if(buffer.charAt(i)=='.'&&"0123456789".indexOf(buffer.charAt(i+1))==-1)
    		buffer=buffer.insert(i+1, 0);
        if(buffer.charAt(i)=='.'&&"0123456789".indexOf(buffer.charAt(i-1))==-1)
        	buffer=buffer.insert(i, 0);
    }
    str=new String(buffer);

//循环将小数转化为分数
while(str.indexOf('.')!=-1){
    int cake= 0,sugar=0;
    //检测多小数点的错误输入情况
    st=new StringTokenizer(str,"*/+-",true);
    int counts=st.countTokens();
    for(int i=0;i<counts;i++){
    	String temp=st.nextToken();
    	if(temp.indexOf('.')!=temp.lastIndexOf('.'))
    		throw new ArrayIndexOutOfBoundsException("multiple points");
        
    }
    
    st = new StringTokenizer(str,"+-*/.",true);
    counts = st.countTokens();
    String[]strMiddle = new String[counts];
    for(int i = 0;i < counts;i++)
    	strMiddle[i] = st.nextToken();
    
    for(int i = counts-1;i >= 0;i--){
    	if(strMiddle[i].charAt(0) == '.')
    		sugar = i;
    	if(i-2>0 && strMiddle[i].charAt(0)=='.' && strMiddle[i-2].charAt(0)=='/')
    		cake = i-2;
    }
    
    str="";
    if(strMiddle[cake].charAt(0)=='/'&&strMiddle[cake+2].charAt(0)=='.'){
    	//解决两个小数连除问题
	    strMiddle[cake-1] = strMiddle[cake-1] + "*" + BigInteger.TEN.pow(strMiddle[cake+3].length());
	    strMiddle[cake] = "/" + strMiddle[cake+1] + strMiddle[cake+3];
	    
	    for(int i=0;i<cake;i++)
	    	str = str + strMiddle[i];
	    str = str + strMiddle[cake];
	    for(int i = cake + 4;i < counts;i++)
	    	str = str + strMiddle[i];
    }
    else {
    	for(int i=0;i<sugar-1;i++)
    		str=str+strMiddle[i];
        str = str + strMiddle[sugar-1] + strMiddle[sugar+1] + "/" + BigInteger.TEN.pow(strMiddle[sugar+1].length());
        for(int i = sugar+2;i < counts;i++)
        	str = str + strMiddle[i];
    }
}
return str;	
}

/**
 * 检测左右括号数是否配对
 * @param str
 * @return 剩余括号匹配数
 */
public static int checkTip(String str){
	   int left =0,right = 0;
       if(str.indexOf("(") != -1){
    	  
	       for(int i = 0;i < str.length();i++){
	    	   if(str.charAt(i) == '(')
	    		   left++;
	    	   else if(str.charAt(i) == ')')
	    		   right++;
	       }
      
        }
       return Math.abs(left-right);
}


/**
 * 叠合运算符转化
 */
public static String transform(String str){
int counts,sign = 0;
String[]strMiddle;
//处理“*-”和“/-”的问题
while(str.indexOf("*-")!=-1||str.indexOf("/-")!=-1){
	{	
    st=new StringTokenizer(str,"+-",true);
    counts=st.countTokens();
    strMiddle=new String[counts];
    for(int i=0;i<counts;i++){
    	strMiddle[i]=st.nextToken();
    	if(strMiddle[i].charAt(strMiddle[i].length()-1)=='*'||strMiddle[i].charAt(strMiddle[i].length()-1)=='/')
		sign=i;
    }
    }
    String temp=strMiddle[sign];
    strMiddle[sign]=strMiddle[sign+1];
    strMiddle[sign+1]=temp;
    str="";
    for(int i=0;i<counts;i++)
    	str=str+strMiddle[i];
 }
//循环处理“--”和“+-”和“-+”和“++”问题，直到全部处理完
while(str.indexOf("+-")!=-1||str.indexOf("--")!=-1||str.indexOf("-+")!=-1||str.indexOf("++")!=-1){
	{
	st=new StringTokenizer(str,"+-",true);
	counts=st.countTokens();
	strMiddle=new String[counts];
	for(int i=0;i<counts;i++)
		strMiddle[i]=st.nextToken();
	}
    for(int i=0;i<counts-1;i++){
    	if((strMiddle[i].charAt(0)=='+'||strMiddle[i].charAt(0)=='-')
    			&&(strMiddle[i+1].charAt(0)=='+'||strMiddle[i+1].charAt(0)=='-'))
    		sign=i;
    }
	if((strMiddle[sign].charAt(0)=='+'&&strMiddle[sign+1].charAt(0)=='-')
	 ||(strMiddle[sign].charAt(0)=='-'&&strMiddle[sign+1].charAt(0)=='+')){
		strMiddle[sign]="-";
		for(int i=sign+1;i<counts-1;i++)
			strMiddle[i]=strMiddle[i+1];
	}
	else if((strMiddle[sign].charAt(0)=='-'&&strMiddle[sign+1].charAt(0)=='-')
	      ||(strMiddle[sign].charAt(0)=='+'&&strMiddle[sign+1].charAt(0)=='+')){
		  strMiddle[sign]="+";
		  for(int i=sign+1;i<counts-1;i++)
			  strMiddle[i]=strMiddle[i+1];
	}
    str="";
	for(int i=0;i<counts-1;i++)
		str=str+strMiddle[i];
 }
return str;

}
/**
 * 判断是否为纯数字
 * @param str
 */
public static boolean isNumeric(String str){
    try {
        Double.parseDouble(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

}
