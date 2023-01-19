// -------------------------------------------------------
	// Assignment3
    // Written by: (Diyi Lin student id40086388)
	// For COMP 249 Section  – winter 2019
	// --------------------------------------------------------
import java.io.*;

public class FileExsitsException extends Exception{


	public FileExsitsException() {
	super("Exception: There is already an existing file for that author. File will\r\n" + 
	 		"be renamed as BU, and older BU files will be deleted!");	
	}
    public FileExsitsException(String s) {
    	super(s);
    }
	
	public String getMessage() {
		return super.getMessage();
	}
		
	
}
