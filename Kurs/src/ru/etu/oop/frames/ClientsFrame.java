package ru.etu.oop.frames;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ru.etu.oop.data.Controller;

public class ClientsFrame extends JDialog{
	
	private Controller ctrl;
	private JTable table;

	public ClientsFrame(Controller ctrl) {
		this.ctrl = ctrl;
		
		setSize(600, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Перечень постояльцев гостиницы");
		setLayout(new BorderLayout());
		
		final String[] col = {"ФИО Плательщика","Номер комнаты"};
		final String[][] tableData = ctrl.getClientsTableData();
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
