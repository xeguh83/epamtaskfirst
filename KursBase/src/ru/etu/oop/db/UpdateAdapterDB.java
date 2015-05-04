package ru.etu.oop.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ru.etu.oop.data.Room;
import ru.etu.oop.data.Worker;

public class UpdateAdapterDB extends AdapterDB {

	private List<Room> roomList;
	private List<Worker> workerList;

	public UpdateAdapterDB(String table, List<?> entityList) throws SQLException {
		super(table);
		if (table.equalsIgnoreCase("rooms")) {
			roomList = (List<Room>) entityList;
		} else if (table.equalsIgnoreCase("workers")) {
			workerList = (List<Worker>) entityList;
		} else {
			throw new ClassCastException("Неверный тип сущности в списке для помещения в базу данных");
		}
	}

	@Override
	public void doClassQuery() throws SQLException {
		Statement st = getConnection().createStatement();
		if (getTable().equalsIgnoreCase("rooms")) {
			for (Room room : roomList) {
				updateRoomRowInDB(st, room);
			}
		} else if (getTable().equalsIgnoreCase("workers")) {
			for (Worker worker : workerList) {
				updateWorkerRowInDB(st, worker);
			}
		} else {
			throw new ClassCastException("Неверный тип сущности в списке для помещения в базу данных");
		}
		
		
	}

	private void updateWorkerRowInDB(Statement st, Worker worker) throws SQLException {
		if (isWorkerExistInTable(st, worker)) {
			st.executeUpdate("UPDATE " + getTable() + " SET fio = '" 
					+ worker.getFIO() + "' WHERE passport = '" + worker.getPassport() + "';");
			st.executeUpdate("UPDATE " + getTable() + " SET work = '" 
					+ worker.getWork() + "' WHERE passport = '" + worker.getPassport() + "';");
			st.executeUpdate("UPDATE " + getTable() + " SET is_deleted = 'FALSE' WHERE passport = '" 
					+ worker.getPassport() + "';");
		} else {
			
		}
	}

	private boolean isWorkerExistInTable(Statement st, Worker worker) throws SQLException {
		ResultSet resultQuery = st.executeQuery("SELECT * FROM " + getTable());
		while (resultQuery.next()) {
			if (resultQuery.getString(3).equals(worker.getPassport())) {
				return true;
			}
		}
		return false;
	}

	private void updateRoomRowInDB(Statement st, Room room) throws SQLException {
		st.executeUpdate("UPDATE " + getTable() + " SET owner = '" 
				+ room.getClientFIO() + "' WHERE room_number = '" + room.getNumber() + "';");
	}

}
