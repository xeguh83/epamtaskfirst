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
import ru.etu.oop.data.Controller;
import ru.etu.oop.data.Data;

public class MainFrame extends JFrame {
	
	private final Controller ctrl;
	
	private final static int DEFAULT_WIDTH = 800;
	private final static int DEFAULT_HEIGHT = 746;
	
	public MainFrame() {
		
		this.ctrl = new Controller();
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("������������� ���������");
		setLayout(new BorderLayout());
		
		add(new ToolBar("������ ����������"), BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.add(new JLabel("foo"));
		panel.setBorder(BorderFactory.createTitledBorder("�������� ������ ���������")); 
		panel.setLayout(new BorderLayout());
		panel.add(new TableToolBar("������ ���������� ��������"), BorderLayout.NORTH);		
		String[] col = {"����� �������","�����������","��� �����������"};
		MyScrollPane table = new MyScrollPane(getData(), col, ctrl);
		panel.add(table.getTable(), BorderLayout.CENTER);
		
		
		
		
		add(panel, BorderLayout.CENTER);
		
		
		
		
		
		
	}
	
	private Vector getData() {
		return new Data().getRooms();
	}

	
}
