package admin;
import java.sql.*;
import sokoban.*;

public class Administrator {

	public static void main(String[] args) {
		createDatabase();

	}
	
	public static void createDatabase() {
		String sqlite_driver = "org.sqlite.JDBC";
		String path= "jdbc:sqlite:database.db";
		try {
			Class.forName(sqlite_driver);
			
		}catch(ClassNotFoundException ex) {
			System.out.println("* Driver manquant");
		}
		try(Connection c
				= DriverManager.getConnection(path)) {
			
			//Statement s = c.createStatement();
			//String sql = "CREATE DATABASE SOKOBAN";
			//s.executeUpdate(sql);
			DatabaseMetaData meta = c.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
