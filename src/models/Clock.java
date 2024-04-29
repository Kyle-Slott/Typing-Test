package models;

public class Clock {
	private long startTime;
	@SuppressWarnings("unused")
	private long estimatedTime;
	
	public Clock () {
		startTime = System.nanoTime(); 
	}
	
	public long getTime () {
		long estimatedTime = System.nanoTime() - startTime;
		return estimatedTime / 1000000000;
	}
	
	public void resetTime () {
		startTime = System.nanoTime(); 
	}
}
