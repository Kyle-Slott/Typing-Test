package models;

public class WordCounter {
	private int wordCount;

	public WordCounter() {
		this.wordCount = 0;
	}

	public int getWordCount() {
		return wordCount;
	}
	
	public int countWords(String text) {
		int count = 1;
		text = text.trim().replaceAll(" +", " ");
		for (int i=0; i<text.length(); i++) {
			char letter = text.charAt(i);
			if ((letter==' ') || (letter == '\n')) count++;
		}
		wordCount=count;
		return count;
	}
}
