package listeners;

import gui.FileSaver;
import gui.Gui;
import gui.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import constants.FieldConstants;

public class SaveButtonListener implements ActionListener {

	private Gui gui;
	private JTextField fields[];
	private JTextField fileNameField;
	private JTextField courseNameField;
	private FileSaver fileSaver;
	
	private static final String FILE_NAME_ENDING = ".txt";

	public SaveButtonListener(Gui gui) {
		this.gui = gui;
		fields = gui.getFields();
		fileNameField = fields[FieldConstants.FILENAME_FIELD];
		courseNameField = fields[FieldConstants.COURSE_FIELD];
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String fileName;
		String courseName;

		if (courseNameField.getText().isEmpty()) {
			courseName = JOptionPane
					.showInputDialog("Please enter a course name: ");
			if (courseName == null || courseName.isEmpty()) {
				return;
			}
			courseNameField.setText(courseName);
		}

		if (fileNameField.getText().isEmpty()) {
			fileName = JOptionPane
					.showInputDialog("Please enter a file name: ");
			if (fileName == null || fileName.isEmpty()) {
				return;
			}
			fileNameField.setText(fileName);
		}

		String newFileName = courseNameField.getText() + "-"
				+ fileNameField.getText() + FILE_NAME_ENDING;
		fileSaver = new FileSaver(Main.LINK_FILE);
		if (fileSaver.fileExists(newFileName)) {
			int answer = JOptionPane.showConfirmDialog(null, "File ''"
					+ newFileName + "'' already exists. Overwrite? ", "Oops",
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.NO_OPTION
					|| answer == JOptionPane.CLOSED_OPTION) {
				return;
			}
		} else {
			// File doesn't exists
			fileSaver.createFile(newFileName);
		}
//		System.out.println(gui.getTextField().getText());
		fileSaver.saveToFile(gui.getTextField().getText(), newFileName);
//		System.out.println("I should save this file as: " + newFileName);

	}

}
