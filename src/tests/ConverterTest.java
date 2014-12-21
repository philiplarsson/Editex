package tests;

import static org.junit.Assert.*;
import gui.Converter;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ConverterTest {
	
	Converter converter = new Converter();
	
	HashMap<String, String> map;
	
	@Before
	public void init() {
		map = converter.getHashMap();
	}

	@Test
	public void testCorrectStrings() {
		String returnString = map.get("+st");
		String returnString2 = map.get("st+");
		assertEquals(returnString, "<strong>");
		assertEquals(returnString2, "</strong>");
	}
	
	@Test
	public void testMoreStrings() {
		String s1 = map.get("+hr");
		String s2 = map.get("+h2");
		String s3 = map.get("h2+");
		String s4 = map.get("+i");
		String s5 = map.get("i+");
		assertEquals(s1, "<hr>");
		assertEquals(s2, "<h2>");
		assertEquals(s3, "</h2>");
		assertEquals(s4, "<i>");
		assertEquals(s5, "</i>");
	}
	
	@Test
	public void testConverter() {
		String line = "This is a +st fancy st+ line";
		String returnString = converter.convert(line);
		assertEquals(returnString, "This is a <strong> fancy </strong> line");
	}
	

}
