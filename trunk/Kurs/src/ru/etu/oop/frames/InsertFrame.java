package ru.etu.oop.frames;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ru.etu.oop.buttons.InsertFrameCanselButton;
import ru.etu.oop.buttons.InsertFrameOkButton;
import ru.etu.oop.data.Controller;

public class InsertFrame extends JFrame {
	
	private final Controller ctrl;
	private final Integer row;
	
	private final static int DEFAULT_WIDTH = 430;
	private final static int DEFAULT_HEIGHT = 200;
	
	public InsertFrame(Object obj, int row, Controller ctrl) {
		
		this.ctrl = ctrl;
		ctrl.setInsertFrame(this);
		
		this.row = row;
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Обновить статус комнаты " + obj.toString());
		setLayout(null);
		
		JLabel label = new JLabel("Введите ФИО постояльца или освободите комнату");
		label.setSize(300, 20);
		label.setLocation(50, 10);
		add(label);
		
		JLabel labelField = new JLabel("ФИО постояльца");
		labelField.setSize(100, 20);
		labelField.setLocation(15, 50);
		add(labelField);
		
		JTextField textFieldFIO = new JTextField("", 20);
		ctrl.setInsertFrameTextField(textFieldFIO);
		textFieldFIO.setSize(270, 20);
		textFieldFIO.setLocation(130, 50);
		add(textFieldFIO);
		
		InsertFrameOkButton ok = new InsertFrameOkButton(ctrl);
		ok.setSize(190, 40);
		ok.setLocation(15, 100);
		add(ok);
		
		InsertFrameCanselButton cansel = new InsertFrameCanselButton(ctrl);
		cansel.setSize(190, 40);
		cansel.setLocation(210, 100);
		add(cansel);

		
	}
	
	public int getRow() {
		return row;
	}


}
