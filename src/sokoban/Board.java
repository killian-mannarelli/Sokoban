package sokoban;

public class Board {
	private int row;
	private int col;
	
	Board(int row, int col){
		this.row=row;
		this.col = col;
	}
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
}
