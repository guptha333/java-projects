package application.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

import application.main.Main;

public class DBConnection {
	static Connection conn;

	private DBConnection() {
	}

	public static Connection open() {
		try {
			DriverManager.registerDriver(new Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
		} catch (Exception e) {
			Main.writeToLog("while DBConnection opening !!", e);
		}
		return conn;
	}

	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			Main.writeToLog("while DBConnection closing !!", e);
		}
	}
}
