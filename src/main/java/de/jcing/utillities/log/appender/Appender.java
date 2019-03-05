package de.jcing.utillities.log.appender;

public abstract class Appender {
	
	public static final String LINE_SEPERATOR = System.getProperty("line.separator");
		
	public abstract void print(String...strings);
	
	public void println(String...strings) {
		strings[strings.length-1] += LINE_SEPERATOR;
		print(strings);
	}	
}
