package listeners;

import gui.FileSaver;
import gui.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SaveButtonListener implements ActionListener {
	
	private Gui gui;
	private JTextField fileNameField;
	private JTextField courseNameField;
	private FileSaver fileSaver;
	
	public SaveButtonListener(Gui gui) {
		this.gui = gui;
		fileNameField = gui.getFileNameField();
		courseNameField = gui.getCourseField();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String fileName;
		String courseName;
		
		if (courseNameField.getText().isEmpty()) {
			courseName = JOptionPane.showInputDialog("Please enter a course name: ");
			if (courseName == null || courseName.isEmpty()) {
				return;
			}
			// TODO: Check if course name is ok.
			courseNameField.setText(courseName);
		}
		
		if (fileNameField.getText().isEmpty()) {
			fileName = JOptionPane.showInputDialog("Please enter a file name: ");
			if (fileName == null || fileName.isEmpty()) {
				return;
			}
			// TODO: Check if file name is ok.
			fileNameField.setText(fileName);
		}
		
		String newFileName = courseNameField.getText() + "-" + fileNameField.getText();
		fileSaver = new FileSaver(newFileName);
		if (fileSaver.fileExists(newFileName)) {
			int answer = JOptionPane.showConfirmDialog(null, "File ''" + newFileName + "'' already exists. Overwrite? ", "Oops", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.NO_OPTION || answer == JOptionPane.CLOSED_OPTION) {
				return;
			}
		}
		System.out.println(gui.getTextField().getText());
		fileSaver.saveFile(gui.getTextField().getText());
		System.out.println("I should save this file as: " + newFileName);
		
	}

}
