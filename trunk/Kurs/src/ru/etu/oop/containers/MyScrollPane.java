package ru.etu.oop.containers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JScrollPane;
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
	public MyScrollPane(Vector<Room> data, String[] columnNames, final Controller ctrl) {
		
		this.ctrl = ctrl;
		
		table = new JTable(new DefaultTableModel(IOClass.roomsToArrays(data), columnNames)) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
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
				if (e.getClickCount() == 2) {
					InsertFrame frame = new InsertFrame(ctrl);
					frame.setVisible(true);
					
//					table.getModel().getValueAt(rowIndex, columnIndex)
//					System.out.println(table.getSelectedRow());
				}
				
			}
		});
//		super(new JTable(new DefaultTableModel(IOClass.roomsToArrays(data), columnNames) {
//
//			/* (non-Javadoc)
//			 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
//			 */
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//			
//		})); 
	}
	
	
	
}
