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
	//�鿴�˵���
	JMenu jm_check = new JMenu("�鿴(V)");
	JMenu jm_check_view = new JMenu("��ʾ��ʽ(W)");
	JMenu jm_check_skin = new JMenu("Ƥ��(Z)");
	JMenu jm_check_theme = new JMenu("����(X)");
	JMenuItem jmi_check_exit = new JMenuItem("�˳�(Q)");
	ButtonGroup bg_check_mode = new ButtonGroup();
	ButtonGroup bg_check_view_alignment = new ButtonGroup();
	ButtonGroup bg_check_skin = new ButtonGroup();
	ButtonGroup bg_check_theme = new ButtonGroup();
	ButtonGroup bgSystem = new ButtonGroup();
	JRadioButtonMenuItem[] jrbmi_check_theme = new JRadioButtonMenuItem[4];
	JRadioButtonMenuItem[] jrbmi_check_skin = new JRadioButtonMenuItem[8];
    JRadioButtonMenuItem jrbmi_check_view_left = new JRadioButtonMenuItem("����ʾ");
    JRadioButtonMenuItem jrbmi_check_view_center = new JRadioButtonMenuItem("������ʾ");
    JRadioButtonMenuItem jrbmi_check_view_right = new JRadioButtonMenuItem("����ʾ");
    JRadioButtonMenuItem jrbmi_check_standard = new JRadioButtonMenuItem("��׼��(S)");
    JRadioButtonMenuItem jrbmi_check_system = new JRadioButtonMenuItem("����ת��(T)");
    
	//�༭�˵���
	JMenu jm_edit = new JMenu("�༭(E)");
	JMenu jm_edit_history = new JMenu("��ʷ(H)");
	JMenu jm_edit_history_MORE = new JMenu("����(M)");
	JMenuItem jmi_edit_copy = new JMenuItem("����(C)   Ctrl+C");
	JMenuItem jmi_edit_cut = new JMenuItem("����(X)   Ctrl+X");
	JMenuItem jmi_edit_paste = new JMenuItem("ճ��(P)   Ctrl+V");
	JMenuItem jmi_edit_history[] = new JMenuItem[20];
	//�����˵���
	JMenu jm_help = new JMenu("����(H)");
	JMenuItem jmi_help_help = new JMenuItem("�鿴����(V)");
	JMenuItem jmi_help_about = new JMenuItem("����(A)");	

	JTextField text = new JTextField();
	JTextField MText = new JTextField();
	JTextArea textArea = new JTextArea(12,40);
	JScrollPane jsp = new JScrollPane(textArea); 
	
    JRadioButton jrbBin = new JRadioButton("2����");
    JRadioButton jrbOct = new JRadioButton("8����");
    JRadioButton jrbDec = new JRadioButton("10����",true);
    JRadioButton jrbHex = new JRadioButton("16����");
	JButton button[] = new JButton[29];
	JPanel mainPanel = new JPanel();
	JPanel jPanStatus = new JPanel();
	JPanel jPanSystem = new JPanel();
	JFrame frame = new JFrame();
	Font fontText = new Font("Times",Font.BOLD,15);
	Font fontMenu = new Font("Times",Font.PLAIN,11);
	Font fontMin = new Font("Times",Font.BOLD,9);
	JLabel title = new JLabel("������");
	JLabel close = new JLabel("��");
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
