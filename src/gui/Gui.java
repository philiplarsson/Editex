package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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

import constants.ButtonConstants;
import constants.FieldConstants;
import constants.LabelConstants;

@SuppressWarnings("serial")
public class Gui extends JFrame {

	private static final String TITLE = "EdiOtex";
	private TextField textField;
	private JButton saveButton;
	private JButton toggleFullScreenButton;
	private JButton indexButton;
	private Dimension oldDimension;
	private JPanel menuPanel;
	private JLabel courseLabel;
	private JLabel fileNameLabel;
	private JTextField courseField;
	private JTextField fileNameField;

	private boolean fullScreen;

	private JLabel labels[];
	private JButton buttons[];
	private JTextField fields[];

	public Gui() {
		fullScreen = false;

		labels = new JLabel[20];
		buttons = new JButton[10];
		fields = new JTextField[20];

		setUpGui();
		saveDimension();
		getFocusToTextField();

	}

	private void getFocusToTextField() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				textField.requestFocus();
				// textField.grabFocus();
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
		JPanel leftPanel = new JPanel(new GridLayout(2, 2));
		// JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1,
		// 1));
		JPanel centerPanel = new JPanel(new GridLayout(2, 2));
		JPanel rightPanel = new JPanel(new GridBagLayout());
		// leftPanel.setBackground(Color.GREEN);

		JLabel label = new JLabel("Press F10 to hide menu");
		labels[LabelConstants.PRESS_F10_LABEL] = label;

		Font labelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 11);
		label.setFont(labelFont);
		menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(1, 3));
		saveButton = new JButton("Save");
		buttons[ButtonConstants.SAVE_BUTTON] = saveButton;
		toggleFullScreenButton = new JButton("Fullscreen");
		buttons[ButtonConstants.FULLSCREEN_BUTTON] = toggleFullScreenButton;
		indexButton = new JButton("Index Files");
		buttons[ButtonConstants.INDEXING_BUTTON] = indexButton;
		saveButton.setFocusable(false);
		toggleFullScreenButton.setFocusable(false);
		indexButton.setFocusable(false);

		courseField = new JTextField(8);
		fields[FieldConstants.COURSE_FIELD] = courseField;
		courseLabel = new JLabel("Kurs:", SwingConstants.RIGHT);
		labels[LabelConstants.COURSE_LABEL] = courseLabel;
		fileNameLabel = new JLabel("Filnamn:", SwingConstants.RIGHT);
		labels[LabelConstants.FILENAME_LABEL] = fileNameLabel;
		fileNameField = new JTextField(8);
		fields[FieldConstants.FILENAME_FIELD] = fileNameField;

		leftPanel.add(courseLabel);
		leftPanel.add(courseField);
		leftPanel.add(fileNameLabel);
		leftPanel.add(fileNameField);
		centerPanel.add(saveButton);
		centerPanel.add(toggleFullScreenButton);
		centerPanel.add(indexButton);
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

	public JLabel[] getLabels() {
		return labels;
	}

	public JButton[] getButtons() {
		return buttons;
	}

	public JTextField[] getFields() {
		return fields;
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

}
