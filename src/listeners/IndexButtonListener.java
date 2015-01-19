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
	private static final String CSS_FOLDERNAME = "css";
	private static final String CSS_FILENAME = "style.css";

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
			links = indexer.indexLinkFile();
		} catch (FileNotFoundException e0) {
			System.err.println("Could not find link file.");
			e0.printStackTrace();
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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

		checkIfCssFolderExists();
		checkIfCssCourseFileExists();
		checkIfCssIndexFileExists();

		try {
			fileHandler.copyFileFromResources(CSS_FILENAME, CSS_FOLDERNAME
					+ "/" + CSS_FILENAME);
		} catch (IOException e1) {
			System.err.println("Could not copy file \"" + CSS_FILENAME
					+ "\" from resources folder");
			e1.printStackTrace();
		}

	}

	private void checkIfCssIndexFileExists() {
		if (!fileHandler.fileExists(CSS_FOLDERNAME + "/" + CSS_FILENAME)) {
			try {
				fileHandler.createFile(CSS_FOLDERNAME + "/" + CSS_FILENAME);
			} catch (IOException e1) {
				System.err
						.println("Could not create css index file in css folder");
				e1.printStackTrace();
			}
		}
	}

	private void checkIfCssCourseFileExists() {
		if (!fileHandler.fileExists(CSS_FOLDERNAME + "/" + CSS_FILENAME)) {
			// css course file doesn't exist
			try {
				fileHandler.createFile(CSS_FOLDERNAME + "/" + CSS_FILENAME);
			} catch (IOException e1) {
				System.err
						.println("Could not create css course file in css folder");
				e1.printStackTrace();
			}
		}
	}

	private void checkIfCssFolderExists() {
		if (!fileHandler.fileExists(CSS_FOLDERNAME)) {
			// Folder doesn't exist.
			try {
				fileHandler.createFolder(CSS_FOLDERNAME);
			} catch (IOException e1) {
				System.err.println("Could not create folder. ");
				e1.printStackTrace();
			}
		}
	}

}
