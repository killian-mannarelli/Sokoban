package admin;
import java.sql.*;
import sokoban.*;
import java.util.*;

public class Administrator {
	static Scanner textEntry = new Scanner(System.in);
	static String sqlite_driver = "org.sqlite.JDBC";
	static String path= "jdbc:sqlite:database.db";
	public static void main(String[] args) {
		boolean ongoing=true;
		while(ongoing){
			System.out.println("ADMINISTRATOR MENU   USE WITH CAUTION");
			System.out.println(" - Create Database");
			System.out.println(" - Show Boardlist");
			System.out.println(" - Add Board");
			System.out.println(" - Quit");
			
			String command = removeSpaces(textEntry.nextLine());
			switch(command) {
			case "quit":
				System.out.println("Bye bye");
				ongoing = false;
				break;
			case "createdatabase" :
				createDatabase();
				break;
			case "showboardlist":
				listBoards();
				break;
			case "addboard":
				FileBoardBuilder build = new FileBoardBuilder();
				try {
					Board b = build.build();
					addBoard("test2",b);
					/**
					for(String str : b.rowsToString()) {
						System.out.println(str);
					*/
				}
				catch(BuilderException e) {
					System.out.println("Error with the Board building");
				}
				
			}
		}
		

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
			else {
				System.out.println("DB already exist");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void listBoards() {
		
		try(Connection c
				= DriverManager.getConnection(path)) {
			
			
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("select * from board");
			while(r.next()) {
				
				System.out.println("name :" +" " +r.getString("name") + " "+ r.getInt("row") +" " +r.getInt("col"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addBoard(String id, Board b) {
		int num = 0;
		try(Connection c
				= DriverManager.getConnection(path)) {
			
			
			Statement s = c.createStatement();
			
			for(String str :b.rowsToString()) {
				PreparedStatement ps = c.prepareStatement("insert into row values ('"+id+"' , "+num+" , ?)");
				//ps.setString(0, String.valueOf(num));
				ps.setString(1, str);
				ps.executeUpdate();
				num++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String removeSpaces(String string) {
        return string.replaceAll("\\s+", "").toLowerCase();
    }

}
