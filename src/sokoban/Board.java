package sokoban;
import java.util.ArrayList;





/**
 * The Class Board.
 */
public class Board {
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/** The board. */
	private ArrayList<ArrayList<Case>> board;
	
	/** The playercase. */
	private Case playercase;
	
	/** The target list. */
	private ArrayList<Case> targetList; 
	
	/**
	 * Instantiates a new board.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public Board(int row, int col){
		this.setRow(row);
		this.setCol(col);
		this.board = createBoard();
		this.targetList = new ArrayList<Case>();
	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Sets the col.
	 *
	 * @param col the new col
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public ArrayList<ArrayList<Case>> getBoard() {
		return board;
	}

	/**
	 * Sets the board.
	 *
	 * @param board the new board
	 */
	public void setBoard(ArrayList<ArrayList<Case>> board) {
		this.board = board;
	}
	
	/**
	 * Gets the case at.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the case at
	 */
	public Case getCaseAt(int x, int y) {
		return board.get(x).get(y);
	}
	
	/**
	 * Creates the board.
	 *
	 * @return the array list
	 */
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
	
	/**
	 * Adds the box.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void addBox(int x, int y) {
		board.get(x).get(y).setType(CaseType.BOX);
	}
	
	/**
	 * Adds the target.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void addTarget(int x, int y) {
		board.get(x).get(y).setType(CaseType.TARGET);
		board.get(x).get(y).setSecondoptionaltype(CaseType.TARGET);
		targetList.add(board.get(x).get(y));
	}
	
	/**
	 * Sets the position.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void setPosition(int x, int y) {
		board.get(x).get(y).setType(CaseType.PLAYERPOSITION);
		setPlayercase(board.get(x).get(y));
	}
	
	/**
	 * Adds the vertical wall.
	 *
	 * @param x the x
	 * @param y the y
	 * @param walllength the walllength
	 */
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
	
	/**
	 * Adds the wall.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void addWall(int x, int y) {
		board.get(x).get(y).setType(CaseType.WALL);
	}
	
	/**
	 * Adds the horizontal wall.
	 *
	 * @param x the x
	 * @param y the y
	 * @param walllength the walllength
	 */
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
	
	/**
	 * Prints the board.
	 */
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

	/**
	 * Gets the playercase.
	 *
	 * @return the playercase
	 */
	public Case getPlayercase() {
		return playercase;
	}

	/**
	 * Sets the playercase.
	 *
	 * @param playercase the new playercase
	 */
	public void setPlayercase(Case playercase) {
		this.playercase = playercase;
	}
	
	/**
	 * Move player.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public void movePlayer(int x, int y) {
		getCaseAt(x,y).setType(CaseType.PLAYERPOSITION);
		playercase.setType(playercase.getSecondoptionaltype());
		setPlayercase(getCaseAt(x,y));
	}
	
	/**
	 * Move box.
	 *
	 * @param oldx the oldx
	 * @param oldy the oldy
	 * @param x the x
	 * @param y the y
	 */
	public void moveBox(int oldx, int oldy, int x, int y) {
		getCaseAt(oldx,oldy).setType(getCaseAt(oldx,oldy).getSecondoptionaltype());
		getCaseAt(x,y).setType(CaseType.BOX);
	}
	
	/**
	 * Gets the target list.
	 *
	 * @return the target list
	 */
	public ArrayList<Case> getTargetList(){
		return this.targetList;
	}
	
	/**
	 * Rows to string.
	 *
	 * @return the array list
	 */
	public ArrayList<String> rowsToString() {
		String charToAdd = ".";
		ArrayList<String> rowList = new ArrayList<String>();
		for(int i=0;i<row;i++) {
			String rowString = "";
			for(int j=0;j<col;j++) {
				switch(getCaseAt(i,j).getType()) {
				case WALL :
					charToAdd = "#";
					break;
				case TARGET : 
					charToAdd = "x";
					break;
				case BOX : 
					charToAdd = "C";
					break;
				case PLAYERPOSITION : 
					charToAdd = "P";
					break;
				default:
					charToAdd = ".";
					break;
				
				}
				if(j==col-1) {
					rowString= rowString + charToAdd;	
				}
				else {
					rowString= rowString + charToAdd;
				}
			}
			rowList.add(rowString);
		}
		return rowList;
	}
	

}
