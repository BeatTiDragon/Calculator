package com.calculator.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import com.calculator.string.BasicString;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements UIElement {
	
   public MainFrame(){

	    setObject();
	   
        addObject();

	}
 
	private void setObject() {
		//主题初始化
		for(int i = 0; i < jrbmi_check_theme.length; i++) {
			jrbmi_check_theme[i] = new JRadioButtonMenuItem(theme[i]);
			bg_check_theme.add(jrbmi_check_theme[i]);
			jm_check_theme.add(jrbmi_check_theme[i]);
		}
		//皮肤初始化
		for(int i = 0; i < jrbmi_check_skin.length; i++) {
			jrbmi_check_skin[i] = new JRadioButtonMenuItem(skin[i]);
			bg_check_skin.add(jrbmi_check_skin[i]);
			jm_check_skin.add(jrbmi_check_skin[i]);
		}
		
		//按钮初始化
		for(int i = 0; i < button.length; i++) {   	
			imageButton[i] = new ImageIcon("resources/images/" + i +".png" );
			button[i] = new JButton(imageButton[i]);
    		button[i].setFocusPainted(false);
		}
		for(int v = 0;v < 6;v++){
	    	for(int u = 0;u < 5;u++){
	    		int i = u+v*5;
	    		if(i == 24)
	    			button[i].setBounds(11 + u*30,97 + v*26,30,49);
	    		else if(i == 29)
	    			break;
	    		else 
	    			button[i].setBounds(11 + u*30,97 + v*26,30,23);
            }
	    }
		//设置按钮提示
		button[0].setToolTipText("右键:MC");
		button[4].setToolTipText("右键:MS");
		button[5].setToolTipText("右键:^3");
		button[6].setToolTipText("右键:1/(...)");
		button[8].setToolTipText("右键:(...)/1");
		button[12].setToolTipText("右键:9个0");
		button[13].setToolTipText("右键:(...)*");
		button[17].setToolTipText("右键:6个0");
		button[22].setToolTipText("右键:3个0");
		
		jPanSystem.setLayout(new GridLayout(2,2,5,10));
  	    jPanSystem.setBounds(18, 100, 147, 50);
  	    jPanSystem.setVisible(false);
		
		jPanStatus.setLayout(null);
		jPanStatus.setBounds(0, 0, 170, 30);
		title.setBounds(60, 3, 90, 20);
		min.setBounds(147, 6, 10, 10);
		close.setBounds(157, 6, 12, 10);
		
  	    text.setToolTipText("按ESC清空");
		text.setFont(fontText);
		MText.setFont(fontText);
		MText.setEditable(false);
		
		textArea.setText(BasicString.HELP);
		textArea.setFont(new Font("楷体",Font.BOLD,12));
		textArea.setEditable(false);
		
		jm_check.setFont(fontMenu);
	    jm_check.setMnemonic('V');
		jm_check_view.setMnemonic('W');
		jm_check_theme.setMnemonic('X');
		jm_check_skin.setMnemonic('Z');
	    jmi_check_exit.setMnemonic('Q');
	    jm_edit.setFont(fontMenu);
	    jm_edit.setMnemonic('E');
	    jmi_edit_copy.setMnemonic('C');
	    jmi_edit_cut.setMnemonic('X');
	    jmi_edit_paste.setMnemonic('P');
		jm_edit_history.setMnemonic('H');
		jm_edit_history_MORE.setMnemonic('M');
		jm_help.setFont(fontMenu);
		jm_help.setMnemonic('H');
	    jmi_help_about.setMnemonic('A');
	    jmi_help_help.setMnemonic('V');
	    jrbmi_check_standard.setMnemonic('S');
	    jrbmi_check_system.setMnemonic('T');
		
		jmb.setBounds(6,30,159,14);
		
		mainPanel.setLayout(null);
		frame.add(mainPanel);
        frame.setSize(171,260);
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void addObject() {
		//历史初始化
	    for(int i = 0;i < jmi_edit_history.length; i++) {
			jmi_edit_history[i]=new JMenuItem();
			if(i < 10){
				jm_edit_history.add(jmi_edit_history[i]);
				if(i%2 != 0)
					jm_edit_history.addSeparator();
			}   
			else{
				jm_edit_history_MORE.add(jmi_edit_history[i]);
                if(i%2 != 0&&i != 19)
                	jm_edit_history_MORE.addSeparator();
			}
			if(i == 19)
				jm_edit_history.add(jm_edit_history_MORE);
        }
	    
		jPanStatus.add(title);
		jPanStatus.add(min);
		jPanStatus.add(close);
		
		bgSystem.add(jrbBin);
		bgSystem.add(jrbOct);
		bgSystem.add(jrbDec);
		bgSystem.add(jrbHex);
	    
		bg_check_view_alignment.add(jrbmi_check_view_left);
		bg_check_view_alignment.add(jrbmi_check_view_center);
		bg_check_view_alignment.add(jrbmi_check_view_right);
		bg_check_mode.add(jrbmi_check_standard);
		bg_check_mode.add(jrbmi_check_system);
		
		jm_check_view.add(jrbmi_check_view_left);
		jm_check_view.add(jrbmi_check_view_center);
		jm_check_view.add(jrbmi_check_view_right);
	    jm_check.add(jrbmi_check_standard);
	    jm_check.add(jrbmi_check_system);
	    jm_check.add(jm_check_theme);
	    jm_check.add(jm_check_skin);
	    jm_check.add(jm_check_view);
	    jm_check.add(jmi_check_exit);

	    jm_edit.add(jmi_edit_copy);
	    jm_edit.add(jmi_edit_cut);
	    jm_edit.add(jmi_edit_paste);
	    jm_edit.add(jm_edit_history);

	    jm_help.add(jmi_help_help);
	    jm_help.add(jmi_help_about);
	    
		jmb.add(jm_check);
		jmb.add(jm_edit);
	    jmb.add(jm_help);
	    
	    
	    jPanSystem.add(jrbBin);
	    jPanSystem.add(jrbOct);
	    jPanSystem.add(jrbDec);
	    jPanSystem.add(jrbHex);
	    mainPanel.add(jPanSystem);
		mainPanel.add(jPanStatus);
		mainPanel.add(jmb);
		mainPanel.add(text);
		mainPanel.add(MText);
	    for(int i = 0; i < button.length; i++)
    		mainPanel.add(button[i]);
	}	
	  
	public static void setSkin(int i) {
	    switch(i) {
		 case 0 : {Color c = new Color(233, 150, 122);
			       jPanStatus.setBackground(c);
				   mainPanel.setBorder(BorderFactory.createLineBorder(c, 5));
		 }
		           break;
		 case 1 :  {Color c = new Color(169,169,169);
	       		   jPanStatus.setBackground(c);
		           mainPanel.setBorder(BorderFactory.createLineBorder(c, 5));
         }
		           break;
		 case 2 :  {Color c = new Color(178, 34, 34);
	               jPanStatus.setBackground(c);
		           mainPanel.setBorder(BorderFactory.createLineBorder(c, 5));
         }
		           break;
		 case 3 :  {Color c = new Color(153, 50, 204);
	       		   jPanStatus.setBackground(c);
		           mainPanel.setBorder(BorderFactory.createLineBorder(c, 5));
         }
		           break;
		 case 4 :  {Color c = new Color(0, 128, 255);
	               jPanStatus.setBackground(c);
		           mainPanel.setBorder(BorderFactory.createLineBorder(c, 5));
         }
		           break;
		 case 5 :  {Color c = new Color(192, 192, 192);
	               jPanStatus.setBackground(c);
		           mainPanel.setBorder(BorderFactory.createLineBorder(c, 5));
         }
		           break;
		 case 6 :  {Color c = new Color(144, 188, 143);
	               jPanStatus.setBackground(c);
		           mainPanel.setBorder(BorderFactory.createLineBorder(c, 5));
         }
		           break;
         default : {
        	       jPanStatus.setBackground(null);
        	       mainPanel.setBorder(BorderFactory.createLineBorder(null, 0));
         }
	    }
			
	}
	
	public static void setTheme(int i) {
		try {
	    switch(i) {
		 case 0 :  UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		           break;
		 case 1 :  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		           break;
		 case 2 :  UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		           break;
		 case 3 :  UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		           break;
	    }
		} catch (Exception e) {
		e.printStackTrace();
		}
	    SwingUtilities.updateComponentTreeUI(frame);	
	}

}
