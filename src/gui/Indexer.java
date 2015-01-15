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
		
		removeBeginAndEnd();
	}
	
	private void removeBeginAndEnd() {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).contains("BEGIN") || lines.get(i).contains("END")) {
				lines.remove(i);
				--i;
			}
		}
	}
	
	public List<String> getLinks() {
		return lines;
	}
	
}
