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
		 case '��':  result = new BigDecimal(Math.sqrt(Double.parseDouble(x)));
	                break;
		 case '^':  { 
			         if (y.indexOf('.') == -1 && y.indexOf('-') == -1) {
				         if (Long.parseLong(y) > 30000)
				        	  return "��";
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
			        	  return "��";
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
/*ѭ����û���ż�����*/
while(str.indexOf('(')!=-1&&str.indexOf(')')!=-1){
	
	st=new StringTokenizer(str,"()",true);
	counts=st.countTokens();
	strMiddle=new String[counts];
	
	//mark��ס���һ������������һλ���
	for(int i=0;i<counts;i++){
		strMiddle[i]=st.nextToken();
		if(strMiddle[i].indexOf('(')!=-1)
			mark=i+1;
	}
	
	//�����ڵ�������
	String baby=unit(strMiddle[mark],dto);
	
	//������
	if(mark!=counts-2&&strMiddle[mark+2].charAt(0)=='^'){
	    st=new StringTokenizer(strMiddle[mark+2],"+-*/^!��",true);
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
	/*heroǰ���ʽ��ȫ������*/
	for(int i=0;i<mark-1;i++)
		str=str+strMiddle[i];
	
    str=str+baby;
    
	//���Ӻ�����ַ���
	for(int i=mark+2;i<counts;i++)
		str=str+strMiddle[i];
	
   System.out.println("һ��ȥ���ź�"+str);
}
return str;
}

/**
*@param mark ��ǲ������㷽ʽ�������±����
*@param counts ��¼StringTokenizer��������Ʊ����
*@param strMiddle �ݴ�ÿһ�����Ƶ��ַ�������
*@param emark1,emark2  �ֱ��¼��һλ�͵ڶ�λE-����ѧ������
*@param bingo ��¼�Ƿ��¼��emark1���߼��жϱ���
*/
public static String unit(String str,CalculDto dto) {

while(str.indexOf('+')!=-1||(str.length()>1&&str.indexOf('-',1)!=-1)||str.indexOf('*')!=-1
    		||str.indexOf('/')!=-1||str.indexOf('^')!=-1||str.indexOf('!')!=-1||str.indexOf('��')!=-1){

    boolean bingo=false;
    int mark= 0,counts,emark1 = 0,emark2 = 0;
    String strMiddle[];
    
	if(str.indexOf('(')!=-1&&str.indexOf(')')!=-1)
		str=Recline.transform(unit(priority(str,dto),dto));
	
    if(str.indexOf('*')!=-1||str.indexOf('/')!=-1||str.indexOf('^')!=-1||str.indexOf('!')!=-1||str.indexOf('��')!=-1)
    	str=mulDiv(str,dto);

	str=Recline.transform(str);

    {
	st=new StringTokenizer(str,"+-",true);
	counts=st.countTokens();
	strMiddle=new String[counts];
	for(int i=0;i<counts;i++)
		strMiddle[i]=st.nextToken();
    }
    
    //��ǲ������㷽ʽ�������±������+����-��,��Ϊemark1��emark2������ѧ��������ˮ��
	for(int i=counts-1;i>0;i--)
		if((strMiddle[i].charAt(0)=='+'||strMiddle[i].charAt(0)=='-')&&strMiddle[i-1].charAt(strMiddle[i-1].length()-1)!='E')
			mark=i;
	
	//�޲���ʱ����
    if((str.indexOf('+',1)==-1&&str.indexOf('-',1)==-1)
			||(str.indexOf("E-")!=-1&&mark==0))
		break;
    
	//�������E-���Ŀ�ѧ����������
	if(str.indexOf("E-")!=-1){
		for(int i=0;i<mark;i++)
			if(strMiddle[i].charAt(strMiddle[i].length()-1)=='E'&&strMiddle[i+1].charAt(0)=='-'){
			emark1=i;bingo=true;
			}
		for(int i=counts-1;i>=mark;i--)
			if(strMiddle[i].charAt(strMiddle[i].length()-1)=='E'&&strMiddle[i+1].charAt(0)=='-')
			emark2=i;
	}

	//�ҳ���һ�������͵ڶ��������ͼ��㷽ʽ
    if(str.charAt(0) == '-'){
		if(bingo)
			one = "-"+strMiddle[emark1] + "-" + strMiddle[emark1+2];
		else if(str.charAt(1) == '��')
    		one = "-" + Math.PI;
    	else 
    		one = "-" + strMiddle[mark-1];
    }
    else if(bingo)
    	one = strMiddle[emark1] + "-" + strMiddle[emark1+2];
	else if(str.charAt(0) == '��')
		one = Math.PI + "";
	else 
		one = strMiddle[mark-1];

    if(emark2 != 0)
    	two = strMiddle[emark2] + "-" + strMiddle[emark2+2];
    else if(strMiddle[mark+1].charAt(0) == '��')
    	two = Math.PI+"";
    else 
    	two = strMiddle[mark+1];
    
	way = strMiddle[mark].charAt(0);

    //��������
    str = Basis(way,one,two,dto);
    
    //���Ӻ�����ַ���
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
while(str.indexOf('*')!=-1||str.indexOf('/')!=-1||str.indexOf('^')!=-1||str.indexOf('!')!=-1||str.indexOf('��')!=-1){
	{
	st=new StringTokenizer(str,"+-*/^!��",true);	
	counts=st.countTokens();
	strMiddle=new String[counts];
	for(int i=0;i<counts;i++)
		strMiddle[i]=st.nextToken();
	}
	
	//ѡ���Ǽ��㷽ʽ
   if(str.indexOf('!') != -1||str.indexOf('��') != -1){
		for(int i = counts - 1; i >= 0; i--)
			if(strMiddle[i].charAt(0) == '!'||strMiddle[i].charAt(0) == '��')
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
	//ѡ��one��two������������way�ļ��㷽ʽ
	
		way = strMiddle[mark].charAt(0);
		
		if(way!='��'){
			if(mark>=3&&strMiddle[mark-3].charAt(strMiddle[mark-3].length()-1)=='E'&&strMiddle[mark-2].charAt(0)=='-')
				one = strMiddle[mark-3] + "-" + strMiddle[mark-1];
			else if(strMiddle[mark-1].charAt(0)=='��')
				one = Math.PI+"";
			else 
				one = strMiddle[mark-1];
		}
	    if(way!='!'){ 
	    	if(counts-mark>3&&strMiddle[mark+1].charAt(strMiddle[mark+1].length()-1)=='E'&&strMiddle[mark+2].charAt(0)=='-')
	    		two = strMiddle[mark + 1] + "-" + strMiddle[mark + 3];
	    	else if(strMiddle[mark].charAt(0)=='^'&&strMiddle[mark+1].charAt(0)=='-')
	    		two = "-" + strMiddle[mark+2];
	    	else if(strMiddle[mark + 1].charAt(0)=='��')
	    		two = Math.PI + "";
	    	else 
	    		two = strMiddle[mark + 1];
	    }
    
    
	str="";
    //����ǰ����ַ���
    if(mark >= 3 && strMiddle[mark - 3].charAt(strMiddle[mark - 3].length() - 1) == 'E'
    		&& strMiddle[mark - 2].charAt(0) == '-')
    	for(int i=0;i<mark-3;i++)
    		str=str+strMiddle[i];
    else if(way=='��')
    	for(int i=0;i<mark;i++)
    		str=str+strMiddle[i];
    else 
    	for(int i=0;i<mark-1;i++)
    		str=str+strMiddle[i];

    //����
    try{
    	//��Ŀ�����Լ����Ӻ����ַ���
	    if(way=='!'){
	    	if(one.indexOf('.') != -1)
	    		throw new IllegalArgumentException(BasicString.FACTORIALERROR + "\n" + "For input string: " + one);
	    	str = str + Basis(way,one,"0",dto);
	    	for(int i=mark+1;i<counts;i++)
	    			str=str+strMiddle[i];
	    }
	    else if(way=='��'){
	    	str = str + Basis(way,two,"0",dto);
	    	for(int i=mark+2;i<counts;i++)
	    		str=str+strMiddle[i];
	    }
	    //˫Ŀ��������Ӻ����ַ����Ĳ���
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
    		str = "-��";
    	else if(one.equals("0") == false&&two.equals("0"))
    		str = "��";
    	else if(e.getMessage().equals("Overflow"))
    		str = "���";
    	else
    		str = "NaN";
    	break;
    }

    
    //����ת��
	if(str.indexOf("Infinity")!=-1){
		str="��";break;
	}
	System.out.println(str);
}
return str;
}

	
	
	
}
