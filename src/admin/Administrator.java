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
		boolean alreadycreated=false;
		try {
			Class.forName(sqlite_driver);
			
		}catch(ClassNotFoundException ex) {
			System.out.println("* Driver manquant");
		}
		try(Connection c
				= DriverManager.getConnection(path)) {
			Statement s = c.createStatement();
			DatabaseMetaData meta = c.getMetaData();
			
			
			ResultSet r = s.executeQuery("select * from existboolean");
			while(r.next()) {
				alreadycreated = Integer.parseInt(r.getString("exist")) == 1;
				System.out.println();
			}
			
			
			if(!alreadycreated) {
				System.out.println("DB Created");
				s.execute("create table board" + "(name text, row integer , col integer)");
				s.execute("create table row" + "(name text, row_num integer , description text)");
				s.execute("create table existboolean" + "(exist integer)");
				s.executeUpdate("insert into existboolean " + "values(1)" );
				
				//s.executeUpdate("insert into board " + "values ('Board1', 6, 6)");	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
