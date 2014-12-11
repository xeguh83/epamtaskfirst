package ru.etu.oop.frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ru.etu.oop.data.Controller;

public class InsertFrame extends JFrame {
	
	private Controller ctrl;
	private String number;
	private String fio;
	
	private final static int DEFAULT_WIDTH = 400;
	private final static int DEFAULT_HEIGHT = 200;
	
	public InsertFrame(Controller ctrl) {
		
		this.ctrl = ctrl;
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Заселить постояльца");
		setLayout(null);
		
		
		JLabel label = new JLabel("Введите номер комнаты и ФИО постояльца");
		label.setSize(300, 20);
		label.setLocation(65, 10);
		add(label);
		
		JLabel labelField1 = new JLabel("Номер комнаты");
		labelField1.setSize(100, 20);
		labelField1.setLocation(20, 50);
		add(labelField1);
		
		
		JTextField textFieldNumber = new JTextField("", 20);
		textFieldNumber.setSize(240, 20);
		textFieldNumber.setLocation(130, 50);
		add(textFieldNumber);

		
	}
	
}
