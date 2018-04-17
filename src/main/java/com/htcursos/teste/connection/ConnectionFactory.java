package com.htcursos.teste.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public static Connection getConnection() {
		String server = "localhost";
		String port = "3306";
		String database = "teste";

		String user = "root";
		String passwd = "alison";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + server + ":" + port + "/" + database;
			return DriverManager.getConnection(url, user, passwd);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
