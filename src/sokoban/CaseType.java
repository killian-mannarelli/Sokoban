package sokoban;

public enum CaseType {
WALL{

public String ToString() {
	return "# ";
}
},
BOX{
	public String ToString() {
		return "C ";	
	}
		
}
,
TARGET{
	public String ToString() {
		return "x ";	
	}
},
PLAYERPOSITION{
	public String ToString() {
		return "P ";	
	}	
},
EMPTY{
	public String ToString() {
		return ". ";	
	}	
}
}
