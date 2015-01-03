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
		String s6 = map.get("+hr");
		assertEquals(s1, "<hr>");
		assertEquals(s2, "<h2>");
		assertEquals(s3, "</h2>");
		assertEquals(s4, "<i>");
		assertEquals(s5, "</i>");
		assertEquals(s6, "<hr>");
	}

	@Test
	public void testConverter() {
		String line = "This is a +st fancy st+ line";
		String returnString = converter.convert(line);
		assertEquals("This is a <strong> fancy </strong> line", returnString);
	}

	@Test
	public void testWithNewLines() {
		String line = "Lorem ipsum dolor sit amet, consectetur\n"
				+ " adipiscing elit, sed do eiusmod tempor incididunt\n"
				+ " ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n"
				+ "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ";
		String returnLine = "Lorem ipsum dolor sit amet, consectetur\n"
				+ " adipiscing elit, sed do eiusmod tempor incididunt\n"
				+ " ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n"
				+ "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ";
		assertEquals(returnLine, line);
	}

	@Test
	public void testWhenNewLine() {
		String line = "This should be a +st strong line st+\n And +i italic i+.";
		String returnString = converter.convert(line);
		assertEquals("This should be a <strong> strong line </strong> \n And <i> italic </i> .",
				returnString);
	}
	
	@Test
	public void testWithOnlyOne() {
		String line = "+hr";
		String returnLine = converter.convert(line);
		assertEquals("<hr>", returnLine);
	}

}
