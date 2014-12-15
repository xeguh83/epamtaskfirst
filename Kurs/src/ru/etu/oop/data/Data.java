package ru.etu.oop.data;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private final List<Room> rooms;
		
	public Data() {
		
		rooms = new ArrayList<Room>();
		List<String> list = IOClass.getData();
		for (String string : list) {
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
	public List<Room> getRooms() {
		return rooms;
	}


	public void updateRoom(int index, Room curRoom) {
		rooms.set(index, curRoom);
	}



}
