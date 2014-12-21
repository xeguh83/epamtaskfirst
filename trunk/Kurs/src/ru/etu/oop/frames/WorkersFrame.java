package ru.etu.oop.frames;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import ru.etu.oop.data.Controller;

public class WorkersFrame extends JDialog{
	
	private final Controller ctrl;
	private JToolBar toolBar;
	private JButton add;
	private JButton edit;
	private JButton delete;
	private JTable table;

	public WorkersFrame(final Controller ctrl) {
		this.ctrl = ctrl;
		
		setSize(700, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Перечень работников гостиницы");
		setLayout(new BorderLayout());
		
		toolBar = new JToolBar("Панель управления");
		
		add = new JButton(new ImageIcon("./img/add.png")); 
		add.setToolTipText("Добавить");
		toolBar.add(add);

		edit = new JButton(new ImageIcon("./img/edit.png")); 
		edit.setToolTipText("Редактировать");
		toolBar.add(edit);
		
		delete = new JButton(new ImageIcon("./img/delete.png")); 
		delete.setToolTipText("Удалить");
		toolBar.add(delete);

		add(toolBar, BorderLayout.NORTH);
		
		final String[] col = {"ФИО Работника","Специальность","Номер паспорта"};
		final String[][] tableData = ctrl.getWorkersTableData();
		DefaultTableModel model = new DefaultTableModel(tableData, col);
		table = new JTable(model){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		
	}
	
}
