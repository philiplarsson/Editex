package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listeners.IndexButtonListener;
import listeners.SaveButtonListener;
import listeners.ToggleFullScreenListener;
import constants.ButtonConstants;
import constants.FieldConstants;

public class Controller {

	private Gui gui;
	private TextField textField;
	private JButton saveButton;
	private JButton toggleFullScreenButton;
	private JButton indexButton;
	private JPanel menuPanel;
	private JTextField fileNameField;
	
	private JButton[] buttons;
	private JTextField[] textFields;
	
	private static final double SIDE_SCALE = 0.15;

	public Controller(Gui gui) {
		this.gui = gui;
		
		buttons = gui.getButtons();
		textFields = gui.getFields();
		textField = gui.getTextField();
		saveButton = buttons[ButtonConstants.SAVE_BUTTON];
		toggleFullScreenButton = buttons[ButtonConstants.FULLSCREEN_BUTTON];
		indexButton = buttons[ButtonConstants.INDEXING_BUTTON];
		menuPanel = gui.getMenuPanel();
		fileNameField = textFields[FieldConstants.FILENAME_FIELD];
		
		init();
	}

	private void init() {
		SaveButtonListener sbl = new SaveButtonListener(gui);
		gui.addComponentListener(new SizeListener());
		textField.addKeyListener(new SmallKeyListener());
		saveButton.addActionListener(sbl);
		toggleFullScreenButton.addActionListener(new ToggleFullScreenListener(
				this));
		indexButton.addActionListener(new IndexButtonListener());
		menuPanel.addKeyListener(new SmallKeyListener());
		gui.addKeyListener(new SmallKeyListener());
		fileNameField.addActionListener(sbl);
	}

	public void toogleFullScreen() {
		if (gui.isFullScreen()) {
			gui.setExtendedState(JFrame.NORMAL);
			Dimension oldDimension = gui.getOldDimension();
			gui.setSize(oldDimension.width, oldDimension.height);
			gui.setFullScreen(false);
		} else {
			gui.saveDimension();
			gui.setFullScreen(true);
			gui.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
	}

	private void hideOrShowPanel() {
		if (gui.menuIsShowing()) {
			gui.showMenu(false);
		} else {
			gui.showMenu(true);
		}
	}

	private class SmallKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			switch (key) {
			case KeyEvent.VK_F5:
				toogleFullScreen();
				break;
			case KeyEvent.VK_F10:
				hideOrShowPanel();
				break;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	}

	private class SizeListener implements ComponentListener {

		@Override
		public void componentHidden(ComponentEvent e) {
		}

		@Override
		public void componentMoved(ComponentEvent e) {
		}

		@Override
		public void componentResized(ComponentEvent e) {
			Dimension guiSize = gui.getSize();
			int sizePadding = (int) (guiSize.width * SIDE_SCALE);
			int topAndBottomPadding = (int) (guiSize.height * 0.1);
			topAndBottomPadding = (topAndBottomPadding > 60) ? 60
					: topAndBottomPadding;
			int textWidth = guiSize.width - 2 * sizePadding;
			sizePadding = (textWidth > 700) ? (guiSize.width - 700) / 2
					: sizePadding;
			textField.setMargin(new Insets(topAndBottomPadding, sizePadding,
					topAndBottomPadding, sizePadding));
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
		}
	}

}
