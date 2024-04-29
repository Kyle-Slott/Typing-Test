package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import utilities.FileUtilities;

public class Dictionary {
	private HashSet<String> dictionary;
	private String dictionaryPath = "resources/dictionary.txt";
	
	public void buildDictionary() throws IOException {
		int size = FileUtilities.getFileSize(dictionaryPath);
		dictionary = new HashSet<String>(2*size);
		String word;
		BufferedReader br = new BufferedReader(new FileReader(dictionaryPath));
		while ((word=br.readLine())!=null) {
			dictionary.add(word);
		}
		br.close();
	}
	
	public boolean searchDictionary(String word) {
		return dictionary.contains(word);
	}
}
