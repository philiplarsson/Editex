package gui;


public class Main {

		
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		FileHandler fh = new FileHandler();
		
		Gui gui = new Gui();
		Controller controller = new Controller(gui);
		
	}

}
