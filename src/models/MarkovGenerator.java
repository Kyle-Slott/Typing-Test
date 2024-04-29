package models;

import java.io.File;
import java.io.IOException;

public class MarkovGenerator {
	private File path;
	private MarkovDB db;

	public MarkovGenerator() throws IOException {
		this.path = new File("data/warAndPeace.txt");
		this.db = new MarkovDB(path);
	}
	
	public MarkovGenerator(File path) throws IOException {
		this.path = path;
		this.db = new MarkovDB(path);
		db.buildMarkovDB();
	}
	
	public String generateMarkov(int length, String startWord, String defaultWord) {
		String word = startWord;
		String text = "";
		String tempStr;
		if (!(db.containsParent(word))) {
			return "Starting word not found";
		}else {
			for (int i= 0; i<length; i++) {
				if (!db.containsParent(word)) {
					word = defaultWord;
					i--;
				}else {
					tempStr = db.getRandomChild(word);
					text+= word +" ";
					word = tempStr;
				}
			}
		}
		return text;
	}
	
	public String generateMarkov(String startWord, String defaultWord) {
		return generateMarkov(20, startWord, defaultWord);
	}
	
	public String generateMarkov(int length, String defaultWord) {
		return generateMarkov(length, "temp", defaultWord);
	}
	
	public String generateMarkov(String defaultWord) {
		return generateMarkov(20, "temp", defaultWord);
	}
	
	public String generateMarkov(int length) {
		return generateMarkov(length, "temp", "for");
	}
	
	public String generateMarkov() {
		return generateMarkov(20, "for", "for");
	}
	
	public boolean containsParent(String parent) {
		return db.containsParent(parent);
	}
	
	public File getPath() {
		return path;
	}
	
	public String getRandomWord() {
		return db.getRandomWord();
	}
	
	public String generateRandomWord() {
		db.generateRandomWord();
		return db.getRandomWord();
	}

	public void setPath(File path) throws IOException {
		this.path = path;
		db.setPath(path);
		db.buildMarkovDB();
	}
	
}
