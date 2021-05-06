package sokoban;

public class Case {
	private int row;
	private int col;
	private CaseType firsttype;
	private CaseType secondoptionaltype;
	
	Case(int row, int col, CaseType type){
		this.row = row;
		this.col = col;
		this.firsttype = type;
		this.secondoptionaltype = CaseType.EMPTY;
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
		return firsttype;
	}

	public void setType(CaseType type) {
		this.firsttype = type;
	}

	public CaseType getSecondoptionaltype() {
		return secondoptionaltype;
	}

	public void setSecondoptionaltype(CaseType secondoptionaltype) {
		this.secondoptionaltype = secondoptionaltype;
	}
}
