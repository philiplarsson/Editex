package gui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Converts string to html code. Delimiter is + for now. 
 * @author phiip
 *
 */
public class Converter {

	private HashMap<String, String> map = new HashMap<String, String>();
	private final static String DELIMETER = "+";
	private ArrayList<String> keyWords = new ArrayList<String>();

	public Converter() {
		fixKeyWords();
		fixHashMap();
	}

	/**
	 * Adds html "keywords" to the keyWords arraylist.
	 */
	private void fixKeyWords() {
		keyWords.add("strong");
		keyWords.add("i");
		keyWords.add("h1");
		keyWords.add("h2");
		keyWords.add("h3");
		keyWords.add("hr");
	}

	/**
	 * Adds the keywords to the HashMap.
	 */
	private void fixHashMap() {
		String keyWord;
		for (int i = 0; i < keyWords.size(); i++) {
			keyWord = keyWords.get(i);
			if (keyWord.length() > 2) {
				keyWord = keyWord.substring(0, 2);
			}
			map.put(DELIMETER + keyWord, "<" + keyWords.get(i) + ">");
			map.put(keyWord + DELIMETER, "</" + keyWords.get(i) + ">");
		}
	}

	/**
	 * Returns the HashMap that contains the keyWords and their corresponding html-code.
	 * @return a HashMap<String, String>.
	 */
	public HashMap<String, String> getHashMap() {
		return map;
	}

	/**
	 * Converts the specified string to html code.
	 * @param text is a string that should be converted
	 * @return a string that contains the html tags.
	 */
	public String convert(String text) {
		if (text.contains(".")) {
			text = text.replace(".", " .");
		}
		String parts[] = text.split("[ \t\\x0B\f\r]+|(?=\n)");
		//TODO: Bättre regex, fungerar inte när key är på ny rad, ex börjar med +st .
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < parts.length; i++) {
			if (map.containsKey(parts[i])) {
				sb.append(map.get(parts[i]));
			} else {
				sb.append(parts[i]);
			}
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
