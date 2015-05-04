package ru.etu.oop.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import ru.etu.oop.data.Controller;

public class MainFrame extends JFrame {
	
	private final Controller ctrl;
	
	private final JToolBar toolBar;
	private final JButton save, prices, clients, workers, report;
//	private final JPanel panel;
	private final JTable table;
	
	@SuppressWarnings("serial")
	public MainFrame(final Controller ctrl) {
		
		this.ctrl = ctrl;
		
		//��������� �������� ����
		setSize(800, 677);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("������������� ���������");
		setLayout(new BorderLayout());
		
		//������ ���������� � � ������
		toolBar = new JToolBar("������ ����������");
		
		save = new JButton(new ImageIcon("./img/save.png")); 
		save.setToolTipText("��������� ��������� ������ � ����");
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.saveDataToDB();
			}
		});
		toolBar.add(save);

		prices = new JButton(new ImageIcon("./img/dollar.png")); 
		prices.setToolTipText("�����������");
		prices.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.showPrises();
			}
		});
		toolBar.add(prices);
		
		clients = new JButton(new ImageIcon("./img/clients.png")); 
		clients.setToolTipText("������");
		clients.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.showClients();
			}
		});
		toolBar.add(clients);
		
		workers = new JButton(new ImageIcon("./img/workers.png")); 
		workers.setToolTipText("���������");
		workers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.showWorkers();
			}
		});
		toolBar.add(workers);
		
		report = new JButton(new ImageIcon("./img/report.png")); 
		report.setToolTipText("�����");
		report.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.showReport();
			}
		});
		toolBar.add(report);
		
		add(toolBar, BorderLayout.NORTH);

		final String[] col = {"����� �������","�����������","��� �����������"};
		final String[][] tableData = ctrl.getTableData();
		DefaultTableModel model = new DefaultTableModel(tableData, col);
		table = new JTable(model){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int selectedRow = table.getSelectedRow();
					String newFIO = ctrl.getNewFIO(selectedRow);
					table.getModel().setValueAt(newFIO, selectedRow, 2);
				}
			}
		});
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
}
