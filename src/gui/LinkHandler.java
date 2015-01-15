package gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

	private ArrayList<String> lines;

	public LinkHandler(String fileName) {
		lines = new ArrayList<String>();

		this.linkFileName = fileName;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.err
					.println("Could not create bufferedReader. Could be because "
							+ linkFileName + "doesn't exists...");
			e.printStackTrace();
		}

		readInFile();
	}

	/**
	 * Adds a link to link file. Checks if fileName exists in file and add stub
	 * if missing. If filename already exists it adds it last.
	 * 
	 * @param fileName
	 *            the String to be added.
	 */
	public void addLinkToLinkFile(String fileName) {
		String parts[] = fileName.split("-");
		String courseName = parts[0];

		if (lines.contains(courseName + "/" + fileName + ".html")) {
			// We don't want to add a duplicate.
			System.out.println("Found duplicate..");
			return;
		}
		if (existsInFile(courseName)) {
			// Detta filnamn finns redan i filen
			appendBeforeEND(fileName);
		} else {
			// appenda på slutet
			appendInFile(courseName, fileName);
		}
	}

	/**
	 * Returns true if the link array (and therefore file) contains the
	 * specified string.
	 * 
	 * @param searchString
	 *            the string to search for.
	 * @return true if string exists.
	 */
	public boolean existsInFile(String searchString) {
		for (String line : lines) {
			if (line.contains(searchString))
				return true;
		}

		return false;
	}

	/**
	 * Reads in file to check for old inputs.
	 */
	private void readInFile() {
		String line;
		try {
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			System.err.println("Could not read in from file.");
			e.printStackTrace();
		}
		// Reset buffer since we have gone to end of file...
		resetBufferedReader();
	}

	/**
	 * Appends specified string before END:fileName.
	 * 
	 * @param fileName
	 *            the string to be added.
	 */
	private void appendBeforeEND(String fileName) {
		String line;
		String parts[] = fileName.split("-");
		String courseName = parts[0];
		for (int i = 0; i < lines.size(); i++) {
			line = lines.get(i);
			if (line.equals("END:" + courseName)) {
				// Vi ska in med den innan rad i.
				lines.add(i - 1, courseName + "/" + fileName);
				break;
			}
		}
		printLinesToFile();
	}

	/**
	 * Appends specified string and course name with file stub.
	 * 
	 * @param courseName
	 *            the string to be added as course name.
	 * @param fileName
	 *            the string to be added as file name.
	 */
	private void appendInFile(String courseName, String fileName) {
		lines.add("BEGIN:" + courseName);
		lines.add(courseName + "/" + fileName);
		lines.add("END:" + courseName);
		printLinesToFile();
	}

	/**
	 * Prints the lines array to file.
	 */
	private void printLinesToFile() {
		try {
			PrintWriter pw = new PrintWriter(linkFileName, "UTF-8");
			for (String line : lines) {
				pw.println(line);
			}
			// lines.clear();
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not find file " + linkFileName);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.err.println("Could not figure out encoding...");
			e.printStackTrace();
		}
	}

	/**
	 * Resets BufferedReader. Used when buffered reader needs to be reset due to
	 * reading to end of file.
	 */
	private void resetBufferedReader() {
		try {
			br = new BufferedReader(new FileReader(linkFileName));
		} catch (FileNotFoundException e) {
			System.err.println("Could not create bufferedReader. ");
			e.printStackTrace();
		}
	}

}
