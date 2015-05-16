package com.calculator.handle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.calculator.algorithm.SystemConversion;
import com.calculator.algorithm.SystemType;
import com.calculator.dto.CalculDto;
import com.calculator.io.*;
import com.calculator.string.BasicString;
import com.calculator.ui.MainFrame;
import com.calculator.util.Recline;

import java.awt.Color;
import java.awt.Point;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class CalculHandle extends MainFrame {
  private CalculDto dto;
  private ConfigIO io;
  private ObjectActionListener objectListener;
  private HistoryActionListener historyListener;
  private ThemeActionListener themeListener;
  private SkinActionListener skinListener;
  private MouseEventListener mouseListener;
  private String str;
  private int xOld, yOld;
  private BigDecimal MResult;
  public boolean changeClothes;

  public CalculHandle() {
	//创建系统配置
	dto = new CalculDto();
	//通过ConfigIO对象来获得上一次的系统配置
	io = new ConfigIO(dto);
    //重获系统配置(上次运行的配置)
    dto = io.getConfig();
    //实例化监听器对象
    objectListener = new ObjectActionListener();
    historyListener = new HistoryActionListener();
    themeListener = new ThemeActionListener();
    skinListener = new SkinActionListener();
    mouseListener = new MouseEventListener();
    //注册监听器
    addListener();
  }

  private void addListener() {
	frame.addMouseListener(mouseListener);
	button[0].addMouseListener(mouseListener);
	button[4].addMouseListener(mouseListener);
	button[5].addMouseListener(mouseListener);
	button[6].addMouseListener(mouseListener);
	button[8].addMouseListener(mouseListener);
	button[12].addMouseListener(mouseListener);
	button[13].addMouseListener(mouseListener);
	button[17].addMouseListener(mouseListener);
	button[22].addMouseListener(mouseListener);
	min.addMouseListener(mouseListener);
	close.addMouseListener(mouseListener);
	text.addActionListener(objectListener);
	jrbBin.addActionListener(objectListener);
	jrbOct.addActionListener(objectListener);
	jrbDec.addActionListener(objectListener);
	jrbHex.addActionListener(objectListener);
    jmi_edit_copy.addActionListener(objectListener);
    jmi_edit_cut.addActionListener(objectListener);
    jmi_edit_paste.addActionListener(objectListener);
    jmi_check_exit.addActionListener(objectListener);
    jmi_help_help.addActionListener(objectListener);
    jmi_help_about.addActionListener(objectListener);
    jrbmi_check_standard.addActionListener(objectListener);
    jrbmi_check_system.addActionListener(objectListener);
    jrbmi_check_view_left.addActionListener(objectListener);
    jrbmi_check_view_center.addActionListener(objectListener);
    jrbmi_check_view_right.addActionListener(objectListener);		
    for(int i = 0; i < button.length; i++)
		button[i].addActionListener(objectListener);
    for(int i = 0;i < jmi_edit_history.length; i++) 
		jmi_edit_history[i].addActionListener(historyListener);
    for(int i = 0; i < jrbmi_check_skin.length; i++) 
    	jrbmi_check_skin[i].addActionListener(skinListener);
    for(int i = 0; i < jrbmi_check_theme.length; i++) 
    	jrbmi_check_theme[i].addActionListener(themeListener);
		
	text.addKeyListener(new KeyAdapter(){
		public void keyTyped(KeyEvent e) {
			if (dto.getPanel() == 1) {
				if (dto.getType() == SystemType.BIN) {
					if ("10".indexOf(e.getKeyChar())==-1)
						e.consume();
				}
				else if (dto.getType() == SystemType.OCT) {
					if ("12345670".indexOf(e.getKeyChar())==-1)
						e.consume();
				}
				else if (dto.getType() == SystemType.DEC) {
					if ("1234567890".indexOf(e.getKeyChar())==-1)
						e.consume();
				}
				else if (dto.getType() == SystemType.HEX) {
					if ("1234567890ABCDEFabcdef".indexOf(e.getKeyChar())==-1)
						e.consume();
				}
				
			}
			else {
				if ("1234567890.^!/*()+-".indexOf(e.getKeyChar())==-1)
					e.consume();
			}
		}

	    public void keyPressed(KeyEvent e) {
			String keyTxt = KeyEvent.getKeyText(e.getKeyCode());
			if (keyTxt == "Backspace")
				dto.setTip(Recline.checkTip(text.getText()));
		    else if (keyTxt == "Esc") {
				text.setText("");
				dto.setFraction("");;
				dto.setTip(0);;
			}
			else if (keyTxt == "Enter") {
				if (!text.getText().equals("")) {
		        String str=Recline.foundation(text.getText(),dto);
			    text.setText(str);
				}
			}
					 
         }
	});
    //窗口关闭时保存保存历史
	frame.addWindowListener(new WindowAdapter() {
		//关闭窗口按钮手动关闭
		public void windowClosing(WindowEvent e) {
			io.saveConfig();
			Point point = frame.getLocation();
		    for (int i = 0; i < 1000; i++) {
		         point.y -= 3;
		         frame.setLocation(point.x, point.y);
		    }
	    }
		//非活动窗口
		public void windowDeactivated(WindowEvent e) {
			io.saveConfig();
	    }
	});
	frame.addMouseMotionListener(new MouseMotionAdapter() {  
	    @Override  
	    public void mouseDragged(MouseEvent e) {  
	        int xOnScreen = e.getXOnScreen();  
	        int yOnScreen = e.getYOnScreen(); 
	        int xx = xOnScreen - xOld;  
	        int yy = yOnScreen - yOld;  
	        frame.setLocation(xx, yy);  
	    }  
	});
  }

 /**内部单独组件监听器类*/
 class ObjectActionListener implements ActionListener {

  public void actionPerformed(ActionEvent e) {
	//清除组
	if(e.getSource()==button[0]) {
		dto.setTip(0);
	    text.setText("");
		dto.setFraction("");
	}
	else if(e.getSource()==button[1]) {
	  if(!text.getText().equals("")){
		str=new String(text.getText());
		text.setText(str=str.substring(0,str.length()-1));
		dto.setFraction("");
	    dto.setTip(Recline.checkTip(str));
	  }
	}
	//M+运算组
	else if(e.getSource()==button[2]) {
		if(!text.getText().equals("")){
			MText.setVisible(true);
			text.setBounds(12,51,80,40);
			MText.setBounds(93,51,66,40);
			str=Recline.foundation(text.getText(),dto);
			MResult = dto.getMemory().add(new BigDecimal(str));
			dto.setMemory(MResult);
	        MText.setText(MResult + "");
	    	MText.setCaretPosition(0);
		}
	}
	else if(e.getSource()==button[3]) {
		if(!text.getText().equals("")) {
			MText.setVisible(true);
			text.setBounds(12,51,80,40);
			MText.setBounds(93,51,66,40);
			str=Recline.foundation(text.getText(),dto);
			MResult = dto.getMemory().subtract(new BigDecimal(str));
			dto.setMemory(MResult);
		    MText.setText(MResult + "");
		    MText.setCaretPosition(0);
		}
	}
	else if(e.getSource()==button[4]) {
	  if(!MText.getText().equals(""))
        text.setText(MText.getText());
	  
	}
    //幂方按钮组
	else if(e.getSource()==button[5]) 
		text.setText(text.getText()+"^2");
	
	else if(e.getSource()==button[6]) 
		text.setText(text.getText()+"^");
	
    //小数与分数之间转化的按钮
	else if(e.getSource()==button[7]) {
	    if(dto.getIsClick()==false || dto.getFraction()=="")
			 JOptionPane.showMessageDialog(null,BasicString.TRANSFROMERROR, "转换分式出错", JOptionPane.ERROR_MESSAGE);
		else if(text.getText().indexOf('/')==-1&&dto.getIsClick())
			 text.setText(dto.getFraction()); 
	    else  
	    	 text.setText(dto.getDecimal());
	    text.setCaretPosition(0);
	}
    //除法按钮
    else if(e.getSource()==button[8]) {
		if(!text.getText().equals("")){
			str=new String(text.getText());
			if(str.charAt(str.length()-1)=='+'||str.charAt(str.length()-1)=='-'
					||str.charAt(str.length()-1)=='*'||str.charAt(str.length()-1)=='/'){
				text.setText(str.substring(0,str.length()-1)+"/");
			}
			else 
				text.setText(text.getText()+"/");
		}
    }
    //阶乘"!"按钮
    else if(e.getSource()==button[9]) 
    	text.setText(text.getText()+"!");
    
	//789按钮组
	else if(e.getSource()==button[10]) {
		str=new String(text.getText());
		if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*7");
		else 
			text.setText(text.getText()+"7");
	}
	else if(e.getSource()==button[11]) {
		str=new String(text.getText());
		if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*8");
		else 
			text.setText(text.getText()+"8");
	}
	else if(e.getSource()==button[12]) {
		str=new String(text.getText());
		if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*9");
		else 
			text.setText(text.getText()+"9");
	}
	//乘法按钮
	else if(e.getSource()==button[13]) {
		if(!text.getText().equals("")){
			str=new String(text.getText());
			if(str.charAt(str.length()-1)=='+'||str.charAt(str.length()-1)=='-'
					||str.charAt(str.length()-1)=='*'||str.charAt(str.length()-1)=='/'){
				text.setText(str.substring(0,str.length()-1)+"*");
			}
			else 
				text.setText(text.getText()+"*");
		}
	}
	//开方按钮
	else if(e.getSource()==button[14]) {
		text.setText(text.getText()+"^(1/");
		dto.setTip(dto.getTip()+1);
	}
	//456按钮组
	else if(e.getSource()==button[15]) {
		str=new String(text.getText());
		if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*4");
		else 
			text.setText(text.getText()+"4");
	}
	else if(e.getSource()==button[16]) {
		str=new String(text.getText());
		if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*5");
		else 
			text.setText(text.getText()+"5");
	}
	else if(e.getSource()==button[17]) {
		str=new String(text.getText());
		if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*6");
		else 
			text.setText(text.getText()+"6");
	}
    //减法按钮
	else if(e.getSource()==button[18]) {
		str=new String(text.getText());
		if(str.equals(""))text.setText(text.getText()+"-");
		else if(str.charAt(str.length()-1)=='+'||str.charAt(str.length()-1)=='-'
				||str.charAt(str.length()-1)=='*'||str.charAt(str.length()-1)=='/'){
			text.setText(str.substring(0,str.length()-1)+"-");
		}
		else 
			text.setText(text.getText()+"-");
	}
	//根号按钮
	else if(e.getSource()==button[19]) 
		text.setText(text.getText()+"√");
	
    //123按钮组
	else if(e.getSource()==button[20]) {
		str=new String(text.getText());
		if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*1");
		else 
			text.setText(text.getText()+"1");
	}
	else if(e.getSource()==button[21]) {
		str=new String(text.getText());
		if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*2");
		else 
			text.setText(text.getText()+"2");
	}
	else if(e.getSource()==button[22]) {
		str=new String(text.getText());
		if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*3");
		else 
			text.setText(text.getText()+"3");
	}
    //加法按钮
	else if(e.getSource()==button[23]) {
		if(!text.getText().equals("")){
			str=new String(text.getText());
			if(str.charAt(str.length()-1)=='+'||str.charAt(str.length()-1)=='-'
					||str.charAt(str.length()-1)=='*'||str.charAt(str.length()-1)=='/'){
				text.setText(str.substring(0,str.length()-1)+"+");
			}
			else 
				text.setText(text.getText()+"+");
		}
	}
	//等号
	else if(e.getSource()==button[24]) {
		if(!text.getText().equals("")){
		    text.setText(str=Recline.foundation(text.getText(),dto));
			text.setCaretPosition(0);
		    dto.setIsClick(true);
		}
    }
	//智能的括号
	else if(e.getSource()==button[25]) {
		str=new String(text.getText());
	    if(str.equals("")==false
	    		&&    
	    		(
	    		    (
	    		    dto.getTip()==0
	    		    &&
	    		        (
	    				     (
	    				     str.charAt(str.length()-1)>=48
	    				     &&str.charAt(str.length()-1)<=57
	    				     )
	    		             ||str.charAt(str.length()-1)==')'
	    		        )
	    		    )
	    		||str.charAt(str.length()-1)=='!'
	    		||str.charAt(str.length()-1)=='.'
	    		)
	      ){
	    	text.setText(text.getText()+"*(");
	    	dto.setTip(dto.getTip()+1);
	    }
	    else if(str.equals("")==false
	    		  &&
	    		  (
	    				str.charAt(str.length()-1)==')'
	    				||(
	    				dto.getTip()!=0&&
	    				str.charAt(str.length()-1)=='π'
	    				)
	    		        ||(
	    		        str.charAt(str.length()-1)>=48
	    		        &&str.charAt(str.length()-1)<=57
	    		        )
	    		   )
	    	   ){
	    	text.setText(text.getText()+")");
	    	dto.setTip(dto.getTip()-1);;
	    }
	    else {
	    	text.setText(text.getText()+"(");
	    	dto.setTip(dto.getTip()+1);
	    }
	}
	
	//0按钮
	else if(e.getSource()==button[26]) {
		str=new String(text.getText());
        if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
			text.setText(text.getText()+"*0");
		else 
			text.setText(text.getText()+"0");
	}
	//小数点按钮
	else if(e.getSource()==button[27]) 
		text.setText(text.getText()+".");
	
	//pi按钮
	else if(e.getSource()==button[28]) 
        text.setText(text.getText()+"π");
	
    //复制剪切黏贴退出组
	else if(e.getSource() == jmi_edit_copy) {
		Clipboard clipboard = getToolkit().getSystemClipboard(); //获得系统粘贴板  
		String selectText = text.getSelectedText();     
		if(selectText!=null){
		StringSelection textInfoSelected = new StringSelection(selectText); //建立一个粘贴板内容实例.   
		clipboard.setContents(textInfoSelected, null); 
		}
		else {
		StringSelection textInfoSelected = new StringSelection(text.getText());
		clipboard.setContents(textInfoSelected, null);
		}
	}
	else if(e.getSource() == jmi_edit_cut) { 
		if(text.getSelectedText()!=null)
			text.cut();
		else {
			Clipboard clipboard = getToolkit().getSystemClipboard(); //获得系统粘贴板 
			clipboard.setContents(new StringSelection(text.getText()), null);
			text.setText("");
		}
	}
	else if(e.getSource() == jmi_edit_paste) 
		text.paste();
	
	else if(e.getSource() == jmi_check_exit) {
        io.saveConfig();
		Point point = frame.getLocation();
	    for (int i = 0; i < 1000; i++) {
	         point.y -= 3;
	         frame.setLocation(point.x, point.y);
	    }
		System.exit(0);
	}
	//帮助关于组
	else if(e.getSource() == jmi_help_help) 
		JOptionPane.showMessageDialog(null , jsp, "帮助", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("resources/images/help.jpg"));
	
	else if(e.getSource() == jmi_help_about) 
		JOptionPane.showMessageDialog( null , BasicString.ABOUT ,"关于" , JOptionPane.INFORMATION_MESSAGE, new ImageIcon("resources/images/about.gif")) ;
	
    //显示方式
	else if(e.getSource() == jrbmi_check_view_right) {
		text.setHorizontalAlignment(JTextField.RIGHT);
		dto.setView(4);
	}
	else if(e.getSource() == jrbmi_check_view_center) {
		text.setHorizontalAlignment(JTextField.CENTER);
		dto.setView(0);
	}
	else if(e.getSource() == jrbmi_check_view_left) {
		text.setHorizontalAlignment(JTextField.LEFT);
		dto.setView(2);
	}
    //计算模式
	else if(e.getSource() == jrbmi_check_standard) {
		frame.setSize(171,260);
		text.setText("");
	      //是否出现M运算文本框
	      if(dto.getMemory().equals(BigDecimal.ZERO))
	  		text.setBounds(12,51,147,40);
	      else {
	    	MText.setVisible(true);
	  		text.setBounds(12,51,80,40);
	  		MText.setBounds(93,51,66,40);
	  		MText.setText(dto.getMemory() + "");
	      }
		jPanSystem.setVisible(false);
		for (int i = 0; i < button.length; i++)
			button[i].setVisible(true);
		jm_edit_history.setVisible(true);
		dto.setPanel(0);
	}
	else if(e.getSource() == jrbmi_check_system) {
		for (int i = 0; i < button.length; i++)
			button[i].setVisible(false);
		text.setText("");
		text.setBounds(12,51,147,40);
		MText.setVisible(false);
		frame.setSize(171, 170);
		jm_edit_history.setVisible(false);
		jPanSystem.setVisible(true);
		dto.setPanel(1);
	}
	//进制转换组
	else if (e.getSource() == jrbBin) {
		try {
			if (!text.getText().equals("")) {
				text.setText(SystemConversion.toBinConverter(text.getText(), dto.getType()));
				text.setCaretPosition(0);
			}
		} catch (java.lang.IllegalArgumentException e1) {
			JOptionPane.showMessageDialog( null , e1.getMessage() ,"ERROR" , JOptionPane.ERROR_MESSAGE) ;
		}
		dto.setType(SystemType.BIN);
	}
	else if(e.getSource() == jrbOct) {
		try {
			if (!text.getText().equals("")) {
				text.setText(SystemConversion.toOctConverter(text.getText(), dto.getType()));
				text.setCaretPosition(0);
			}
		} catch (java.lang.IllegalArgumentException e1) {
			JOptionPane.showMessageDialog( null , e1.getMessage() ,"ERROR" , JOptionPane.ERROR_MESSAGE) ;
		}
		dto.setType(SystemType.OCT);
	}
	else if(e.getSource() == jrbDec) {
		try {
			if (!text.getText().equals("")) {
				text.setText(SystemConversion.toDecConverter(text.getText(), dto.getType()));
				text.setCaretPosition(0);
			}
		} catch (java.lang.IllegalArgumentException e1) {
			JOptionPane.showMessageDialog( null , e1.getMessage() ,"ERROR" , JOptionPane.ERROR_MESSAGE) ;
		}
		dto.setType(SystemType.DEC);
	}
	else if(e.getSource() == jrbHex) {
		try {
			if (!text.getText().equals("")) {
				text.setText(SystemConversion.toHexConverter(text.getText(), dto.getType()));
				text.setCaretPosition(0);
			}
		} catch (java.lang.IllegalArgumentException e1) {
			JOptionPane.showMessageDialog( null , e1.getMessage() ,"ERROR" , JOptionPane.ERROR_MESSAGE) ;
		}
		dto.setType(SystemType.HEX);
	}
	
	if (e.getSource() != button[24] && e.getSource() != button[7])
		dto.setIsClick(false);
  }
	
 }
 /**内部历史数组组件监听器类*/
 class HistoryActionListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    for(int i = 0;i < jmi_edit_history.length; i++)
	      if(e.getSource() == jmi_edit_history[i]) {
	    	if(i%2 != 0)
	    		text.setText(dto.getRecord(i/2).getResult());
		    else
		    	text.setText(dto.getRecord(i/2).getFormula());
	    	break;
	      }	
	}
	 
 }
 /**内部主题数组组件监听器类*/
 class ThemeActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	  for(int i = 0; i < jrbmi_check_theme.length; i++) 
		if(e.getSource() == jrbmi_check_theme[i]) {
		  dto.setTheme(i);
          MainFrame.setTheme(i);
		  break;	
		}      
	}	
 }
 /**内部皮肤数组组件监听器类*/
 class SkinActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	  for(int i = 0; i < jrbmi_check_skin.length; i++) 
		if(e.getSource() == jrbmi_check_skin[i]) {
		  dto.setSkin(i);
          MainFrame.setSkin(i);
		  break;	
		}      
	}	
 }
 /**内部鼠标事件监听器类*/
 class MouseEventListener implements MouseListener {
   public void mouseClicked(MouseEvent e) {
	  if(e.getSource() == min) {
		frame.setExtendedState(JFrame.ICONIFIED);
	  }
	  else if(e.getSource() == close) {
	    io.saveConfig();
		Point point = frame.getLocation();
	    for (int i = 0; i < 1000; i++) {
	         point.y -= 3;
	         frame.setLocation(point.x, point.y);
	    }
		System.exit(0);
	  }
	  else if(e.getButton() == MouseEvent.BUTTON3) {
       if(e.getSource() == button[0]) {
   		dto.setMemory(BigDecimal.ZERO);
  		text.setBounds(12,51,147,40);
  		MText.setVisible(false);
   		MText.setText("");
       }
       else if (e.getSource() == button[4]) {
		 if (!text.getText().equals("")){
			MText.setVisible(true);
			text.setBounds(12,51,80,40);
			MText.setBounds(93,51,66,40);
			str=Recline.foundation(text.getText(),dto);
			MResult = new BigDecimal(str);
			dto.setMemory(MResult);
	        MText.setText(MResult + "");
	    	MText.setCaretPosition(0);
		 }
       }
   	   else if (e.getSource() == button[5]) 
   		   text.setText(text.getText()+"^3");
   	   else if (e.getSource() == button[6]) {
   		   if (!text.getText().equals(""))
   			   text.setText("1/(" + text.getText() + ")");
   	   }
   	   else if (e.getSource() == button[8]) {
   		   if (!text.getText().equals(""))
   			   text.setText("(" + text.getText() + ")/");
   	   }
   	   else if (e.getSource() == button[13]) {
   		   if (!text.getText().equals(""))
   			   text.setText("(" + text.getText() + ")*");
   	   }
   	   else if (e.getSource() == button[12]) {
	   	   str=new String(text.getText());
	       if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
		       text.setText(text.getText()+"*000000000");
		   else 
		       text.setText(text.getText()+"000000000");
   	   }
   	   else if (e.getSource() == button[17]) {
	   	   str=new String(text.getText());
	       if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
		       text.setText(text.getText()+"*000000");
		   else 
		       text.setText(text.getText()+"000000");
   	   }
   	   else if (e.getSource() == button[22]) {
	   	   str=new String(text.getText());
	       if(str.equals("")==false&&(str.charAt(str.length()-1)==')'||str.charAt(str.length()-1)=='!'))
		       text.setText(text.getText()+"*000");
		   else 
		       text.setText(text.getText()+"000");
   	   }
     }
   }
   public void mousePressed(MouseEvent e) {
      if(e.getSource() == frame) {
        xOld = e.getX();  
        yOld = e.getY(); 
      }
      else if(e.getSource() == button[0])
    	  button[0].setBackground(Color.gray);
      else if(e.getSource() == button[4])
    	  button[4].setBackground(Color.gray);
      else if(e.getSource() == button[5])
    	  button[5].setBackground(Color.gray);
      else if(e.getSource() == button[6])
    	  button[6].setBackground(Color.gray);
      else if(e.getSource() == button[8])
    	  button[8].setBackground(Color.gray);
      else if(e.getSource() == button[12])
    	  button[12].setBackground(Color.gray);
      else if(e.getSource() == button[13])
    	  button[13].setBackground(Color.gray);
      else if(e.getSource() == button[17])
    	  button[17].setBackground(Color.gray);
      else if(e.getSource() == button[22])
    	  button[22].setBackground(Color.gray);
   }
	
   public void mouseReleased(MouseEvent e) {
      if(e.getSource() == button[0])
    	  button[0].setBackground(getBackground());
      else if(e.getSource() == button[4])
    	  button[4].setBackground(getBackground());
      else if(e.getSource() == button[5])
    	  button[5].setBackground(getBackground());
      else if(e.getSource() == button[6])
    	  button[6].setBackground(getBackground());
      else if(e.getSource() == button[8])
    	  button[8].setBackground(getBackground());
      else if(e.getSource() == button[12])
    	  button[12].setBackground(getBackground());
      else if(e.getSource() == button[13])
    	  button[13].setBackground(getBackground());
      else if(e.getSource() == button[17])
    	  button[17].setBackground(getBackground());
      else if(e.getSource() == button[22])
    	  button[22].setBackground(getBackground());
   }

   public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
      if(e.getSource() == min) 
    	  min.setForeground(Color.white);
      
      else if(e.getSource() == close) 
    	  close.setForeground(Color.white);
      
   }
   public void mouseExited(MouseEvent e) {
      if(e.getSource() == min) 
    	  min.setForeground(getForeground());
      
      else if(e.getSource() == close) 
    	  close.setForeground(getForeground());
      
   }
	 
 }

}

