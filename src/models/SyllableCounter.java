package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyllableCounter {
	private int syllableCount;

	public SyllableCounter() {
		this.syllableCount = 0;
	}

	public int getSyllableCount() {
		return syllableCount;
	}
	
	public int countSyllables(String text) {
		int count = 0;
		text = text.trim().replaceAll(" +", " ");
		String[] splitString = text.split(" ");
		for (String word: splitString) {
			count+= countWordSyllables(word);
		}
		syllableCount+=count;
		return count;
	}
	
	public int countWordSyllables(String word) {
		int count=0;
		word = word.toLowerCase();
		String stringPattern = "[aeiouy]+";
		Pattern splitter = Pattern.compile(stringPattern);
		Matcher match = splitter.matcher(word);
		String prev = "";
		while (match.find()) {
			count++;
			prev = match.group();
		}
		if ((prev.equals("e")) && (count > 1) && (word.charAt(word.length()-1)=='e')) {
			count--;
		}
		return count;
	}
}
