package ru.etu.oop.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class pricesFrame extends JDialog{
	
	JDialog frame = this;

	public pricesFrame() {
		
		setSize(350, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Прейскурант цен на комнаты");
		setLayout(null);
		
		JLabel label1 = new JLabel("Цена за 1 сутки 1-местной комнаты - 90$");
		label1.setSize(300, 20);
		label1.setLocation(50, 20);
		add(label1);
		
		JLabel label2 = new JLabel("Цена за 1 сутки 2-местной комнаты - 130$");
		label2.setSize(300, 20);
		label2.setLocation(50, 50);
		add(label2);
		
		JLabel label3 = new JLabel("Цена за 1 сутки 1-местной комнаты - 170$");
		label3.setSize(300, 20);
		label3.setLocation(50, 80);
		add(label3);
		
		JButton button = new JButton("Закрыть");
		button.setSize(190, 40);
		button.setLocation(80, 120);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		add(button);
		
	}
	
	
}
