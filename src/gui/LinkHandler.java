package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * This class handles all writing to link file. The link file needs to exist
 * before this class is being used.
 * 
 * @author Philip
 * 
 */
public class LinkHandler {

	private String linkFileName;
	private BufferedReader br;
	private PrintWriter pw;

	private ArrayList<String> lines;

	public LinkHandler(String fileName) {
		lines = new ArrayList<String>();

		this.linkFileName = fileName;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.err.println("Could not create bufferedReader. ");
			e.printStackTrace();
		}

		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(linkFileName,
					true)));
		} catch (IOException e) {
			System.err.println("Could not create printWriter. ");
			e.printStackTrace();
		}
	}

	public void addLink(String fileName) {
		String parts[] = fileName.split("-");
		String courseName = parts[0];
		if (existsInFile(courseName)) {
			// Detta filnamn finns redan i filen
			appendBeforeEND(fileName);
		} else {
			// appenda på slutet
			pw.println("BEGIN:" + courseName);
			pw.println(courseName + "/" + fileName + ".html");
			pw.println("END:" + courseName);
			pw.close();

		}
	}

	public boolean existsInFile(String searchString) {

		String line;
		boolean stringExists = false;
		try {
			while ((line = br.readLine()) != null) {
				lines.add(line);
				if (line.contains(searchString)) {
					stringExists = true;
				}
			}
		} catch (IOException e) {
			System.err
					.println("Could not read next line from BufferedReader. ");
			e.printStackTrace();
		}
		resetBufferedReader();
		return stringExists;
	}

	private void appendBeforeEND(String fileName) {
		String line;
		String parts[] = fileName.split("-");
		String courseName = parts[0];
		for (int i = 0; i < lines.size(); i++) {
			line = lines.get(i);
			if (line.equals("END:" + courseName)) {
				// Vi ska in med den innan rad i.
				lines.add(i - 1, courseName + "/" + fileName + ".html");
				break;
			}
		}
		printLinesToFile();
	}

	private void printLinesToFile() {
		try {
			PrintWriter pw = new PrintWriter(linkFileName, "UTF-8");
			for (String line : lines) {
				pw.println(line);
			}
			lines.clear();
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not find file " + linkFileName);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.err.println("Could not figure out encoding...");
			e.printStackTrace();
		}
	}

	private void resetBufferedReader() {
		try {
			br = new BufferedReader(new FileReader(linkFileName));
		} catch (FileNotFoundException e) {
			System.err.println("Could not create bufferedReader. ");
			e.printStackTrace();
		}
	}

}
