package ru.etu.oop.frames;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ru.etu.oop.containers.MyScrollPane;
import ru.etu.oop.containers.TableToolBar;
import ru.etu.oop.containers.ToolBar;
import ru.etu.oop.data.Data;

public class MainFrame extends JFrame {
	
	private final static int DEFAULT_WIDTH = 800;
	private final static int DEFAULT_HEIGHT = 746;
	
	public MainFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Администратор гостиницы");
		setLayout(new BorderLayout());
		
		add(new ToolBar("Панель управления"), BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("foo"));
		panel.setBorder(BorderFactory.createTitledBorder("Перечень комнат гостиницы")); 
		panel.setLayout(new BorderLayout());
		panel.add(new TableToolBar("Панель управления таблицей"), BorderLayout.NORTH);		
		String[] col = {"Номер комнаты","Вместимость","ФИО Плательщика"};
		MyScrollPane table = new MyScrollPane(getData(), col);
		panel.add(table, BorderLayout.CENTER);
		
		
		
		
		add(panel, BorderLayout.CENTER);
		
		
		
		
		
		
	}
	
	private Vector getData() {
		return new Data().getRooms();
	}

	
}
