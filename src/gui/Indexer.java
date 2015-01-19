package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import listeners.SaveButtonListener;

/**
 * Indexer class cleans up the link file and returns that as a list.
 * 
 * @author phiip
 * 
 */
public class Indexer {

	private FileHandler fileHandler;
	private List<String> lines;

	public Indexer() {
		fileHandler = new FileHandler();
	}

	/**
	 * Checks if link file exists and fills List with links.
	 * 
	 * @returns List of links as strings.
	 * @throws IOException
	 */
	public List<String> indexLinkFile() throws IOException {
		if (!fileHandler.fileExists(SaveButtonListener.LINK_FILE)) {
			// Link file doesn't exist. Return...
			throw new FileNotFoundException("Could not find linkfile "
					+ SaveButtonListener.LINK_FILE);
		}
		lines = fileHandler.readLines(SaveButtonListener.LINK_FILE);

		removeBeginAndEnd();
		return lines;
	}

	/**
	 * Removes non links from lines list.
	 */
	private void removeBeginAndEnd() {
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).contains("BEGIN") || lines.get(i).contains("END")) {
				lines.remove(i);
				--i;
			}
		}
	}

}
