package sokoban;

public class Player {

	public static void main(String[] args) {
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
		
		
		b.printBoard();
	}

}
