package ru.etu.oop.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;

import ru.etu.oop.data.Controller;

public class ReportFrame extends JDialog {
	
	private Controller ctrl;
	private JDialog frame = this;

	public ReportFrame(Controller ctrl) {
		this.ctrl = ctrl;
		
		setSize(350, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Отчет о деятельности гостиницы");
		setLayout(null);
		
		JLabel label1 = new JLabel("Количество работающего персонала: " + ctrl.getWorkersCount() + " чел.");
		label1.setSize(300, 20);
		label1.setLocation(30, 20);
		add(label1);
		
		JLabel label2 = new JLabel("Количество проживающих клиентов: " + ctrl.getClientsCount() + " чел.");
		label2.setSize(300, 20);
		label2.setLocation(30, 50);
		add(label2);
		
		JLabel label3 = new JLabel("Доход гостиницы за сегодня составляет: " + ctrl.getPaid() + "$");
		label3.setSize(300, 20);
		label3.setLocation(30, 80);
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
