package ru.etu.oop.frames;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;

import ru.etu.oop.containers.MyScrollPane;
import ru.etu.oop.containers.TableToolBar;
import ru.etu.oop.containers.ToolBar;
import ru.etu.oop.data.Controller;
import ru.etu.oop.data.Data;

public class MainFrame extends JFrame {
	
	private final Controller ctrl;
	
	private final JToolBar toolBar;
	private final JButton save, prices, clients, workers, report;
	private final JPanel panel;
	private final JTable table;
	
	public MainFrame(final Controller ctrl) {
		
		this.ctrl = ctrl;
		
		//параметры главного окна
		setSize(800, 746);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Администратор гостиницы");
		setLayout(new BorderLayout());
		
		//панель управления и её кнопки
		toolBar = new JToolBar("Панель управления");
		
		save = new JButton(new ImageIcon("./img/save.png")); 
		save.setToolTipText("Сохранить изменения данных в файл");
		toolBar.add(save);

		prices = new JButton(new ImageIcon("./img/dollar.png")); 
		prices.setToolTipText("Прейскурант");
		toolBar.add(prices);
		
		clients = new JButton(new ImageIcon("./img/clients.png")); 
		clients.setToolTipText("Жильцы");
		toolBar.add(clients);
		
		workers = new JButton(new ImageIcon("./img/workers.png")); 
		workers.setToolTipText("Работники");
		toolBar.add(workers);
		
		report = new JButton(new ImageIcon("./img/report.png")); 
		report.setToolTipText("Отчет");
		toolBar.add(report);
		
		add(toolBar, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.add(new JLabel("foo"));
		panel.setBorder(BorderFactory.createTitledBorder("Перечень комнат гостиницы")); 
		panel.setLayout(new BorderLayout());
		String[] col = {"Номер комнаты","Вместимость","ФИО Плательщика"};
		table = new JTable(ctrl.getTableData(), col){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String newFIO = ctrl.getNewFIO(table.getSelectedRow());
//					InsertFrame frame = new InsertFrame(table.getModel().getValueAt(table.getSelectedRow(), 0), table.getSelectedRow(), ctrl);
//					frame.setVisible(true);
				}
			}
		});
		panel.add(table, BorderLayout.CENTER);
	
		add(panel, BorderLayout.CENTER);
		
		
		
		
		
		
	}
	
}
