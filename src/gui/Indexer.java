package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import listeners.SaveButtonListener;

public class Indexer {

	private FileHandler fileHandler;
	private List<String> lines;
	
	public Indexer() {
		fileHandler = new FileHandler();
	}
	
	public void indexLinkFile() throws IOException{
		if (!fileHandler.fileExists(SaveButtonListener.LINK_FILE)) {
			// Link file doesn't exist. Return...
			throw new FileNotFoundException("Could not find linkfile " + SaveButtonListener.LINK_FILE);
		}
		lines = fileHandler.readLines(SaveButtonListener.LINK_FILE);
		for (String line : lines) {
			System.out.println(line);
		}
	}
	
}
