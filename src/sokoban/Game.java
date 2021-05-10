package sokoban;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	public static Board gameBoard=buildBoard();
	private static final Scanner in = new Scanner(System.in);
	public static boolean playStatus;



	
	
	public Game(Board b) {
		this.gameBoard = b;
	}
	
	
	public static void Play(Board b) {
		Game g = new Game(b);
		playStatus=true;
		char input = '0';
		while(playStatus) {
			g.gameBoard.printBoard();
			while(input == '0') {
				input=commandInput();
			}
			input='0';
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
	
	
	
}
