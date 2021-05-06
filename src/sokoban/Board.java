package sokoban;
import java.util.ArrayList;




public class Board {
	private int row;
	private int col;
	private ArrayList<ArrayList<Case>> board;
	
	public Board(int row, int col){
		this.setRow(row);
		this.setCol(col);
		this.board = createBoard();
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public ArrayList<ArrayList<Case>> getBoard() {
		return board;
	}

	public void setBoard(ArrayList<ArrayList<Case>> board) {
		this.board = board;
	}
	
	private ArrayList<ArrayList<Case>> createBoard(){
		ArrayList<ArrayList<Case>> boardtocreate = new ArrayList<ArrayList<Case>>();
		for(int i=0;i<row;i++) {
			ArrayList<Case> rowlist = new ArrayList<Case>();
			for(int j=0;j<col;j++) {
				Case newCase = new Case(i,j,CaseType.EMPTY);
				rowlist.add(newCase);
			}
			boardtocreate.add(rowlist);
		}
		
		
		return boardtocreate;
		
	}
	
	public void addBox(int x, int y) {
		board.get(x).get(y).setType(CaseType.BOX);
	}
	
	public void addTarget(int x, int y) {
		board.get(x).get(y).setType(CaseType.TARGET);
	}

}
