package sokoban;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public static Board gameBoard=buildBoard();
	private static final Scanner in = new Scanner(System.in);
	public static boolean playStatus;



	
	/**
	public Game(Board b) {
		this.gameBoard = b;
	}
	*/
	
	
	public static void Play(Board b) {
		//Game g = new Game(b);
		playStatus=true;
		char input = '0';
		gameBoard.printBoard();
		while(playStatus) {
			//g.gameBoard.printBoard();
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
			}
		}
	}
	
	public static char commandInput() {
		System.out.println("Enter U or D or L or R :");
		String input = in.nextLine().trim();
		if(input.charAt(0)!='U' && input.charAt(0)!='L'&& input.charAt(0)!='R' && input.charAt(0)!='D' ) {
			return '0';
		}
		else {
			System.out.println(input.charAt(0));
			return input.charAt(0);
			
		}
	}
	
	public static void playerMove(char input) {
		
		switch(input) {
			case 'L':
				if(crateInThatDirection(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-1)) {
					gameBoard.moveBox(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-1,gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-2);
				}
				gameBoard.movePlayer(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-1);
				break;
				
			case 'R':
				if(crateInThatDirection(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+1)) {
					gameBoard.moveBox(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+1,gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+2);
				}
				gameBoard.movePlayer(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+1);
				break;
			case 'U':
				if(crateInThatDirection(gameBoard.getPlayercase().getRow()-1, gameBoard.getPlayercase().getCol())) {
					gameBoard.moveBox(gameBoard.getPlayercase().getRow()-1, gameBoard.getPlayercase().getCol(),gameBoard.getPlayercase().getRow()-2, gameBoard.getPlayercase().getCol());
				}
				gameBoard.movePlayer(gameBoard.getPlayercase().getRow()-1, gameBoard.getPlayercase().getCol());
				break;
			default:
				if(crateInThatDirection(gameBoard.getPlayercase().getRow()+1, gameBoard.getPlayercase().getCol())) {
					gameBoard.moveBox(gameBoard.getPlayercase().getRow()+1, gameBoard.getPlayercase().getCol(),gameBoard.getPlayercase().getRow()+2, gameBoard.getPlayercase().getCol());
				}
				gameBoard.movePlayer(gameBoard.getPlayercase().getRow()+1, gameBoard.getPlayercase().getCol());
				break;
		}
	}
	
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
				if(gameBoard.getPlayercase().getCol()-2<0) {
					allowedmove = false;
				}
				else if(gameBoard.getCaseAt(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-2).getType()==CaseType.WALL || gameBoard.getCaseAt(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()-2).getType()==CaseType.BOX) {
					allowedmove = false;
				}
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
				if(gameBoard.getPlayercase().getCol()+2>gameBoard.getCol()-1) {
					allowedmove = false;
				}
				else if(gameBoard.getCaseAt(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+2).getType()==CaseType.WALL || gameBoard.getCaseAt(gameBoard.getPlayercase().getRow(), gameBoard.getPlayercase().getCol()+2).getType()==CaseType.BOX) {
					allowedmove = false;
				}
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
				if(gameBoard.getPlayercase().getRow()-2<0) {
					allowedmove = false;
				}
				else if(gameBoard.getCaseAt(gameBoard.getPlayercase().getRow()-2, gameBoard.getPlayercase().getCol()).getType()==CaseType.WALL || gameBoard.getCaseAt(gameBoard.getPlayercase().getRow()-2, gameBoard.getPlayercase().getCol()).getType()==CaseType.BOX) {
					allowedmove = false;
				}
			}
			break;
		default:
			if(gameBoard.getPlayercase().getRow()+1>gameBoard.getRow()-1) {
				allowedmove = false;
			}
			else if(gameBoard.getCaseAt(gameBoard.getPlayercase().getRow()+1, gameBoard.getPlayercase().getCol()).getType()==CaseType.WALL) {
				allowedmove = false;
			}
			else if(crateInThatDirection(gameBoard.getPlayercase().getRow()-1, gameBoard.getPlayercase().getCol())) {
				if(gameBoard.getPlayercase().getRow()+2>gameBoard.getRow()-1) {
					allowedmove = false;
				}
				else if(gameBoard.getCaseAt(gameBoard.getPlayercase().getRow()+2, gameBoard.getPlayercase().getCol()).getType()==CaseType.WALL || gameBoard.getCaseAt(gameBoard.getPlayercase().getRow()-2, gameBoard.getPlayercase().getCol()).getType()==CaseType.BOX) {
					allowedmove = false;
				}
			}
			break;
	}
		return allowedmove;
	}
	
	private static boolean crateInThatDirection(int x, int y) {
		if(gameBoard.getCaseAt(x, y).getType()==CaseType.BOX) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static Board buildBoard() {
		Board b = new Board( 5, 6);
		b.addHorizontalWall(0,5,6);
		b.addHorizontalWall(4,5,6);
		b.addVerticalWall(0,0,5);
		b.addVerticalWall(0,5,5);
		b.addBox(2,1);
		b.addBox(2,3);
		b.addTarget(3,1);
		b.addTarget(3,2);
		b.setPosition(3,4);
		return b;
	}
	
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
