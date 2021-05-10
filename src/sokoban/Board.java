package sokoban;
import java.util.ArrayList;




public class Board {
	private int row;
	private int col;
	private ArrayList<ArrayList<Case>> board;
	private Case playercase;
	private ArrayList<Case> targetList; 
	
	public Board(int row, int col){
		this.setRow(row);
		this.setCol(col);
		this.board = createBoard();
		this.targetList = new ArrayList<Case>();
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
	
	public Case getCaseAt(int x, int y) {
		return board.get(x).get(y);
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
		board.get(x).get(y).setSecondoptionaltype(CaseType.TARGET);
		targetList.add(board.get(x).get(y));
	}
	
	public void setPosition(int x, int y) {
		board.get(x).get(y).setType(CaseType.PLAYERPOSITION);
		setPlayercase(board.get(x).get(y));
	}
	
	public void addVerticalWall(int x, int y, int walllength) {
		if(x+walllength < row) {
			for(int i = x;i<x+walllength;i++) {
				board.get(i).get(y).setType(CaseType.WALL);
			}
		}
		else {
			for(int i = x;i<row;i++) {
				board.get(i).get(y).setType(CaseType.WALL);
			}
		}
	}
	
	public void addWall(int x, int y) {
		board.get(x).get(y).setType(CaseType.WALL);
	}
	public void addHorizontalWall(int x, int y, int walllength) {
		if(y+walllength < col) {
			for(int i = y;i<y+walllength;i++) {
				board.get(x).get(i).setType(CaseType.WALL);
			}
		}
		else {
			for(int i = y;i<col;i++) {
				board.get(x).get(i).setType(CaseType.WALL);
			}
		}
	}
	
	public void printBoard() {
		String charToPrint = ". ";
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				switch(getCaseAt(i,j).getType()) {
				case WALL :
					charToPrint = "# ";
					break;
				case TARGET : 
					charToPrint = "x ";
					break;
				case BOX : 
					charToPrint = "C ";
					break;
				case PLAYERPOSITION : 
					charToPrint = "P ";
					break;
				default:
					charToPrint = ". ";
					break;
				
				}
				if(j==col-1) {
					System.out.println(charToPrint);
				}
				else {
					System.out.print(charToPrint);
				}
			}
		}
	}

	public Case getPlayercase() {
		return playercase;
	}

	public void setPlayercase(Case playercase) {
		this.playercase = playercase;
	}
	
	public void movePlayer(int x, int y) {
		getCaseAt(x,y).setType(CaseType.PLAYERPOSITION);
		playercase.setType(playercase.getSecondoptionaltype());
		setPlayercase(getCaseAt(x,y));
	}
	
	public void moveBox(int oldx, int oldy, int x, int y) {
		getCaseAt(oldx,oldy).setType(getCaseAt(oldx,oldy).getSecondoptionaltype());
		getCaseAt(x,y).setType(CaseType.BOX);
	}
	
	public ArrayList<Case> getTargetList(){
		return this.targetList;
	}
	

}
