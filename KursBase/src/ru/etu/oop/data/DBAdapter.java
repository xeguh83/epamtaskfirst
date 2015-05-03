package ru.etu.oop.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBAdapter {
	
	public static final String BASE_USER = "user";
	public static final String BASE_PASSWORD = "12345678";
	public static final String BASE_URL_FOR_JDBC_DRIVER = "jdbc:postgresql://localhost:5432/kurs";
	

	public static List<Room> getRooms() {
		List<Room> roomList = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		try {
			rs = connectAndQuery(con);
			while (rs.next()) {
				roomList.add(createRoom(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
				con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return roomList;
		
	}


	private static Room createRoom(ResultSet rs) throws SQLException {
		return new Room(Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)), rs.getString(3));
	}


	private static ResultSet connectAndQuery(Connection con) throws SQLException {
		Statement st = null;
		con = DriverManager.getConnection(BASE_URL_FOR_JDBC_DRIVER, BASE_USER, BASE_PASSWORD);
		st = con.createStatement();
		return st.executeQuery("SELECT * FROM rooms");
	}

}
