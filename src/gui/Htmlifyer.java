package gui;

import java.util.ArrayList;
import java.util.List;

public final class Htmlifyer {
	
	private static final String INDEX_FILE_TITLE = "Index file";
	
	
	private Htmlifyer() {
		// IMA private constructor.
	}
	
	public static String htmlIfyText(String text, String title) {
		StringBuilder sb = new StringBuilder();
		text = text.replace("\n", "<br> \n");
		List<String> htmlHeadList = getHtmlHead(title);
		for (String line : htmlHeadList) {
			sb.append(line);
		}		
		sb.append(text + "\n");
		
		List<String> htmlFeetList = getHtmlFeet();
		for (String line: htmlFeetList) {
			sb.append(line);
		}
		return sb.toString();
	}
	
	/**
	 * Creates index file from links.
	 * @param links is a list with all links (without BEGIN and END).
	 */
	public static String getIndexFile(List<String> links) {
		StringBuilder sb = new StringBuilder();
		List<String> indexText = getIndexFileText();
		for (String text : indexText) {
			sb.append(text);
		}
		
		sb.append("<ul> \n");
		for (String link: links) {
			sb.append(" <li> <a href=\" " + link + "\">"+ link + "</a> </li> \n");
		}
		sb.append("</ul> \n");
		String indexFile = htmlIfyText(sb.toString(), INDEX_FILE_TITLE);
		
		return indexFile;
	}
	
	private static List<String> getHtmlHead(String title) {
		List<String> htmlHeadList = new ArrayList<String>();
		htmlHeadList.add("<!doctype html> \n");
		htmlHeadList.add("<html lang=\"en\"> \n");
		htmlHeadList.add(" <head> \n");
		htmlHeadList.add("  <meta charset=\"utf-8\"> \n");
		htmlHeadList.add("  <title>" + title + "</title> \n");
		htmlHeadList.add(" </head> \n");
		htmlHeadList.add("<body> \n \n");
		htmlHeadList.add("<p> \n");
		return htmlHeadList;		
	}
	
	private static List<String> getHtmlFeet() {
		List<String> htmlFeetList = new ArrayList<String>();
		htmlFeetList.add("</p> \n \n");
		htmlFeetList.add("</body> \n");
		htmlFeetList.add("</html> \n");		
		return htmlFeetList;
	}
	
	private static List<String> getIndexFileText() {
		List<String> indexFileText = new ArrayList<String>();
		indexFileText.add("<h1>Index file </h1> \n");
		indexFileText.add(" <p> Below is your notes: </p> \n");
		
		return indexFileText;
	}
}
