package sokoban;


/**
 * The Class Case.
 */
public class Case {
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/** The firsttype. */
	private CaseType firsttype;
	
	/** The secondoptionaltype. */
	private CaseType secondoptionaltype;
	
	/**
	 * Instantiates a new case.
	 *
	 * @param row the row
	 * @param col the col
	 * @param type the type
	 */
	Case(int row, int col, CaseType type){
		this.row = row;
		this.col = col;
		this.firsttype = type;
		this.secondoptionaltype = CaseType.EMPTY;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public CaseType getType() {
		return firsttype;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(CaseType type) {
		this.firsttype = type;
	}

	/**
	 * Gets the secondoptionaltype.
	 *
	 * @return the secondoptionaltype
	 */
	public CaseType getSecondoptionaltype() {
		return secondoptionaltype;
	}

	/**
	 * Sets the secondoptionaltype.
	 *
	 * @param secondoptionaltype the new secondoptionaltype
	 */
	public void setSecondoptionaltype(CaseType secondoptionaltype) {
		this.secondoptionaltype = secondoptionaltype;
	}
}
