package listeners;

import gui.Converter;
import gui.FileHandler;
import gui.Gui;
import gui.Htmlifyer;
import gui.LinkHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import constants.FieldConstants;

public class SaveButtonListener implements ActionListener {

	public final static String LINK_FILE = "linkFile.conf";
	private static final String FILE_NAME_ENDING_RAW = ".raw";

	private Gui gui;
	private JTextField fields[];
	private JTextField fileNameField;
	private JTextField courseNameField;
	private FileHandler fileHandler;
	private LinkHandler linkHandler;
	private Converter converter;

	public SaveButtonListener(Gui gui) {
		this.gui = gui;
		fields = gui.getFields();
		fileHandler = new FileHandler();
		if (!fileHandler.fileExists(LINK_FILE)) {
			try {
				fileHandler.createFile(LINK_FILE);
			} catch (IOException e) {
				System.err.println("Could not create link file ' " + LINK_FILE
						+ " '");
				e.printStackTrace();
			}
		}
		linkHandler = new LinkHandler(LINK_FILE);
		fileNameField = fields[FieldConstants.FILENAME_FIELD];
		courseNameField = fields[FieldConstants.COURSE_FIELD];
		converter = new Converter();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String fileName;
		String courseName;

		boolean status = checkCourseNameField();
		if (status == false)
			return;

		status = checkFileNameField();

		if (status == false)
			return;

		courseName = courseNameField.getText();
		fileName = fileNameField.getText();

		String fileAndCourseName = courseName + "-" + fileName;
		String entireFileName = courseName + "/" + fileAndCourseName;

		if (fileHandler.fileExists(courseName)) {
			System.out.println("Folder Exists!");
		} else {
			System.out.println(courseName + " folder doesnt exists.");
			// Folder doesn't exist
			try {
				fileHandler.createFolder(courseName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (fileHandler.fileExists(entireFileName + FILE_NAME_ENDING_RAW)) {
			int answer = JOptionPane.showConfirmDialog(null, "File ''"
					+ fileAndCourseName + "'' already exists. Overwrite? ",
					"Oops", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.NO_OPTION
					|| answer == JOptionPane.CLOSED_OPTION) {
				return;
			}
		} else {
			// File doesn't exists
			try {
				fileHandler.createFile(courseName + "/" + fileAndCourseName
						+ FILE_NAME_ENDING_RAW);
			} catch (IOException e) {
				System.err.println("Could not create file...");
				e.printStackTrace();
			}
		}
		String text = gui.getTextField().getText();

		String convertedText = converter.convert(text);
		String htmlText = Htmlifyer.htmlIfyText(convertedText,
				fileAndCourseName);
		try {
			fileHandler.writeToFile(entireFileName + FILE_NAME_ENDING_RAW,
					text, false);
			fileHandler.writeToFile(entireFileName + ".html", htmlText, false);

			linkHandler.addLinkToLinkFile(fileAndCourseName + ".html");
		} catch (IOException e) {
			System.err.println("Could not save to file " + fileAndCourseName);
			e.printStackTrace();
		}
	}

	/**
	 * This method checks if course name field is filled in. Returns false if
	 * user aborts.
	 * 
	 * @return true if field is filled in.
	 */
	private boolean checkCourseNameField() {
		String courseName;
		if (courseNameField.getText().isEmpty()) {
			courseName = JOptionPane
					.showInputDialog("Please enter a course name: ");
			if (courseName == null || courseName.isEmpty()) {
				return false;
			}
			courseNameField.setText(courseName);
		}
		return true;
	}

	/**
	 * This method checks if file name field is filled in. Returns false if user
	 * aborts.
	 * 
	 * @return true if field is filled in.
	 */
	private boolean checkFileNameField() {
		String fileName;
		if (fileNameField.getText().isEmpty()) {
			fileName = JOptionPane
					.showInputDialog("Please enter a file name: ");
			if (fileName == null || fileName.isEmpty()) {
				return false;
			}
			fileNameField.setText(fileName);
		}
		return true;
	}
}
