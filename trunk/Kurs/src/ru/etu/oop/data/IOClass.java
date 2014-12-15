package ru.etu.oop.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class IOClass {
	
	private final static String DATA_FILE = "./data/data.txt";

	public static List<String> getData() {
		
		ArrayList<String> store = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));			
			String line;
			while((line = reader.readLine())!= null)
			{
				store.add(line);
			} 
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return store;		    	

	}

	public static String[][] roomsToArrays(Data data) {
		List<Room> list = data.getRooms();
		String[][] table = new String[list.size()][];
		for (int i = 0; i < table.length; i++) {
			table[i] = new String[3]; 
			table[i][0] = list.get(i).getNumber();
			table[i][1] = list.get(i).getCapacity();
			table[i][2] = list.get(i).getClientFIO();
		}
		return table;
	}

//	public static String[] columnsToArray(Vector<String> columnNames) {
//		String[] strings = new String[columnNames.size()];
//		return columnNames.toArray(strings);
//	}
}
