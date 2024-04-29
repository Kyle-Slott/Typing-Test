package models;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import utilities.FileUtilities;

public class MarkovDB {
	private File learnPath;
	private String[] str;
	private String randomWord;
	private MarkovChain<String, LinkedList<String>> mcp;
	
	public MarkovDB(File learnPath) throws IOException {
		super();
		this.learnPath = learnPath;
	}
	
	public void setPath(File path) {
		learnPath=path;
	}
	
	public MarkovChain<String, LinkedList<String>> buildMarkovDB() throws IOException {
		//int size = FileUtilities.getFileSize(learnPath.getPath());
		
		String text = FileUtilities.readFile(learnPath);
		String lastWord ="";
		String[] arr;
		
		text = text.replaceAll("[^a-zA-Z'\s\n]", "");
		text = text.replaceAll("(\n)", " ");
		text = text.toLowerCase();
		text = text.trim().replaceAll(" +", " ");
		arr = text.split(" ");
		mcp = new MarkovChain<String, LinkedList<String>>(arr.length);
		str = arr;
		generateRandomWord();
		for (String word: arr) {
			mcp.addData(word, lastWord);
			lastWord=word;
		}
		return mcp;
	}
	
	public void generateRandomWord() {
		String[] strArr = str;
		Random rand = new Random();
		randomWord=strArr[rand.nextInt(strArr.length)];
	}
	
	public boolean containsParent(String parent) {
		return mcp.containsParent(parent);
	}

	public String getRandomChild(String child) {
		return mcp.getRandomBaby(child);
	}
	
	public String getRandomWord() {
		return randomWord;
	}
}
