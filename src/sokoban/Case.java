package sokoban;

public class Case {
	private int row;
	private int col;
	private CaseType type;
	
	Case(int row, int col, CaseType type){
		this.row = row;
		this.col = col;
		this.type = type;
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

	public CaseType getType() {
		return type;
	}

	public void setType(CaseType type) {
		this.type = type;
	}
}
