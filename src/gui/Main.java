package gui;


public class Main {

	public final static String LINK_FILE = ".linkFile";
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
//		final String LINK_FILE = ".linkfile";
		
		FileSaver fs = new FileSaver(LINK_FILE);
		
		Gui gui = new Gui();
		Controller controller = new Controller(gui);
		
	}

}
