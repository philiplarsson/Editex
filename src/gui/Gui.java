package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Gui extends JFrame {

	private static final String TITLE = "Editex";
	private TextField textField;
	private JButton saveButton;
	private JButton toggleFullScreenButton;
	private Dimension oldDimension;
	private JPanel menuPanel;
	private JLabel courseLabel;
	private JTextField courseField;
	private JLabel fileNameLabel;
	private JTextField fileNameField;
	
	private boolean fullScreen;
	

	public Gui() {
		fullScreen = false;
		setUpGui();
		saveDimension();
		getFocusToTextField();
	}

	private void getFocusToTextField() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				textField.requestFocus();
//				textField.grabFocus();
			}

		});
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}
	
	public boolean menuIsShowing() {
		return menuPanel.isVisible();
	}
	
	public void showMenu(boolean bool) {
		menuPanel.setVisible(bool);
	}

	private void setUpGui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.err.println("Could not get Look and feel.");
			e.printStackTrace();
		}
		/**
		 * Initial Setups
		 */
		setLayout(new BorderLayout(4, 4));
		setTitle(TITLE);
		textField = new TextField();
		JScrollPane scrollPane = new JScrollPane(textField);
		add(scrollPane, BorderLayout.CENTER);

		// Top Menu
		JPanel leftPanel = new JPanel(new GridLayout(2,2));
		JPanel centerPanel = new JPanel();
		JPanel rightPanel = new JPanel(new GridBagLayout());
//		leftPanel.setBackground(Color.GREEN);

		JLabel label = new JLabel("Press F10 to hide menu");
		Font labelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 11);
		label.setFont(labelFont);
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(1, 3));
		saveButton = new JButton("Save");
		toggleFullScreenButton = new JButton("Fullscreen");
		saveButton.setFocusable(false);
		toggleFullScreenButton.setFocusable(false);

		courseField = new JTextField(8);
		courseLabel = new JLabel("Kurs:", SwingConstants.RIGHT);
		fileNameLabel = new JLabel("Filnamn:", SwingConstants.RIGHT);
		fileNameField = new JTextField(8);
		
		leftPanel.add(courseLabel);
		leftPanel.add(courseField);
		leftPanel.add(fileNameLabel);
		leftPanel.add(fileNameField);
		centerPanel.add(saveButton);
		centerPanel.add(toggleFullScreenButton);
		rightPanel.add(label);
		menuPanel.add(leftPanel);
		menuPanel.add(centerPanel);
		menuPanel.add(rightPanel);
		
		add(menuPanel, BorderLayout.PAGE_START);

		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public TextField getTextField() {
		return textField;
	}

	public Dimension getOldDimension() {
		return oldDimension;
	}

	public JPanel getMenuPanel() {
		return menuPanel;
	}

	public void saveDimension() {
		oldDimension = getSize();
	}
	
	public JButton getSaveButton() {
		return saveButton;
	}
	
	public JButton getToggleFullScreenButton() {
		return toggleFullScreenButton;
	}

	public JTextField getCourseField() {
		return courseField;
	}
	
	public JTextField getFileNameField() {
		return fileNameField;
	}
}
