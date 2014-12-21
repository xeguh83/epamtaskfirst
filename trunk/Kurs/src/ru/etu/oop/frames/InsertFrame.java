package ru.etu.oop.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ru.etu.oop.buttons.InsertFrameCanselButton;
import ru.etu.oop.buttons.InsertFrameOkButton;
import ru.etu.oop.data.Controller;

public class InsertFrame extends JDialog {
	
	private final JDialog frame = this;
	private final Controller ctrl;
	private final JTextField textFieldFIO;
	private final InsertFrameOkButton ok;
	private final InsertFrameCanselButton cansel;
	
	public InsertFrame(String roomNumber, final int selectedRow, final Controller ctrl) {
		this.ctrl = ctrl;
		
		setSize(430, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Обновить статус комнаты " + roomNumber);
		setLayout(null);
		
		JLabel label = new JLabel("Введите ФИО постояльца или освободите комнату");
		label.setSize(300, 20);
		label.setLocation(50, 10);
		add(label);
		
		JLabel labelField = new JLabel("ФИО постояльца");
		labelField.setSize(100, 20);
		labelField.setLocation(15, 50);
		add(labelField);
		
		textFieldFIO = new JTextField("", 20);
		ctrl.setInsertFrameTextField(textFieldFIO);
		textFieldFIO.setSize(270, 20);
		textFieldFIO.setLocation(130, 50);
		add(textFieldFIO);
		
		ok = new InsertFrameOkButton(ctrl);
		ok.setSize(190, 40);
		ok.setLocation(15, 100);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.updateTableFromField(selectedRow, textFieldFIO.getText());
				frame.dispose();
			}
		});
		add(ok);
		
		cansel = new InsertFrameCanselButton(ctrl);
		cansel.setSize(190, 40);
		cansel.setLocation(210, 100);
		cansel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.updateTableFromField(selectedRow, "-");
				frame.dispose();
			}
		});
		add(cansel);

	}

}
