package ru.etu.oop.containers;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar {

	public ToolBar(String title) {
		
		super(title);
		
		JButton save = new JButton(new ImageIcon("./img/save.png")); 
		save.setToolTipText("Сохранить изменения данных в файл");
		this.add(save);

		
		JButton prices = new JButton(new ImageIcon("./img/dollar.png")); 
		prices.setToolTipText("Прейскурант");
		this.add(prices);
		
		JButton clients = new JButton(new ImageIcon("./img/clients.png")); 
		clients.setToolTipText("Жильцы");
		this.add(clients);
		
		JButton workers = new JButton(new ImageIcon("./img/workers.png")); 
		workers.setToolTipText("Работники");
		this.add(workers);
		
		JButton report = new JButton(new ImageIcon("./img/report.png")); 
		report.setToolTipText("Отчет");
		this.add(report);

	}
	
}
