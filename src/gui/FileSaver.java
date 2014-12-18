package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileSaver {

	@SuppressWarnings("unused")
	private String fileName;
	private PrintWriter printWriter;
	
	public FileSaver(String fileName) {
		this.fileName = fileName;
		try {
			printWriter = new PrintWriter(fileName, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.err.println("Could not create printwriter.");
			e.printStackTrace();
		}
	}
	
	public void saveFile(String content) {
		printWriter.write(content);
		printWriter.close();
	}
	
	public boolean fileExists(String fileName) {
		File file = new File(fileName);
		if (file.exists())
			return true;
		return false;		
	}
}
