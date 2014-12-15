package ru.etu.oop.containers;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

	public ToolBar(String title) {
		
		super(title);
		
		JButton save = new JButton(new ImageIcon("./img/save.png")); 
		save.setToolTipText("��������� ��������� ������ � ����");
		this.add(save);

		
		JButton prices = new JButton(new ImageIcon("./img/dollar.png")); 
		prices.setToolTipText("�����������");
		this.add(prices);
		
		JButton clients = new JButton(new ImageIcon("./img/clients.png")); 
		clients.setToolTipText("������");
		this.add(clients);
		
		JButton workers = new JButton(new ImageIcon("./img/workers.png")); 
		workers.setToolTipText("���������");
		this.add(workers);
		
		JButton report = new JButton(new ImageIcon("./img/report.png")); 
		report.setToolTipText("�����");
		this.add(report);

	}
	
}
