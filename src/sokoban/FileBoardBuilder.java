package sokoban;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.util.Scanner;


public class FileBoardBuilder implements BoardBuilder{
	
	
	
	
	
	@Override
	public Board build() throws BuilderException{
		try  {
			File myObj = new File("board.txt");
		    Scanner myReader = new Scanner(myObj);
		    TextBoardBuilder TextBuilder = new TextBoardBuilder();
		    while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        TextBuilder.addRow(data);
		      }
		    return TextBuilder.build();
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println("FileNotFound");
			throw new BuilderException("FileNotFound");
			
		}
	}
}
