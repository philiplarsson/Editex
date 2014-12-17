package listeners;

import gui.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleFullScreenListener implements ActionListener {

	private Controller controller;
	
	public ToggleFullScreenListener(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.toogleFullScreen();
	}

}
