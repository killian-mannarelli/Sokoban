package sokoban;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class TextBoardBuilder.
 */
public class TextBoardBuilder implements BoardBuilder{
	
	/** The row string. */
	public ArrayList<String> rowString;
	
	
	/**
	 * Instantiates a new text board builder.
	 */
	public TextBoardBuilder() {
		rowString = new ArrayList<String>();
	}
	
	/**
	 * Adds a row.
	 *
	 * @param row the row
	 */
	public void addRow(String row) {
		rowString.add(row);
	}
	
	/**
	 * Check string size and cancel the build if a string has not the same size as another.
	 *
	 * @throws BuilderException the builder exception
	 */
	public void checkStringSize() throws BuilderException{
		int referencesize = rowString.get(0).length();
		for(String c : rowString) {
			if(c.length()!=referencesize) {
				throw new BuilderException("The strings are not of the same size !");
		}
	}
	}
	
	/**
	 * Builds the board using Stringk, each string is a row.
	 *
	 * @return the builded board
	 * @throws BuilderException the builder exception if any string is longer or shorter than the others.
	 */
	@Override
	public Board build() throws BuilderException{
		checkStringSize();
		Board b = new Board(rowString.size(),rowString.get(0).length());
		for(int i=0;i<rowString.size();i++) {
			for(int j=0;j<rowString.get(0).length();j++) {
				switch(rowString.get(i).charAt(j)) {
				case '#':
					b.addWall(i, j);
					break;
				case 'P':
					b.setPosition(i,j);
					break;
				case 'x':
					b.addTarget(i, j);
					break;
				case 'C':
					b.addBox(i, j);
					break;
				default:
					break;
				}
			}
		}
		return b;
	}
}
