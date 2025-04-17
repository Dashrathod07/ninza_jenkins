package com.ninza.hrm.DatabaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;
import com.ninza.hrm.FileUtility.PropertyFileUtility;

public class DataBaseUtility {
	PropertyFileUtility plib = new PropertyFileUtility();
	static Connection conn = null;
	static Resultset result = null;

	public void getdbConnection() throws Exception {
		String url = plib.getDataFromPropertiesFile("DBUrl");
		String username = plib.getDataFromPropertiesFile("DB_username");
		String password = plib.getDataFromPropertiesFile("DB_password");
		Driver driver;
		try {
			driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
		}
	}

	public void getMYSQLdbConnection() throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/", "root", "root");
		} catch (Exception e) {
		}
	}

	public void closedbConnection() throws SQLException {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}

	public ResultSet executeSelectQuery(String query) throws SQLException {

		try {
			Statement stat = conn.createStatement();
			result = (Resultset) stat.executeQuery(query);
		} catch (Exception e) {
		}
		return (ResultSet) result;
	}

	public int executeNonSelectQuery(String query) throws Exception {
		/*int result = 0;
		System.out.println("Executing SQL: " + query);
			Statement stat = conn.createStatement();
			result = stat.executeUpdate(query);
		
		if(result==0) {
			System.out.println("The Resource is not Created in DataBase");
			return result;
		}
		else {
			System.out.println("The Resource is Created in DataBase");
			return result;
		}*/
		int result;

	    System.out.println("Executing SQL: " + query);

	    try (Statement stat = conn.createStatement()) {
	        result = stat.executeUpdate(query);
	    }

	    if (result == 0) {
	        System.out.println("The Resource is not Created in DataBase");
	    } else {
	        System.out.println("The Resource is Created in DataBase");
	    }

	    return result;
	}
		

	public boolean executeQueryVerifyAndGetData(String query, int columnName, String expectedData)
			throws Exception {
		boolean flag = false;
		result = (Resultset) conn.createStatement().executeQuery(query);
		while (((ResultSet) result).next()) {
			if (((ResultSet) result).getString(columnName).equals(expectedData)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			System.out.println(expectedData + "===> Data is Verified in DataBase");
			return true;
		} else {
			System.out.println(expectedData + "===> Data is not Verified in DataBase");
			return false;
		}
	}
}
