package listeners;

import gui.FileHandler;
import gui.Htmlifyer;
import gui.Indexer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class IndexButtonListener implements ActionListener {

	private static final String INDEX_FILENAME = "index.html";
	private Indexer indexer;
	private List<String> links;
	private String linkFileContent;
	private FileHandler fileHandler;

	public IndexButtonListener() {
		indexer = new Indexer();
		fileHandler = new FileHandler();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			indexer.indexLinkFile();

		} catch (FileNotFoundException e0) {
			System.err.println("Could not find link file.");
			e0.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		links = indexer.getLinks();
		linkFileContent = Htmlifyer.getIndexFile(links);
		try {
			if (!fileHandler.fileExists(INDEX_FILENAME)) {
				fileHandler.createFile(INDEX_FILENAME);
			}
		} catch (IOException e1) {
			System.err.println("Could not create file " + INDEX_FILENAME);
			e1.printStackTrace();
		}
		try {
			fileHandler.writeToFile(INDEX_FILENAME, linkFileContent, false);
		} catch (IOException e1) {
			System.err.println("Could not write content to " + INDEX_FILENAME);
			e1.printStackTrace();
		}

	}

}
