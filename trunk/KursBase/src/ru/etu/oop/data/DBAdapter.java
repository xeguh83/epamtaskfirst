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
	
	public static void tryConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(BASE_URL_FOR_JDBC_DRIVER, BASE_USER, BASE_PASSWORD);
			System.out.println("���������� �����������!");
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
	}
	
	public static void showRooms() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(BASE_URL_FOR_JDBC_DRIVER, BASE_USER, BASE_PASSWORD);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM rooms");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3));
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
	}
	
	public static List<Room> getRooms() {
		List<Room> roomList = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(BASE_URL_FOR_JDBC_DRIVER, BASE_USER, BASE_PASSWORD);
			roomList = new ArrayList<>();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM rooms");
			while (rs.next()) {
				roomList.add(new Room(Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(2)), rs.getString(3)));
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

}
