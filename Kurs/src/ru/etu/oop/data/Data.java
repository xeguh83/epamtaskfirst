package ru.etu.oop.data;

import java.util.Vector;

public class Data {

	private final Vector<Room> rooms;
		
	public Data() {
		
		rooms = new Vector<Room>();
		String[] data = IOClass.getData();
		for (String string : data) {
			String[] words = string.split("\\|");
			if (words.length != 3) return;
			try {
				rooms.add(new Room(words[0], words[1], words[2]));	
			} catch (Exception e) {
				return;
			}
			
		}
		
	}
	
	
	/**
	 * @return the rooms
	 */
	public Vector<Room> getRooms() {
		return rooms;
	}



}
