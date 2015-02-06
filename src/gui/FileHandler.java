package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

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
		// System.out.println("Checking if file: '" + path.toString()
		// + "' exists.");
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
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName,
					append)));
			out.println(content);
		} finally {
			out.close();
		}

		/*
		 * try(PrintWriter out = new PrintWriter(new BufferedWriter(new
		 * FileWriter(fileName, append)))) { out.println(content); out.close();
		 * }catch (IOException e) {
		 * 
		 * } /*
		 * 
		 * 
		 * FileOutputStream fs = new FileOutputStream(fileName);
		 * OutputStreamWriter os = new OutputStreamWriter(fs, "UTF-8"); if
		 * (append) { os.append(content); } else { os.write(content); }
		 * os.close(); fs.close();
		 */
	}

	/**
	 * Returns a list of strings from specified file.
	 * 
	 * @param fileName
	 *            is the filename of the file.
	 * @return a List of Strings.
	 * @throws IOException
	 *             if an I/O error occurs reading from the file or a malformed
	 *             or unmappable byte sequence is read.
	 */
	public List<String> readLines(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readAllLines(path, Charset.forName("UTF-8"));
	}

	/**
	 * Reads specified file from resources folder.
	 * 
	 * @param fileName
	 *            is the filename of the resource file.
	 * @return a List of Strings.
	 * @throws IOException
	 *             if an I/O error occurs reading from the file or a malformed
	 *             or unmappable byte sequence is read.
	 */
	public List<String> readLinesFromResources(String fileName)
			throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File source = null;

		try {
			source = new File(classLoader.getResource(fileName).getFile());
		} catch (NullPointerException e) {
			System.err.println("Could not find " + fileName
					+ " in resource folder.");
			e.printStackTrace();
		}

		return Files.readAllLines(source.toPath(), Charset.forName("UTF-8"));
	}

	/**
	 * Adds line to resource file with specified file name.
	 * 
	 * @param fileName
	 *            the name of the resource file.
	 * @param line
	 *            is the String to be added.
	 */
	public void addLineToResourceFile(String fileName, String line) {
		ClassLoader classLoader = getClass().getClassLoader();
		File dest = null;

		try {
			dest = new File(classLoader.getResource(fileName).getFile());
		} catch (NullPointerException e) {
			System.err.println("Could not find " + fileName
					+ " in resource folder.");
			e.printStackTrace();
		}
		try {
			writeToFile(dest.toString(), line, true);
		} catch (IOException e) {
			System.err.println("Could not write to file "
					+ dest.getAbsolutePath());
			e.printStackTrace();
		}

	}

	/**
	 * Copy file from resources folder to specified destination.
	 * 
	 * @param resourceFile
	 *            is the resource file name.
	 * @param destination
	 *            is the destination.
	 * @throws IOException
	 *             if copy was unsuccesfull.
	 */
	public void copyFileFromResources(String resourceFile, String destination)
			throws IOException {
		File dest = new File(destination);

		ClassLoader classLoader = getClass().getClassLoader();

		File source = new File(classLoader.getResource(resourceFile).getFile());

		Files.copy(source.toPath(), dest.toPath(),
				StandardCopyOption.REPLACE_EXISTING);

	}
}
