package models;

public class StatusBar {
	private WordCounter wordCounter = new WordCounter();
	private SentenceCounter sentenceCounter = new SentenceCounter();
	private SyllableCounter syllableCounter = new SyllableCounter();
	private double fleschScore;
	private int wordCount;
	private int sentenceCount;
	private int syllableCount;
	
	public StatusBar() {
		fleschScore = 0;
		wordCount = 0;
		sentenceCount = 0;
		syllableCount = 0;
	}
	
	public StatusBar(String text) {
		updateAll(text);
	}
	
	public double getFleschScore() {
		return fleschScore;
	}
	public int getWordCount() {
		return wordCount;
	}
	public int getSentenceCount() {
		return sentenceCount;
	}
	public int getSyllableCount() {
		return syllableCount;
	}
	public void updateAll(String text) {
		updateSyllableCount(text);
		updateWordCount(text);
		updateSentenceCount(text);
		updateFleschScore();
	}
	public double updateFleschScore() {
		fleschScore = 206.835 - (1.015 * (double) wordCount / (double) sentenceCount);
		fleschScore -= (84.6 * ((double) syllableCount / (double) wordCount));
		return fleschScore;
	}
	public int updateWordCount(String text) {
		wordCount = wordCounter.countWords(text);
		return wordCount;
	}
	public int updateSentenceCount(String text) {
		sentenceCount = sentenceCounter.countSentences(text);
		return sentenceCount;
	}
	public int updateSyllableCount(String text) {
		syllableCount = syllableCounter.countSyllables(text);
		return syllableCount;
	}
}
