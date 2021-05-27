package sokoban;

// TODO: Auto-generated Javadoc
/**
 * The Enum CaseType.
 */
public enum CaseType {

/** The wall. */
WALL{

public String ToString() {
	return "# ";
}
},

/** The box. */
BOX{
	public String ToString() {
		return "C ";	
	}
		
}
,

/** The target. */
TARGET{
	public String ToString() {
		return "x ";	
	}
},

/** The playerposition. */
PLAYERPOSITION{
	public String ToString() {
		return "P ";	
	}	
},

/** The empty. */
EMPTY{
	public String ToString() {
		return ". ";	
	}	
}
}
