package sokoban;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.util.Scanner;


// TODO: Auto-generated Javadoc
/**
 * The Class FileBoardBuilder.
 */
public class FileBoardBuilder implements BoardBuilder{
	
	/** The filepath. */
	String filepath;
	
	/**
	 * Instantiates a new file board builder.
	 *
	 * @param filepath the filepath
	 */
	public FileBoardBuilder(String filepath) {
		this.filepath = filepath;
	}
	
	
	/**
	 * Builds the.
	 *
	 * @return the board
	 * @throws BuilderException the builder exception
	 */
	@Override
	public Board build() throws BuilderException{
		try  {
			File myObj = new File(filepath);
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
