package ru.etu.oop.data;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private final List<Room> rooms;
	private final List<Worker> workers;
		
	public Data() {
		
		rooms = new ArrayList<Room>();
		workers = new ArrayList<Worker>();
		
		DBAdapter.showRooms();
		
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
		
		List<String> workerList = IOClass.getWorkers();
		for (String string : workerList) {
			String[] word = string.split("\\|");
			if (word.length != 3) return;
			try {
				workers.add(new Worker(word[0], word[1], word[2]));
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
	
	public List<Worker> getWorkers() {
		return workers;
	}

	public void updateRoom(int index, Room curRoom) {
		rooms.set(index, curRoom);
	}



}
