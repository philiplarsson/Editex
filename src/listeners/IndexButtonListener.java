package listeners;

import gui.Indexer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IndexButtonListener implements ActionListener{

	private Indexer indexer;
	
	public IndexButtonListener() {
		indexer = new Indexer();
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
	}

}
