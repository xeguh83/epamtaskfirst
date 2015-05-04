package ru.etu.oop.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ru.etu.oop.db.SelectAdapterDB;

public class Data {

	private List<Room> rooms;
	private List<Worker> workers;
		
	public Data() {
		
		try {
			rooms = getRoomsFromDB();
			workers = getWorkersFromDB();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ошибка связи с базой данных: " + e.toString());
		}

	}
	
	
	private List<Worker> getWorkersFromDB() throws SQLException {
		List<Worker> roomList = new ArrayList<>();
		SelectAdapterDB adapter = new SelectAdapterDB("workers");
		adapter.doClassQuery();
		ResultSet result = adapter.getResultQuery();
		while (result.next()) {
			roomList.add(createWorker(result));
		}
		adapter.closeConnection();
		return roomList;
	}


	private Worker createWorker(ResultSet rs) throws SQLException {
		return new Worker(rs.getString(1), rs.getString(2), rs.getString(3));
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
