package com.calculator.ui;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public interface UIElement {
	
	JMenuBar jmb = new JMenuBar();
	//查看菜单项
	JMenu jm_check = new JMenu("查看(V)");
	JMenu jm_check_view = new JMenu("显示方式(W)");
	JMenu jm_check_skin = new JMenu("皮肤(Z)");
	JMenu jm_check_theme = new JMenu("主题(X)");
	JMenuItem jmi_check_exit = new JMenuItem("退出(Q)");
	ButtonGroup bg_check_mode = new ButtonGroup();
	ButtonGroup bg_check_view_alignment = new ButtonGroup();
	ButtonGroup bg_check_skin = new ButtonGroup();
	ButtonGroup bg_check_theme = new ButtonGroup();
	ButtonGroup bgSystem = new ButtonGroup();
	JRadioButtonMenuItem[] jrbmi_check_theme = new JRadioButtonMenuItem[4];
	JRadioButtonMenuItem[] jrbmi_check_skin = new JRadioButtonMenuItem[8];
    JRadioButtonMenuItem jrbmi_check_view_left = new JRadioButtonMenuItem("左显示");
    JRadioButtonMenuItem jrbmi_check_view_center = new JRadioButtonMenuItem("居中显示");
    JRadioButtonMenuItem jrbmi_check_view_right = new JRadioButtonMenuItem("右显示");
    JRadioButtonMenuItem jrbmi_check_standard = new JRadioButtonMenuItem("标准型(S)");
    JRadioButtonMenuItem jrbmi_check_system = new JRadioButtonMenuItem("进制转换(T)");
    
	//编辑菜单项
	JMenu jm_edit = new JMenu("编辑(E)");
	JMenu jm_edit_history = new JMenu("历史(H)");
	JMenu jm_edit_history_MORE = new JMenu("更多(M)");
	JMenuItem jmi_edit_copy = new JMenuItem("复制(C)   Ctrl+C");
	JMenuItem jmi_edit_cut = new JMenuItem("剪切(X)   Ctrl+X");
	JMenuItem jmi_edit_paste = new JMenuItem("粘贴(P)   Ctrl+V");
	JMenuItem jmi_edit_history[] = new JMenuItem[20];
	//帮助菜单项
	JMenu jm_help = new JMenu("帮助(H)");
	JMenuItem jmi_help_help = new JMenuItem("查看帮助(V)");
	JMenuItem jmi_help_about = new JMenuItem("关于(A)");	

	JTextField text = new JTextField();
	JTextField MText = new JTextField();
	JTextArea textArea = new JTextArea(12,40);
	JScrollPane jsp = new JScrollPane(textArea); 
	
    JRadioButton jrbBin = new JRadioButton("2进制");
    JRadioButton jrbOct = new JRadioButton("8进制");
    JRadioButton jrbDec = new JRadioButton("10进制",true);
    JRadioButton jrbHex = new JRadioButton("16进制");
	JButton button[] = new JButton[29];
	JPanel mainPanel = new JPanel();
	JPanel jPanStatus = new JPanel();
	JPanel jPanSystem = new JPanel();
	JFrame frame = new JFrame();
	Font fontText = new Font("Times",Font.BOLD,15);
	Font fontMenu = new Font("Times",Font.PLAIN,11);
	Font fontMin = new Font("Times",Font.BOLD,9);
	JLabel title = new JLabel("计算器");
	JLabel close = new JLabel("×");
	JLabel min = new JLabel("-");
	ImageIcon imageButton[] = new ImageIcon[29];
	String theme[] = {
			"Nimbus","Windows","WindowsClassic","Metal"
	};
	String skin[] = {
			"DarkSalmon","DarkGray","FireBrick","Purple",
			"Azure","Silver","DarkSeaGreen","Null",
	};
	
}
