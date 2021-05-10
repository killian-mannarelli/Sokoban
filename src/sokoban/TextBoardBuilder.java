package sokoban;

import java.util.ArrayList;

public class TextBoardBuilder implements BoardBuilder{
	public ArrayList<String> rowString;
	
	
	public TextBoardBuilder() {
		rowString = new ArrayList<String>();
	}
	
	public void addRow(String row) {
		rowString.add(row);
	}
	
	@Override
	public Board build() {
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
