package ru.etu.oop.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ru.etu.oop.data.Controller;

public class WorkerFrame extends JDialog{
	
	private final JDialog frame = this;
	private Controller ctrl;
	private JTextField textFieldFIO;
	private JTextField textFieldWork;
	private JTextField textFieldPass;
	private JButton ok;
	private JButton cansel;
	private boolean okPressed = false;

	public WorkerFrame(String name, Controller ctrl) {
		this.ctrl = ctrl;
		
		setSize(430, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Работник " + name);
		setLayout(null);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); 
		
		JLabel labelField1 = new JLabel("ФИО работника");
		labelField1.setSize(100, 20);
		labelField1.setLocation(15, 20);
		add(labelField1);

		JLabel labelField2 = new JLabel("Специальность");
		labelField2.setSize(100, 20);
		labelField2.setLocation(15, 50);
		add(labelField2);
		
		JLabel labelField3 = new JLabel("Номер паспорта");
		labelField3.setSize(100, 20);
		labelField3.setLocation(15, 80);
		add(labelField3);
		
		
		textFieldFIO = new JTextField("", 20);
		textFieldFIO.setSize(270, 20);
		textFieldFIO.setLocation(130, 20);
		add(textFieldFIO);
		
		textFieldWork = new JTextField("", 20);
		textFieldWork.setSize(270, 20);
		textFieldWork.setLocation(130, 50);
		add(textFieldWork);
		
		textFieldPass = new JTextField("", 20);
		textFieldPass.setSize(270, 20);
		textFieldPass.setLocation(130, 80);
		add(textFieldPass);
		
		ok = new JButton("Сохранить", new ImageIcon("./img/confirm.png"));
		ok.setSize(190, 40);
		ok.setLocation(15, 115);
		ok.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				if (Pattern.compile(".[a-zA-Zа-яА-Я\\s]+").matcher(textFieldFIO.getText()).matches()) {
					if (Pattern.compile(".[a-zA-Zа-яА-Я\\s]+").matcher(textFieldWork.getText()).matches()) {
						if (Pattern.compile(".[0-9\\s]+").matcher(textFieldPass.getText()).matches()) {
							
							setOkPressed(true);
							frame.setVisible(false);
														
						} else {
							JOptionPane.showMessageDialog(null, "Некорректный формат пасспорта");
							textFieldPass.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Некорректный формат специальности");
						textFieldWork.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Некорректный формат ФИО");
					textFieldFIO.setText("");
				}
			}
		});
		add(ok);
		
		cansel = new JButton("Отменить", new ImageIcon("./img/fail.png"));
		cansel.setSize(190, 40);
		cansel.setLocation(210, 115);
		cansel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		add(cansel);
		
		
		
		
		
	}

	/**
	 * @return the textFieldFIO
	 */
	public String getFIO() {
		return textFieldFIO.getText();
	}

	/**
	 * @return the textFieldWork
	 */
	public String getWork() {
		return textFieldWork.getText();
	}

	/**
	 * @return the textFieldPass
	 */
	public String getPass() {
		return textFieldPass.getText();
	}

	/**
	 * @return the okPressed
	 */
	public boolean isOkPressed() {
		return okPressed;
	}

	/**
	 * @param okPressed the okPressed to set
	 */
	public void setOkPressed(boolean okPressed) {
		this.okPressed = okPressed;
	}
	
	
	

}
