package sokoban;

import java.util.ArrayList;
import java.util.Scanner;
import admin.Administrator;


/**
 * The Class Game containing all the methods that the game use to be playable.
 */
public class Game {
	
	/** The game board. */
	public static Board gameBoard;
	
	/** The scanner used for interaction with the player. */
	private static final Scanner in = new Scanner(System.in);
	
	/** The play status. */
	public static boolean playStatus;



	
	
	
	/**
	 * A method that launch a new game.
	 */
	public static void Play()  {
		try {
			System.out.println("Welcome to Sokoban");
			System.out.println("you can quit at any time by writing quit");
			System.out.println("First choose you board by typing the EXACT id as written on this terminal.");
			System.out.println("Then press U to go Up, L to go Left, R to go Right, and D to go Down.");
			System.out.println("Good luck !");
			gameBoard = chooseBoard();
			playStatus=true;
			char input = '0';
			gameBoard.printBoard();
			while(playStatus) {
				while(input == '0') {
					input=commandInput();
				}
				if(possibleMovePlayer(input)) {
					playerMove(input);
					input='0';
					gameBoard.printBoard();
				}
				else {
					System.out.println("Invalid move, try again:");
					input='0';
					gameBoard.printBoard();
				}
				if(winCondition()) {
					playStatus =false;
					System.out.println("You win congrats !");
					Play();
				}
			}	
		}
		catch(PlayerLeaveException  ex) {
			
			
				System.out.println(ex.getMessage());
			
			
		}
		catch(IndexOutOfBoundsException ex){
			System.out.println("Incorrect id please try again !");
			Play();
		}
		
	}
	
	/**
	 * A method that prompt the player for what he want to do.
	 *
	 * @return The action 
	 * @throws PlayerLeaveException the player leave exception
	 */
	public static char commandInput() throws PlayerLeaveException {
		System.out.println("Enter U or D or L or R :");
		String input = in.nextLine().trim();
		 if(input.equals("quit")) {
			throw new PlayerLeaveException("Player left");
		}
		 else if(input.charAt(0)!='U' && input.charAt(0)!='L'&& input.charAt(0)!='R' && input.charAt(0)!='D' ) {
			return '0';
		}
		
		else {
			System.out.println(input.charAt(0));
			return input.charAt(0);
			
		}
		
	}
	
	/**
	 * Choose board by searching in the DB.
	 *
	 * @return the board
	 * @throws PlayerLeaveException the player leave exception
	 */
	public static Board chooseBoard() throws PlayerLeaveException {
		Administrator.listBoards();
		System.out.println("Choose your board by typing the id:");
		String input = in.nextLine().trim();
		if(input.equals("quit")) {
			throw new PlayerLeaveException("Player left");
		}
		return Administrator.getBoardWithId(input);
		
	}
	
	/**
	 * Move the player according to a char input.
	 *
	 * @param input the input (direction)
	 */
	public static void playerMove(char input) {
		
		switch(input) {
			case 'L':
				if(crateInThatDirection(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-1)) {
					crateMove(gameBoard.getPlayercase().getCol()-1,gameBoard.getPlayercase().getRow(), 'L');
				}
				gameBoard.movePlayer(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-1);
				break;
				
			case 'R':
				if(crateInThatDirection(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+1)) {
					crateMove(gameBoard.getPlayercase().getCol()+1,gameBoard.getPlayercase().getRow(), 'R');
				}
				gameBoard.movePlayer(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+1);
				break;
			case 'U':
				if(crateInThatDirection(gameBoard.getPlayercase().getRow()-1, gameBoard.getPlayercase().getCol())) {
					crateMove(gameBoard.getPlayercase().getCol(),gameBoard.getPlayercase().getRow()-1, 'U');
				}
				gameBoard.movePlayer(gameBoard.getPlayercase().getRow()-1, gameBoard.getPlayercase().getCol());
				break;
			default:
				if(crateInThatDirection(gameBoard.getPlayercase().getRow()+1, gameBoard.getPlayercase().getCol())) {
					crateMove(gameBoard.getPlayercase().getCol(),gameBoard.getPlayercase().getRow()+1, 'D');
				}
				gameBoard.movePlayer(gameBoard.getPlayercase().getRow()+1, gameBoard.getPlayercase().getCol());
				break;
		}
	}
	
	/**
	 * Check if the can move to a direction according to a char.
	 *
	 * @param input the input (direction)
	 * @return true, if successful
	 */
	private static boolean possibleMovePlayer(char input) {
		boolean allowedmove = true;
		switch(input) {
		case 'L':
			if(gameBoard.getPlayercase().getCol()-1<0) {
				allowedmove = false;
			}
			else if(gameBoard.getCaseAt(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-1).getType()==CaseType.WALL ) {
				allowedmove = false;
			}
			else if(crateInThatDirection(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-1)) {
				allowedmove=possibleCrateMove(gameBoard.getPlayercase().getCol()-1, gameBoard.getPlayercase().getRow(),'L');
			}
			break;
			
		case 'R':
			if(gameBoard.getPlayercase().getCol()+1>gameBoard.getCol()-1) {
				allowedmove = false;
			}
			else if(gameBoard.getCaseAt(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+1).getType()==CaseType.WALL ) {
				allowedmove = false;
			}
			
			else if(crateInThatDirection(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+1)) {
				allowedmove=possibleCrateMove(gameBoard.getPlayercase().getCol()+1, gameBoard.getPlayercase().getRow(),'R');
			}
			
			break;
		case 'U':
			if(gameBoard.getPlayercase().getRow()-1<0) {
				allowedmove = false;
			}
			else if(gameBoard.getCaseAt(gameBoard.getPlayercase().getRow()-1, gameBoard.getPlayercase().getCol()).getType()==CaseType.WALL) {
				allowedmove = false;
			}
			else if(crateInThatDirection(gameBoard.getPlayercase().getRow()-1, gameBoard.getPlayercase().getCol())) {
				allowedmove=possibleCrateMove(gameBoard.getPlayercase().getCol(), gameBoard.getPlayercase().getRow()-1,'U');
			}
			break;
		default:
			if(gameBoard.getPlayercase().getRow()+1>gameBoard.getRow()-1) {
				allowedmove = false;
			}
			else if(gameBoard.getCaseAt(gameBoard.getPlayercase().getRow()+1, gameBoard.getPlayercase().getCol()).getType()==CaseType.WALL) {
				allowedmove = false;
			}
			else if(crateInThatDirection(gameBoard.getPlayercase().getRow()+1, gameBoard.getPlayercase().getCol())) {
				allowedmove=possibleCrateMove(gameBoard.getPlayercase().getCol(), gameBoard.getPlayercase().getRow()+1,'D');
			}
			break;
	}
		return allowedmove;
	}
	
	/**
	 * Check if there is a crate in the direction where the player want to move.
	 *
	 * @param x the row of the case that  
	 * @param y the y
	 * @return true, if successful
	 */
	private static boolean crateInThatDirection(int x, int y) {
		if(gameBoard.getCaseAt(x, y).getType()==CaseType.BOX) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Check if the crate move is possible.
	 *
	 * @param x the row of the crate case
	 * @param y the col of the crate case
	 * @param input the direction
	 * @return true, if successful
	 */
	private static boolean possibleCrateMove(int x, int y, char input) {
		boolean allowedmove = true;
		switch(input) {
		case 'L':
			if(x-1<0) {
				allowedmove = false;
			}
			else if(gameBoard.getCaseAt(y, x-1).getType()==CaseType.WALL ) {
				allowedmove = false;
			}
			else if(crateInThatDirection(y, x-1)) {
				return possibleCrateMove(x-1,y,'L');
				}
			
			break;
			
		case 'R':
			if(x+1>gameBoard.getCol()-1) {
				allowedmove = false;
			}
			else if(gameBoard.getCaseAt(y, x+1).getType()==CaseType.WALL ) {
				allowedmove = false;
			}
			
			else if(crateInThatDirection(y, x+1)) {
				return possibleCrateMove(x+1,y,'R');
			}
			
			break;
		case 'U':
			if(y-1<0) {
				allowedmove = false;
			}
			else if(gameBoard.getCaseAt(y-1, x).getType()==CaseType.WALL) {
				allowedmove = false;
			}
			else if(crateInThatDirection(y-1, x)) {
				return possibleCrateMove(x,y-1,'U');
			}
			break;
		default:
			if(y+1>gameBoard.getRow()-1) {
				allowedmove = false;
			}
			else if(gameBoard.getCaseAt(y+1, x).getType()==CaseType.WALL) {
				allowedmove = false;
			}
			else if(crateInThatDirection(y+1, x)) {
				return possibleCrateMove(x,y+1,'D');
			}
			break;
	}
		return allowedmove;
	}
	
	/**
	 * Move the crate and move all the other crates in that direction.
	 *
	 * @param x the row of the crate case
	 * @param y the col of the crate case
	 * @param input the direction
	 */
	private static void crateMove(int x, int y , char input) {
		switch(input) {
		case 'L':
			if(crateInThatDirection(y, x-1)) {
				crateMove(x-1,y,'L');
				
			}
			gameBoard.moveBox(y, x,y,x-1);
			break;
			
		case 'R':
			if(crateInThatDirection(y, x+1)) {
				crateMove(x+1,y,'R');
			}
			gameBoard.moveBox(y, x,y,x+1);
			break;
		case 'U':
			if(crateInThatDirection(y-1, x)) {
				crateMove(x,y-1,'U');
			}
			gameBoard.moveBox(y, x,y-1,x);
			break;
		default:
			if(crateInThatDirection(y+1, x)) {
				crateMove(x,y+1,'D');
			}
			gameBoard.moveBox(y, x,y+1,x);
			break;
	}
	}
	
	
	
	
	/**
	 * Win condition.
	 *
	 * @return true, if successful
	 */
	public static boolean winCondition() {
		boolean condition=false;
		int sumCase=0;
		for(Case c : gameBoard.getTargetList()) {
			if(c.getType()==CaseType.BOX) {
				sumCase++;
			}
		}
		if(sumCase == gameBoard.getTargetList().size()) {
			condition = true;
		}
		return condition;
	}
	
}
