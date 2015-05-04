package ru.etu.oop.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectAdapterDB extends AdapterDB {
	
	private ResultSet resultQuery;
	
	public SelectAdapterDB(String table) throws SQLException {
		super(table);
	}

	@Override
	public void doClassQuery() throws SQLException {
		Statement st = getConnection().createStatement();
		resultQuery = st.executeQuery("SELECT * FROM " + getTable());
	}

	public ResultSet getResultQuery() {
		return resultQuery;
	}
	
}
