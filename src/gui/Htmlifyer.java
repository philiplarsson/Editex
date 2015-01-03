package gui;

public final class Htmlifyer {

	private Htmlifyer() {
		// IMA private constructor.
	}
	
	public static String htmlIfyText(String text, String title) {
		StringBuilder sb = new StringBuilder();
		text = text.replace("\n", "<br>");
		sb.append("<!doctype html> \n");
		sb.append("<html lang=\"en\"> \n");
		sb.append(" <head> \n");
		sb.append("  <meta charset=\"utf-8\"> \n");
		sb.append("  <title>" + title + "</title> \n");
		sb.append(" </head> \n");
		sb.append("<body> \n \n");

		sb.append("<p> \n");
		sb.append(text+ "\n");
		sb.append("</p> \n \n");
		sb.append("</body> \n");
		sb.append("</html> \n");		
		return sb.toString();
	}
}
