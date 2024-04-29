package models;

public class SentenceCounter {
	private int sentenceCount;

	public SentenceCounter() {
		this.sentenceCount = 0;
	}

	public int getSentenceCount() {
		return sentenceCount;
	}
	
	public int countSentences(String text) {
		int count = 1;
		String tempString = text.trim();
		char lastChar = ' ';
		if( tempString.length() > 0) {
			lastChar = tempString.charAt(tempString.length()-1);
		}
		if ((tempString.length()>0) && ((lastChar == '.') || ( lastChar == '?') || lastChar =='!')) {
			count-=1;
		}
		text = text.replaceAll("[\\.\\!\\?]+", ".");
		text = text.replaceAll("[^?!.]", "");
		count+=text.length();
		sentenceCount=count;
		return count;
	}
}
