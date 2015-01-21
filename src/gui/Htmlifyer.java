package gui;

import java.util.ArrayList;
import java.util.List;

public final class Htmlifyer {

	private static final String INDEX_FILE_TITLE = "Index file";

	private Htmlifyer() {
		// IMA private constructor.
	}

	/**
	 * Returns the specified string with html "padding".
	 * 
	 * @param text
	 *            a string that should be wrapped in html.
	 * @param title
	 *            a string that should be in the html-title tag.
	 * @return a html-wrapped string.
	 */
	public static String htmlIfyText(String text, String title) {
		StringBuilder sb = new StringBuilder();
		List<String> htmlHeadList = getHtmlHead(title);
		for (String line : htmlHeadList) {
			sb.append(line);
		}
		sb.append(text + "\n");

		List<String> htmlFeetList = getHtmlFeet();
		for (String line : htmlFeetList) {
			sb.append(line);
		}
		return sb.toString();
	}

	/**
	 * Returns the specified string with html "padding". This method gets the
	 * index file head and is therefore to be used with the index file.
	 * 
	 * @param text
	 *            a string that should be wrapped in html.
	 * @param title
	 *            a string that should be in the html-title tag.
	 * @return a html-wrapped string.
	 */
	public static String htmlIfyTextForIndex(String text, String title) {
		StringBuilder sb = new StringBuilder();
		List<String> htmlHeadList = getHtmlHeadForIndex(title);
		for (String line : htmlHeadList) {
			sb.append(line);
		}
		sb.append(text + "\n");

		List<String> htmlFeetList = getHtmlFeet();
		for (String line : htmlFeetList) {
			sb.append(line);
		}
		return sb.toString();
	}

	/**
	 * Creates index file from links.
	 * 
	 * @param links
	 *            is a list with all links (without BEGIN and END).
	 */
	public static String getIndexFile(List<String> links) {
		StringBuilder sb = new StringBuilder();
		List<String> indexText = getIndexFileText();
		for (String text : indexText) {
			sb.append(text);
		}
		String course;
		String oldCourseName = "";

		for (String link : links) {
			course = getCourseName(link);
			if (oldCourseName.equals("")) {
				// Första "gången"
				sb.append("<h2> " + course + " </h2> \n");
				sb.append("<ul> \n");
				oldCourseName = course;
			} else if (!oldCourseName.equals(course)) {
				// Ny kurs
				sb.append("</ul> \n");
				sb.append("<h2> " + course + " </h2> \n");
				sb.append("<ul> \n");
			}
			sb.append(" <li> <a href=\" " + link + "\">" + link
					+ "</a> </li> \n");
			oldCourseName = course;
		}
		sb.append("</ul> \n");
		String indexFile = htmlIfyTextForIndex(sb.toString(), INDEX_FILE_TITLE);

		return indexFile;
	}

	/**
	 * Returns the course name from the entered link string.
	 * 
	 * @param link
	 *            a string that contains the course name.
	 * @return a string with the course name.
	 */
	private static String getCourseName(String link) {
		String parts[] = link.split("/");

		return parts[0];
	}

	/**
	 * Returns a list with html-head tags for easy usage.
	 * 
	 * @param title
	 *            a string that should be in the html-title tag.
	 * @return a list that contains the html head tags.
	 */
	private static List<String> getHtmlHead(String title) {
		List<String> htmlHeadList = new ArrayList<String>();
		htmlHeadList.add("<!doctype html> \n");
		htmlHeadList.add("<html lang=\"en\"> \n");
		htmlHeadList.add(" <head> \n");
		htmlHeadList.add("  <meta charset=\"utf-8\"> \n");
		htmlHeadList
				.add("  <link href=\"../css/style.css\" rel=\"stylesheet\"> \n");
		htmlHeadList
				.add("  <!-- Fonts --> \n <link href=\"http://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\" type=\"text/css\"> \n");
		htmlHeadList.add("  <title>" + title + "</title> \n");
		htmlHeadList.add(" </head> \n");
		htmlHeadList.add("<body> \n \n");
		htmlHeadList.add("<div class=\"link-box\">");
		htmlHeadList.add("<a href=\"../index.html\">Back to index</a>");
		htmlHeadList.add("</div>");

		htmlHeadList.add("<div class=\"main-block\">");
		htmlHeadList.add("<p> \n");
		return htmlHeadList;
	}

	/**
	 * Returns a list with html-head tags for the index file.
	 * 
	 * @param title
	 *            a string that should be in the html-title tag.
	 * @return a list that contains the html head tags.
	 */
	private static List<String> getHtmlHeadForIndex(String title) {
		List<String> htmlHeadList = new ArrayList<String>();
		htmlHeadList.add("<!doctype html> \n");
		htmlHeadList.add("<html lang=\"en\"> \n");
		htmlHeadList.add(" <head> \n");
		htmlHeadList.add("  <meta charset=\"utf-8\"> \n");
		htmlHeadList
				.add("  <link href=\"css/style.css\" rel=\"stylesheet\"> \n");
		htmlHeadList
				.add("  <!-- Fonts --> \n <link href=\"http://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\" type=\"text/css\"> \n");
		htmlHeadList.add("  <title>" + title + "</title> \n");
		htmlHeadList.add(" </head> \n");
		htmlHeadList.add("<body> \n \n");

		htmlHeadList.add("<div class=\"main-block\">");
		htmlHeadList.add("<p> \n");
		return htmlHeadList;
	}

	/**
	 * Returns a list with the html feet tag that is used at the bottom of a
	 * html file.
	 * 
	 * @return a list with html tags.
	 */
	private static List<String> getHtmlFeet() {
		List<String> htmlFeetList = new ArrayList<String>();
		htmlFeetList.add("</p> \n \n");
		htmlFeetList.add("</div>");
		htmlFeetList.add("</body> \n");
		htmlFeetList.add("</html> \n");
		return htmlFeetList;
	}

	/**
	 * Returns a list with the top text for the index file.
	 * 
	 * @return a list with the index text.
	 */
	private static List<String> getIndexFileText() {
		List<String> indexFileText = new ArrayList<String>();
		indexFileText.add("<div class=\"link-box\">");
		indexFileText.add(" <h1> Index File </h1> \n");
		indexFileText.add("</div>");
		indexFileText.add(" <p> Below is your notes: </p> \n");

		return indexFileText;
	}

}
