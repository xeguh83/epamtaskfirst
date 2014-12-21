package ru.etu.oop.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ru.etu.oop.data.Controller;

public class InsertFrame extends JDialog {
	
	private final JDialog frame = this;
	private final Controller ctrl;
	private final JTextField textFieldFIO;
	private final JButton ok;
	private final JButton cansel;
	private boolean correctlyClosed = false;
	

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
		textFieldFIO.setSize(270, 20);
		textFieldFIO.setLocation(130, 50);
		add(textFieldFIO);
		
		ok = new JButton("Заселить клиента", new ImageIcon("./img/insert.png"));
		ok.setSize(190, 40);
		ok.setLocation(15, 100);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textFieldFIO.getText();
				if (Pattern.compile(".[a-zA-Zа-яА-Я\\s]+").matcher(text).matches()) {
					ctrl.updateDataFromField(selectedRow, textFieldFIO.getText());
					setCorrectlyClosed(true);
					frame.setVisible(false);					
				} else {
					JOptionPane.showMessageDialog(null, "Некорректный формат ФИО");
					textFieldFIO.setText("");
				}
			}
		});
		add(ok);
		
		cansel = new JButton("Освободить комнату", new ImageIcon("./img/free.png"));
		cansel.setSize(190, 40);
		cansel.setLocation(210, 100);
		cansel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldFIO.setText("-");
				ctrl.updateDataFromField(selectedRow, "-");
				setCorrectlyClosed(true);
				frame.setVisible(false);
			}
		});
		add(cansel);

	}

	public String getFieldText() {
		return textFieldFIO.getText();
	}

	/**
	 * @return the correctlyClosed
	 */
	public boolean isCorrectlyClosed() {
		return correctlyClosed;
	}
	
	/**
	 * @param correctlyClosed the correctlyClosed to set
	 */
	public void setCorrectlyClosed(boolean correctlyClosed) {
		this.correctlyClosed = correctlyClosed;
	}
}
