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

import constants.ButtonConstants;
import listeners.SaveButtonListener;
import listeners.ToggleFullScreenListener;

public class Controller {

	private Gui gui;
	private TextField textField;
	private JButton saveButton;
	private JButton toggleFullScreenButton;
	private JPanel menuPanel;

	private JButton[] buttons;
	private static final double SIDE_SCALE = 0.15;

	public Controller(Gui gui) {
		this.gui = gui;
		buttons = gui.getButtons();
		textField = gui.getTextField();
		saveButton = buttons[ButtonConstants.SAVE_BUTTON];
		menuPanel = gui.getMenuPanel();
		toggleFullScreenButton = buttons[ButtonConstants.FULLSCREEN_BUTTON];
		init();
	}

	private void init() {
		gui.addComponentListener(new SizeListener());
		textField.addKeyListener(new SmallKeyListener());
		saveButton.addActionListener(new SaveButtonListener(gui));
		toggleFullScreenButton.addActionListener(new ToggleFullScreenListener(
				this));
		menuPanel.addKeyListener(new SmallKeyListener());
		gui.addKeyListener(new SmallKeyListener());
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
