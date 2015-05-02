package ru.etu.oop.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] worker = ctrl.getNewWorker();
				if (worker != null && worker.length == 3) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(worker);
				}
			}
		});
		
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row > -1) {
					String[] worker = ctrl.updateWorker(row);
					if (worker != null && worker.length == 3) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						for (int i = 0; i < 3; i++) {							
							model.setValueAt(worker[i], row, i);;
						}
					}
				}
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row > -1) {
					int ask = JOptionPane.showConfirmDialog(null, "Вы действительно хотите удалить работника?", "Внимание!", 0);
					if (ask < 1) {
						ctrl.deleteWorkerFromData(row);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(row);
					}
					
				}
			}
		});
		
	}
	
}
