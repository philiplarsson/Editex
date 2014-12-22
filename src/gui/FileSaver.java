package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileSaver {

	private PrintWriter printWriter;
	private LinkHandler linkHandler;
	@SuppressWarnings("unused")
	private String linkFile;
	
	public FileSaver(String linkFile) {
		this.linkFile = linkFile;
		if (!fileExists(linkFile)) {
			createFile(linkFile);
		}
		linkHandler = new LinkHandler(linkFile);
	}

	public void createFile(String fileName) {
		try {
			printWriter = new PrintWriter(fileName, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.err.println("Could not create printwriter.");
			e.printStackTrace();
		}
	}

	public void saveToFile(String content, String fileName) {
		createFile(fileName);
		linkHandler.addLink(fileName);
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
