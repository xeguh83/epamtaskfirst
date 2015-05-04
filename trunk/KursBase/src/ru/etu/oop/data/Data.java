package ru.etu.oop.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ru.etu.oop.db.SelectAdapterDB;

public class Data {

	private List<Room> rooms;
	private final List<Worker> workers;
		
	public Data() {
		
		try {
			rooms = getRoomsFromDB();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ошибка связи с базой данных" + e.toString());
		}
		workers = new ArrayList<Worker>();
		
//		DBAdapter.showRooms();
//		
//		List<String> list = IOClass.getData();
//		for (String string : list) {
//			String[] words = string.split("\\|");
//			if (words.length != 3) return;
//			try {
//				rooms.add(new Room(words[0], words[1], words[2]));	
//			} catch (Exception e) {
//				return;
//			}
//		}
		
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
	
	
	private List<Room> getRoomsFromDB() throws SQLException {
		List<Room> roomList = new ArrayList<>();
		SelectAdapterDB adapter = new SelectAdapterDB("rooms");
		adapter.doClassQuery();
		ResultSet result = adapter.getResultQuery();
		while (result.next()) {
			roomList.add(createRoom(result));
		}
		adapter.closeConnection();
		return roomList;
	}


	private Room createRoom(ResultSet rs) throws SQLException {
		return new Room(Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)), rs.getString(3));
	}


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
