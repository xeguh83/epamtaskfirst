package ru.etu.oop.containers;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ru.etu.oop.data.IOClass;
import ru.etu.oop.data.Room;


public class MyScrollPane extends JScrollPane {
	
	public MyScrollPane(Vector<Room> data, String[] columnNames) {
		
		super(new JTable(new DefaultTableModel(IOClass.roomsToArrays(data), columnNames) {

			/* (non-Javadoc)
			 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		})); 
	}
	
	
	
}
