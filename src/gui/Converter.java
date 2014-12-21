package gui;

import java.util.ArrayList;
import java.util.HashMap;

public class Converter {

	private HashMap<String, String> map = new HashMap<String, String>();
	private final static String DELIMETER = "+";
	private ArrayList<String> keyWords = new ArrayList<String>();
	
	
	public Converter() {
		fixKeyWords();
		fixHashMap();
	}
	
	private void fixKeyWords() {
		keyWords.add("strong");
		keyWords.add("i");
		keyWords.add("h1");
		keyWords.add("h2");
		keyWords.add("h3");
		keyWords.add("hr");
	}
	
	
	private void fixHashMap() {
		String keyWord;
		for (int i = 0; i < keyWords.size(); i++) {
			keyWord = keyWords.get(i);
			if (keyWord.length() > 2) {
				keyWord = keyWord.substring(0, 2);
			} 
				map.put(DELIMETER + keyWord, "<" + keyWords.get(i) +">");
				map.put(keyWord + DELIMETER, "</" + keyWords.get(i) + ">");
		}
	}
	
	public HashMap<String, String> getHashMap() {
		return map;
	}
	
	public String convert(String text) {
		String parts[] = text.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < parts.length; i++) {
			if (map.containsKey(parts[i])) {
				sb.append(map.get(parts[i]));
			} else {
				sb.append(parts[i]);
			}
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
