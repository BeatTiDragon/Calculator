package com.calculator.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

import com.calculator.dto.CalculDto;
import com.calculator.string.BasicString;
import com.calculator.ui.MainFrame;


public class ConfigIO {
  private CalculDto dto;
	  
  public ConfigIO(CalculDto dto){
	  this.dto = dto;
	  readConfig();
	  init();
  }
  /**��ȡ����*/
  private void readConfig() {  
	  File configData = new File(BasicString.CONFIG_PATH);
	  try {
		//����ļ������ڣ������ļ�
		if(!configData.exists()){
			    new File("data").mkdirs();
			    configData.createNewFile();
		}
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BasicString.CONFIG_PATH));
		dto = (CalculDto)ois.readObject();
		ois.close();
	  } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("\n���� ��ӭʹ��Zeus��������� ����");
	  }		
  }
  /**��ʼ��*/
  private void init() {
	  
	  //��ӡ��ʷ
      for(int i = 0;i < MainFrame.jmi_edit_history.length;i++) 
      	if(i%2 != 0)
      		MainFrame.jmi_edit_history[i].setText("="+dto.getRecord(i/2).getResult());
  	    else
  	    	MainFrame.jmi_edit_history[i].setText(dto.getRecord(i/2).getFormula());
      
      //�ı���ʾ��ʽ
      MainFrame.text.setHorizontalAlignment(dto.getView());
      switch(dto.getView()){
      case 2:MainFrame.jrbmi_check_view_left.setSelected(true);break;
      case 0:MainFrame.jrbmi_check_view_center.setSelected(true);break;
      case 4:MainFrame.jrbmi_check_view_right.setSelected(true);break;
      }
      //����ģʽ
      if(dto.getPanel() == 1) {
      	MainFrame.jrbmi_check_system.setSelected(true);
      }
      else
      	MainFrame.jrbmi_check_standard.setSelected(true);
      
      //ѡ���׼��廹�ǽ������
      if (dto.getPanel() == 0) {
	      //�Ƿ����M�����ı���
	      if(dto.getMemory().equals(BigDecimal.ZERO))
	  		MainFrame.text.setBounds(12,51,147,40);
	      else {
	  		MainFrame.text.setBounds(12,51,80,40);
	  		MainFrame.MText.setBounds(93,51,66,40);
	  		MainFrame.MText.setText(dto.getMemory() + "");
	      }
      }
      else {
    	  for (int i = 0; i < MainFrame.button.length; i++)
    		  MainFrame.button[i].setVisible(false);
    	  MainFrame.jPanSystem.setVisible(true);
    	  MainFrame.text.setBounds(12,51,147,40);
    	  MainFrame.frame.setSize(171, 170);
    	  MainFrame.jm_edit_history.setVisible(false);
      }
      //���ư�ťѡ��
	  switch (dto.getType()) {
		  case BIN :MainFrame.jrbBin.setSelected(true);
		            break;
		  case OCT :MainFrame.jrbOct.setSelected(true);
		            break;
		  case DEC :MainFrame.jrbDec.setSelected(true);
		            break;
		  case HEX :MainFrame.jrbHex.setSelected(true);
      }
	  //����ѡ��
	  MainFrame.setTheme(dto.getTheme());
      MainFrame.jrbmi_check_theme [dto.getTheme()].setSelected(true);
      
      //Ƥ��ѡ��
      MainFrame.setSkin(dto.getSkin());
      MainFrame.jrbmi_check_skin [dto.getSkin()].setSelected(true);
      
      //���尴�ϴ�λ����ʾ
      MainFrame.frame.setLocation(dto.getLocation());
      MainFrame.frame.setVisible(true);
  }
  /**��������*/
  public void saveConfig(){
	    try {
	    	dto.setLocation( MainFrame.frame.getX(),  MainFrame.frame.getY());
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BasicString.CONFIG_PATH));
			oos.writeObject(dto);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
  }	  
  /**@return ��������*/
  public CalculDto getConfig(){
	  return dto;
  }
}
