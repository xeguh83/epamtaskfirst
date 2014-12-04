package ru.etu.oop.frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InsertFrame extends JFrame {
	
	private String number;
	private String fio;
	
	private final static int DEFAULT_WIDTH = 400;
	private final static int DEFAULT_HEIGHT = 200;
	
	public InsertFrame() {
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("�������� ����������");
		setLayout(null);
		
		
		JLabel label = new JLabel("������� ����� ������� � ��� ����������");
		label.setSize(300, 20);
		label.setLocation(65, 10);
		add(label);
		
		JLabel labelField1 = new JLabel("����� �������");
		labelField1.setSize(100, 20);
		labelField1.setLocation(20, 50);
		add(labelField1);
		
		
		JTextField textFieldNumber = new JTextField("", 20);
		textFieldNumber.setSize(240, 20);
		textFieldNumber.setLocation(130, 50);
		add(textFieldNumber);

		
	}
	
}
