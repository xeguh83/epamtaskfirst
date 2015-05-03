package ru.etu.oop.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAdapter {
	
	public static final String BASE_USER = "user";
	public static final String BASE_PASSWORD = "12345678";
	public static final String BASE_URL_FOR_JDBC_DRIVER = "jdbc:postgresql://localhost:5432/kurs";
	
	public static void tryConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(BASE_URL_FOR_JDBC_DRIVER, BASE_USER, BASE_PASSWORD);
			System.out.println("Соединение установлено!");
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

}
