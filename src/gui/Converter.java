package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Converts string to html code. Delimiter is § for now.
 * 
 * @author phiip
 * 
 */
public class Converter {

	private HashMap<String, String> map = new HashMap<String, String>();
	private final static String DELIMETER = "§";
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
	 * Returns the HashMap that contains the keyWords and their corresponding
	 * html-code.
	 * 
	 * @return a HashMap<String, String>.
	 */
	public HashMap<String, String> getHashMap() {
		return map;
	}

	/**
	 * Converts the specified string to html code.
	 * 
	 * @param text
	 *            is a string that should be converted
	 * @return a string that contains the html tags.
	 */
	public String convert(String text) {

		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> pairs = (Entry<String, String>) iterator
					.next();
			text = text.replaceAll(pairs.getKey(), pairs.getValue());
		}

		return text;
	}
}
