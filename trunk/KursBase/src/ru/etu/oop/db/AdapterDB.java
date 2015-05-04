package ru.etu.oop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AdapterDB {

	public static final String BASE_USER = "user";
	public static final String BASE_PASSWORD = "12345678";
	public static final String BASE_URL_FOR_JDBC_DRIVER = "jdbc:postgresql://localhost:5432/kurs";
	
	private final String table;
	private final Connection connection;
	
	public AdapterDB(String table) throws SQLException {
		this.connection = DriverManager.getConnection(BASE_URL_FOR_JDBC_DRIVER, BASE_USER, BASE_PASSWORD);
		this.table = table;
	}
	
	abstract public void doClassQuery() throws SQLException;
	
	public void closeConnection() throws SQLException {
		connection.close();
	}

	protected Connection getConnection() {
		return connection;
	}

	protected String getTable() {
		return table;
	}
	
	
	
	
	
	
	
}
