package models;

import java.util.LinkedList;
import java.util.Random;
import java.util.TreeMap;

public class MarkovChain <E, T>{
	private TreeMap<String, LinkedList<String>> parentMap;

	public MarkovChain (int capacity) {
		super();
		this.parentMap = new TreeMap<String, LinkedList<String>>();
	}
	
	public MarkovChain () {
		super();
		this.parentMap = new TreeMap<String, LinkedList<String>>();
	}
	
	public void addChild(String parent, String child) {
		parentMap.get(parent).add(child);
	}
	
	public int getChildSize(String parent) {
		return parentMap.get(parent).size();
	}
	
	public String getBaby(String parent, int index) {
		return parentMap.get(parent).get(index);
	}
	
	public String getRandomBaby(String parent) {
		if (getChildSize(parent)==0){
			return "Child Not Found";
		}
		Random rand = new Random();
		int randBaby = rand.nextInt(getChildSize(parent));
		return parentMap.get(parent).get(randBaby);
	}
	
	public void addData(String data, String lastWord) {
		boolean contains = parentMap.containsKey(data);
		if (contains==false) {
			parentMap.put(data, new LinkedList<String>());
		}else {
			parentMap.get(lastWord).add(data);	
		}
	}

	public boolean containsParent(String parent) {
		return parentMap.containsKey(parent);
	}
}
