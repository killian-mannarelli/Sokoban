package admin;
import java.sql.*;
import sokoban.*;
import java.util.*;


/**
 * The Class Administrator.
 */
public class Administrator {
	
	/** The text entry. */
	static Scanner textEntry = new Scanner(System.in);
	
	/** The sqlite driver. */
	static String sqlite_driver = "org.sqlite.JDBC";
	
	/** The path. */
	static String path= "jdbc:sqlite:database.db";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	/**
	 * Le main administrateur permettant la gestion de la base de donnée contenant les plateaux
	 * @param args
	 */
	public static void main(String[] args) {
		boolean ongoing=true;
		while(ongoing){
			System.out.println("ADMINISTRATOR MENU   USE WITH CAUTION");
			System.out.println(" - Create Database");
			System.out.println(" - Show Boardlist");
			System.out.println(" - Add Board");
			System.out.println(" - Show Board");
			System.out.println(" - Delete Board");
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
				addBoard(fileToBoard());
				break;
			case "showboard":
				showBoard();
				break;		
			case "deleteboard":
				deleteBoard();
				break;
				
				
			}
		}
		

	}
	
	/**
	 * A method that create a database�.
	 */
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
			
			
			if(alreadycreated) {
				
				System.out.println("DB already exist");
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DB not existing, creating. . .");
			createTables();
			
			
		}
	}
	
	/**
	 * A method that create the tables that are used in the DB.
	 */
	public static void createTables() {
		String sqlite_driver = "org.sqlite.JDBC";
		String path= "jdbc:sqlite:database.db";
		try {
			Class.forName(sqlite_driver);
			
		}catch(ClassNotFoundException ex) {
			System.out.println("* Driver missing");
		}
		try(Connection c
				= DriverManager.getConnection(path)) {
			Statement s = c.createStatement();
			s.execute("create table board" + "(name text, row integer , col integer)");
			s.execute("create table row" + "(name text, row_num integer , description text)");
			s.execute("create table existboolean" + "(exist integer)");
			s.executeUpdate("insert into existboolean " + "values(1)" );
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * A method that list all the boards that the DB contains. Showing for each, it id and size.
	 */
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
	
	/**
	 * Add a board to the DB.
	 *
	 * @param b The board to add
	 */
	public static void addBoard(Board b) {
		int num = 0;
		System.out.println("Give Board id for the database");
		String id = textEntry.nextLine();
		try(Connection c
				= DriverManager.getConnection(path)) {
			
			if(id.equals("quit")) {
				throw new PlayerLeaveException("Admin left");
			}
			Statement s = c.createStatement();
			
			for(String str :b.rowsToString()) {
				PreparedStatement ps = c.prepareStatement("insert into row values (? , "+num+" , ?)");
				ps.setString(2, str);
				ps.setString(1, id);
				ps.executeUpdate();
				num++;
			}
			PreparedStatement ps = c.prepareStatement("insert into board values  (?,"+b.getRow()+","+b.getCol()+")");
			ps.setString(1, id);
			ps.executeUpdate();
			
		} catch (SQLException | PlayerLeaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Transform a textfile into a Board .
	 *
	 * @return The board build using the textfile.
	 */
	public static Board fileToBoard() {
		String fileName = "";
		System.out.println("Give Boardfile name without .txt");
		fileName = textEntry.nextLine();
		try {
			if(fileName.equals("quit")) {
				throw new PlayerLeaveException("Admin left");
			}
		}
		catch(PlayerLeaveException ex) {
			System.out.println(ex.getMessage());
		}
		
		fileName = fileName + ".txt";
		FileBoardBuilder builder = new FileBoardBuilder(fileName);
		Board b;
		try {
			b= builder.build();
			return b;
		}
		catch(BuilderException e) {
			System.out.println("Error with the Board building");
			return null;
		}
		
	}
	/**
	 * A method that show a board in the terminal, it first ask for the board id to search in the DB.
	 */
	public static void showBoard() {
		listBoards();
		System.out.println("Give Board id for the database");
		String id = textEntry.nextLine();
		try(Connection c
				= DriverManager.getConnection(path)){
			if(id.equals("quit")) {
				throw new PlayerLeaveException("Admin left");
			}
			TextBoardBuilder t= new TextBoardBuilder();
			PreparedStatement ps = c.prepareStatement("select description from row where name = ?");
			ps.setString(1, id);
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				t.addRow(r.getString(1));
				//System.out.println(r.getString(1));
			}
			Board b = t.build();
			b.printBoard();
		}
		catch (SQLException | BuilderException | PlayerLeaveException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Incorrect id please try again");
			showBoard();
		}
	}
	
	/**
	 * A method that return a Board which is in the DB using it id.
	 *
	 * @param id The board id
	 * @return The board asked+
	 */
	public static Board getBoardWithId(String id) {
		try(Connection c
				= DriverManager.getConnection(path)){
			
			
			TextBoardBuilder t = new TextBoardBuilder();
			PreparedStatement ps = c.prepareStatement("select description from row where name = ?");
			ps.setString(1, id);
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				t.addRow(r.getString(1));
				//System.out.println(r.getString(1));
			}
			Board b = t.build();
			return b;
			
		} catch (SQLException | BuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * !Danger! A method that delete a Board from the DB, it ask first for the Board.
	 */
	public static void deleteBoard() {
		try(Connection c
				= DriverManager.getConnection(path)){
			listBoards();
			System.out.println("Give the id that you want to delete from the DB : ");
			String id = textEntry.nextLine();
			if(id.equals("quit")) {
				throw new PlayerLeaveException("Admin left");
			}
			PreparedStatement ps = c.prepareStatement("delete from row where name = ?");
			ps.setString(1, id);
			ps.execute();
			 ps = c.prepareStatement("delete from board where name = ?");
			 ps.setString(1, id);
			 ps.execute();
			 System.out.println("Table deleted : " + id);
		} catch (SQLException | PlayerLeaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	
	/**
	 * Enlève les espaces d'une chaîne de caractère en plus de la mettre totalement en minuscule.
	 *
	 * @param string La chaîne contenant des espaces et des majuscules
	 * @return La chaîne sans espaces et en minuscule.
	 */
	private static String removeSpaces(String string) {
        return string.replaceAll("\\s+", "").toLowerCase();
    }

}
