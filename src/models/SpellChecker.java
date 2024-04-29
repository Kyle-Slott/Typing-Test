package models;

public class SpellChecker {
	private Dictionary dc;

	public SpellChecker() throws Exception {
		dc = new Dictionary();
		dc.buildDictionary();
	}
	
	public String spellCheck(String text) {
		String misspelledWords = "";
		if (text.length()==0) return "";
		char lastChar = text.charAt(text.length()-1);
		text = text.replaceAll("[^a-zA-Z'\s]", " ");
		text = text.trim().replaceAll(" +", " ");
		String[] words = text.split(" ");
		for (int i = 0; i<words.length-1; i++) {
			if (!(dc.searchDictionary(words[i]))) {
				if (!(dc.searchDictionary(words[i].toLowerCase()))) {
					misspelledWords+=words[i] + " ";
				}
			}
		}
		if (lastChar==' ') {
			if (!(dc.searchDictionary(words[words.length-1]))) {
				if (!(dc.searchDictionary(words[words.length-1].toLowerCase()))) {
					misspelledWords+=words[words.length-1] + " ";
				}
			}
		}
		return misspelledWords;
	}
}
