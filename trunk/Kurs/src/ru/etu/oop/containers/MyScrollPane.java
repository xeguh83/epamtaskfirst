package ru.etu.oop.containers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ru.etu.oop.data.Controller;
import ru.etu.oop.data.IOClass;
import ru.etu.oop.data.Room;
import ru.etu.oop.frames.InsertFrame;


public class MyScrollPane {
	
	private final Controller ctrl;
	private JTable table;
	
	public JTable getTable() {
		return table;
	}
	public MyScrollPane(List<Room> data, String[] columnNames, final Controller ctrl) {
		
		
//		table = new JTable(new DefaultTableModel(IOClass.roomsToArrays(data), columnNames)) {
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				// TODO Auto-generated method stub
//				return false;
//			}
	//	};
		
		this.ctrl = ctrl;
		ctrl.setRoomTable(this);
	

		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					InsertFrame frame = new InsertFrame(table.getModel().getValueAt(table.getSelectedRow(), 0), table.getSelectedRow(), ctrl);
//					frame.setVisible(true);
//				}
				
			}
		});

	}
	public void refresh(Object fio, int rowIndex, int columnIndex) {
		table.getModel().setValueAt(fio, rowIndex, columnIndex);
	}
	
	
	
}
