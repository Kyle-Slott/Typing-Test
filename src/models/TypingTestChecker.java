package models;

public class TypingTestChecker {
	public TypingTestChecker() {}
	
	public double testCheck (String displayString, String userString) {
		double percent=0;
		int count=0;
		
		if (0==displayString.length()) return 0;
		if (0==userString.length()) return 100;
		
		displayString = displayString.toLowerCase();
		userString = userString.toLowerCase();
		
		for (int i=0; i<userString.length();i++) {
				if (displayString.charAt(i)==userString.charAt(i)) count++;
		}

		percent = (double)count / (double)userString.length();
		return percent * 100;
	}
}
