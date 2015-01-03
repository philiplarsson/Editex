package gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class FileHandler {

	/**
	 * Creates a new folder.
	 * 
	 * @param folderName
	 *            is the name of the folder.
	 * @throws IOException
	 *             if the folder couldn't be created.
	 */
	public void createFolder(String folderName) throws IOException {
		System.out.println("Creating folder " + folderName + ". ");
		File dir = new File(folderName);
		boolean result = dir.mkdir();
		if (!result) {
			throw new IOException("Could not create folder " + folderName
					+ " .");
		}
	}

	/**
	 * Creates a new file.
	 * 
	 * @param fileName
	 *            is the file name of the new file.
	 * @throws IOException
	 *             if the file couldn't be created.
	 */
	public void createFile(String fileName) throws IOException {
		File file = new File(fileName);
		boolean result = false;
		result = file.createNewFile();

		if (!result) {
			throw new IOException("File " + fileName
					+ " already exists. Or some other problem :) ");
		}

	}

	/**
	 * Checks if the given file exists.
	 * 
	 * @param fileName
	 *            is the name of the file to check.
	 * @return true if file exists.
	 */
	public boolean fileExists(String fileName) {
		File file = new File(fileName);
		Path path = file.toPath();
		System.out.println("Checking if file: '" + path.toString()
				+ "' exists.");
		return Files.exists(path, LinkOption.NOFOLLOW_LINKS);

	}

	/**
	 * Writes the given string to file. Last argument is if the file shall
	 * append or not.
	 * 
	 * @param fileName
	 *            is the name of the file.
	 * @param content
	 *            is the content to be added to fileName.
	 * @param append
	 *            is true if content should be appended to fileName.
	 * @throws IOException
	 *             if file could not be written to.
	 */
	public void writeToFile(String fileName, String content, boolean append)
			throws IOException {
		FileOutputStream fs = new FileOutputStream(fileName);
		OutputStreamWriter os = new OutputStreamWriter(fs, "UTF-8");
		if (append) {
			os.append(content);
		} else {
			os.write(content);
		}
		os.close();
		fs.close();
	}

}
