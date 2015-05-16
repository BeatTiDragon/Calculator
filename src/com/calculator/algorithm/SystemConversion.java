package com.calculator.algorithm;

import java.math.BigInteger;

/**
 * 支持二进制，八进制，十进制，十六进制间的任意精度整数转换
 * @设计类
 * @author .C
 */
public final class SystemConversion {
    /**
     * 指定进制字符串转换成十进制
     * 
     * @param  original  指定字符串
     *         
     * @param  fromType 指定进制
     *         
     * @throws  IllegalArgumentException {@code original}不符合{@code fromType}类型
     */
	public final static String toDecConverter(String original, SystemType fromType) {
	   	if (isLegal(original, fromType)) {
        	if (fromType == SystemType.BIN)
        		return binToDec(original);
        	else if (fromType == SystemType.OCT)
        		return octToDec(original);
        	else if (fromType == SystemType.HEX)
        		return hexToDec(original.toUpperCase());
        	else 
        		return original;	   		
	   	}
	   	else 
    	    throw new IllegalArgumentException("Is not " + fromType);			
	}
    /**
     * 指定进制字符串转换成二进制
     * 
     * @param  original  指定字符串
     *         
     * @param  fromType 指定进制
     *         
     * @throws  IllegalArgumentException {@code original}不符合{@code fromType}类型
     */
	public final static String toBinConverter(String original, SystemType fromType) {
	   	if (isLegal(original, fromType)) {
        	if (fromType == SystemType.DEC)
        		return decToBin(original);
        	else if (fromType == SystemType.OCT)
        		return octToBin(original);
        	else if (fromType == SystemType.HEX)
        		return hexToBin(original.toUpperCase());
        	else
        		return original;   		
	   	}
	   	else 
    	    throw new IllegalArgumentException("Is not " + fromType);		
	}
    /**
     * 指定进制字符串转换成八进制
     * 
     * @param  original  指定字符串
     *         
     * @param  fromType 指定进制
     *         
     * @throws  IllegalArgumentException {@code original}不符合{@code fromType}类型
     */
	public final static String toOctConverter(String original, SystemType fromType) {
	   	if (isLegal(original, fromType)) {
        	if (fromType == SystemType.BIN)
        		return binToOct(original);
        	else if (fromType == SystemType.DEC)
        		return decToOct(original);
        	else if (fromType == SystemType.HEX)
        		return hexToOct(original.toUpperCase());
        	else 
        		return original;   		
	   	}
	   	else 
    	    throw new IllegalArgumentException("Is not " + fromType);		
	}
    /**
     * 指定进制字符串转换成十六进制
     * 
     * @param  original  指定字符串
     *         
     * @param  fromType 指定进制
     *         
     * @throws  IllegalArgumentException {@code original}不符合{@code fromType}类型
     */
	public final static String toHexConverter(String original, SystemType fromType) {
	   	if (isLegal(original, fromType)) {
        	if (fromType == SystemType.BIN)
        		return binToHex(original);
        	else if (fromType == SystemType.DEC)
        		return decToHex(original);
        	else if (fromType == SystemType.OCT)
        		return octToHex(original);
        	else
        		return original.toUpperCase();    		
	   	}
	   	else 
    	    throw new IllegalArgumentException("Is not " + fromType);		
	}
    /**
     * 十进制转换器
     * 
     * @param  original  指定字符串
     *         
     * @param  toType 指定进制
     *         
     * @throws  IllegalArgumentException {@code original}不符合十进制类型
     */
	public final static String decConverter(String original, SystemType toType) {
	   	if (isLegal(original, SystemType.DEC)) {
        	if (toType == SystemType.BIN)
        		return decToBin(original);
        	else if (toType == SystemType.OCT)
        		return decToOct(original);
        	else if (toType == SystemType.HEX)
        		return decToHex(original);
        	else 
        		return original;	   		
	   	}
	   	else 
    	    throw new IllegalArgumentException("Is not " + SystemType.DEC);	
	}
    /**
     * 二进制转换器
     * 
     * @param  original  指定字符串
     *         
     * @param  toType 指定进制
     *         
     * @throws  IllegalArgumentException {@code original}不符合二进制类型
     */
	public final static String binConverter(String original, SystemType toType) {
	   	if (isLegal(original, SystemType.BIN)) {
        	if (toType == SystemType.DEC)
        		return binToDec(original);
        	else if (toType == SystemType.OCT)
        		return binToOct(original);
        	else if (toType == SystemType.HEX)
        		return binToHex(original);
        	else
        		return original;   		
	   	}
	   	else 
    	    throw new IllegalArgumentException("Is not " + SystemType.BIN);		
	}
    /**
     * 八进制转换器
     * 
     * @param  original  指定字符串
     *         
     * @param  toType 指定进制
     *         
     * @throws  IllegalArgumentException {@code original}不符合八进制类型
     */
	public final static String octConverter(String original, SystemType toType) {
	   	if (isLegal(original, SystemType.OCT)) {
        	if (toType == SystemType.BIN)
        		return octToBin(original);
        	else if (toType == SystemType.DEC)
        		return octToDec(original);
        	else if (toType == SystemType.HEX)
        		return octToHex(original);
        	else 
        		return original;   		
	   	}
	   	else 
    	    throw new IllegalArgumentException("Is not " + SystemType.OCT);		
	}
    /**
     * 十六进制转换器
     * 
     * @param  original  指定字符串
     *         
     * @param  toType 指定进制
     *         
     * @throws  IllegalArgumentException {@code original}不符合十六进制类型
     */
	public final static String hexConverter(String original, SystemType toType) {
	   	if (isLegal(original, SystemType.HEX)) {
        	if (toType == SystemType.BIN)
        		return hexToBin(original.toUpperCase());
        	else if (toType == SystemType.DEC)
        		return hexToDec(original.toUpperCase());
        	else if (toType == SystemType.OCT)
        		return hexToOct(original.toUpperCase());
        	else
        		return original.toUpperCase();    		
	   	}
	   	else 
    	    throw new IllegalArgumentException("Is not " + SystemType.HEX);		
	}
    /**
     * 进制转换器
     * 
     * @param  original  指定字符串
     * 
     * @param  fromType  指定字符串进制
     *         
     * @param  toType 指定转换进制
     *         
     * @throws  IllegalArgumentException {@code original}不符合{@code fromType}类型
     */
    public final static String converter(String original, SystemType fromType, SystemType toType) {
    	if (isLegal(original, fromType)) {
	        if (fromType == SystemType.BIN) {
	        	if (toType == SystemType.DEC)
	        		return binToDec(original);
	        	else if (toType == SystemType.OCT)
	        		return binToOct(original);
	        	else if (toType == SystemType.HEX)
	        		return binToHex(original);
	        	else
	        		return original;
	        }
	        
	        else if (fromType == SystemType.DEC) {
	        	if (toType == SystemType.BIN)
	        		return decToBin(original);
	        	else if (toType == SystemType.OCT)
	        		return decToOct(original);
	        	else if (toType == SystemType.HEX)
	        		return decToHex(original);
	        	else 
	        		return original;
	        }
	        
	        else if (fromType == SystemType.OCT) {
	        	if (toType == SystemType.BIN)
	        		return octToBin(original);
	        	else if (toType == SystemType.DEC)
	        		return octToDec(original);
	        	else if (toType == SystemType.HEX)
	        		return octToHex(original);
	        	else 
	        		return original;     	
	        }
	        
	        else {
	        	if (toType == SystemType.BIN)
	        		return hexToBin(original.toUpperCase());
	        	else if (toType == SystemType.DEC)
	        		return hexToDec(original.toUpperCase());
	        	else if (toType == SystemType.OCT)
	        		return hexToOct(original.toUpperCase());
	        	else
	        		return original.toUpperCase();      	
	        }
        }
        else 
    	    throw new IllegalArgumentException("Is not " + fromType);
    	
    } 

    private static String binToDec(String str) {
    	BigInteger value = BigInteger.ZERO;
    	for (int i = 0; i < str.length(); i++) {
    		char c = str.charAt(i);
    		value = value.multiply(new BigInteger("2")).add(new BigInteger(c + ""));
    	}
		return value.toString();	
    }
    private static String binToOct(String str) {
    	String result = "";
    	if (str.length() % 3 == 1)
    		str = "00" + str;
    	else if (str.length() % 3 == 2) 
    		str = "0" + str;
    	for (int i = 0, j = 3; j <= str.length(); i += 3, j += 3) 
    		result = result + binToOctTable(str.substring(i, j));
		return result.replaceAll("^(0+)", "");
    }
    private static String binToHex(String str) {
    	String result = "";
    	if (str.length() % 4 == 1)
    		str = "000" + str;
    	else if (str.length() % 4 == 2) 
    		str = "00" + str;
    	else if (str.length() % 4 == 3) 
    		str = "0" + str;
    	for (int i = 0, j = 4; j <= str.length(); i += 4, j += 4) 
    		result = result + binToHexTable(str.substring(i, j));
		return result.replaceAll("^(0+)", "");
    }
    
    private static String octToBin(String str) {
    	String result = "";
    	for (int i = 0; i < str.length(); i++) 
    		result = result + octToBinTable(str.charAt(i) + "");
		return result.replaceAll("^(0+)", "");
    }
    private static String octToDec(String str) {
    	BigInteger value = BigInteger.ZERO;
    	for (int i = 0; i < str.length(); i++) {
    		char c = str.charAt(i);
    		value = value.multiply(new BigInteger("8")).add(new BigInteger(c + ""));
    	}
		return value.toString();	
    }
    private static String octToHex(String str) {
    	return binToHex(octToBin(str));
    }
    
    private static String decToBin(String str) {
    	BigInteger value = new BigInteger(str);
    	str = "";
    	while (!value.equals(BigInteger.ZERO)) {
    		str = value.remainder(new BigInteger("2")) + str;
    		value = value.divide(new BigInteger("2"));
    	}
		return str;	
    }  
    private static String decToOct(String str) {
    	BigInteger value = new BigInteger(str);
    	str = "";
    	while (!value.equals(BigInteger.ZERO)) {
    		str = value.remainder(new BigInteger("8")) + str;
    		value = value.divide(new BigInteger("8"));
    	}
		return str;	
    }
    private static String decToHex(String str) {
    	BigInteger value = new BigInteger(str);
    	str = "";
    	while (!value.equals(BigInteger.ZERO)) {
    		str = toHexChar(value.remainder(new BigInteger("16")).intValue()) + str;
    		value = value.divide(new BigInteger("16"));
    	}
		return str;	
    }

    private static String hexToBin(String str) {
    	String result = "";
    	for (int i = 0; i < str.length(); i++) 
    		result = result + hexToBinTable(str.charAt(i) + "");
		return result.replaceAll("^(0+)", "");
    }
    private static String hexToOct(String str) {
    	return binToOct(hexToBin(str));
    }
    private static String hexToDec(String str) {
    	BigInteger value = BigInteger.ZERO;
    	for (int i = 0; i < str.length(); i++) {
    		char c = str.charAt(i);
    		value = value.multiply(new BigInteger("16")).add(new BigInteger(hexCharToDec(c) + ""));
    	}
		return value.toString();	
    }
    
    /**采用二分法映射*/
    private static String binToOctTable(String str) {
    	int tip = str.compareTo("100");
       	if (tip == 0)
    		return "4";
       	else if (tip < 0) {
	    	if (str.equals("000"))
	    		return "0";
	    	else if (str.equals("001"))
	    		return "1";
	       	else if (str.equals("010"))
	    		return "2";
	       	else if (str.equals("011"))
	    		return "3";
       	}
       	else {
	       	if (str.equals("101"))
	    		return "5";
	       	else if (str.equals("110"))
	    		return "6";
	       	else if (str.equals("111"))
	    		return "7";
       	}
		return str;  	
    }
    
    private static String octToBinTable(String str) {
        switch (str.compareTo("4")) {
			case -4: return "000";
			case -3: return "001";
			case -2: return "010";
			case -1: return "011";
	        case  0: return "100";
			case  1: return "101";
			case  2: return "110";
			case  3: return "111";           
        }
        return str;
    }
    
    /**采用二分法映射*/
    private static String hexToBinTable(String str) {
    	if (str.compareTo("A") == 0)
    		return "1010";
    	else if (str.compareTo("A") < 0) {
    		switch (str.compareTo("4")) {
	    		case -4: return "0000";
	    		case -3: return "0001";
	    		case -2: return "0010";
	    		case -1: return "0011";
	    		case  0: return "0100";
	    		case  1: return "0101";
	    		case  2: return "0110";
	    		case  3: return "0111";
	    		case  4: return "1000";
	    		case  5: return "1001";
    		}
    	}
    	else {
    		switch (str.compareTo("C")) {
	    		case -1: return "1011";
	    		case  0: return "1100";
	    		case  1: return "1101";
	    		case  2: return "1110";
	    		case  3: return "1111";
    		}
    	}
		return str;
    }
    
    /**采用二分法映射*/
    private static String binToHexTable(String str) {
    	int tip = str.compareTo("0111");
    	if (tip == 0)
    		return "7";
    	else if (tip < 0) {
	    	if (str.equals("0000"))
	    		return "0";
	    	else if (str.equals("0001"))
	    		return "1";
	       	else if (str.equals("0010"))
	    		return "2";
	       	else if (str.equals("0011"))
	    		return "3";
	       	else if (str.equals("0100"))
	    		return "4";
	       	else if (str.equals("0101"))
	    		return "5";
	       	else if (str.equals("0110"))
	    		return "6";
    	}
    	else {
	       	if (str.equals("1000"))
	    		return "8";
	       	else if (str.equals("1001"))
	    		return "9";
	       	else if (str.equals("1010"))
	    		return "A";
	       	else if (str.equals("1011"))
	    		return "B";
	       	else if (str.equals("1100"))
	    		return "C";
	       	else if (str.equals("1101"))
	    		return "D";
	       	else if (str.equals("1110"))
	    		return "E";
	       	else if (str.equals("1111"))
	    		return "F";
    	}
		return str;  	
    }
    
    private static char toHexChar(int value) {
        if (value <= 9 && value >= 0) 
        	return (char)(value + '0');
        else 
        	return (char)(value - 10 + 'A');     
    }
    private static int hexCharToDec(char ch) {
    	if (ch >= 'A' && ch <= 'F')
    		return 10 + ch - 'A';
    	else
    		return ch - '0';
    }
  
    /**
     * 检测字符串是否是相应进制
     * 
     * @param  original  
     *         指定字符串
     *         
     * @param  type 
     *         指定进制
     * 
     * @return true表示{@code original}符合{@code type}类型，false表示不符合
     */
    private static boolean isLegal(String original, SystemType type) {
    	if (type == SystemType.BIN) {
    		int i  = 0;
	        while (i < original.length()) {
	    	    if (original.charAt(i) != 48 && original.charAt(i) != 49) 
	    		    return false;
		        i++;
		    }
	    }
    	
	    else if (type == SystemType.OCT) {
	        int i  = 0;
	        while (i < original.length()) {
		        if (original.charAt(i) < 48 || original.charAt(i) > 55) 
			        return false;
		        i++;
	        }
	    }
    	
	    else if (type == SystemType.DEC) {
	        int i  = 0;
	        while (i < original.length()) {
		        if (original.charAt(i) < 48 || original.charAt(i) > 57)
			        return false;
		        i++;
	        }
	    }
    	
	    else if (type == SystemType.HEX) {
	        int i  = 0;
	        while (i < original.length()) {
		        if ( !((original.charAt(i) >= 48 && original.charAt(i) <= 57)
				|| (original.charAt(i) >= 65 && original.charAt(i) <= 70)
				|| (original.charAt(i) >= 97 && original.charAt(i) <= 102)) ) 
			        return false;
		        i++;
	        }
	    }
	    return true;	  
    }
}

