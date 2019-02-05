package de.jcing.utillities.log;

import java.io.PrintStream;

public abstract class Appender {
	
	public static final String LINE_SEPERATOR = System.getProperty("line.separator");
	
	//TODO: make fully abstract - remove stream. Do Streamappender class for example
	protected PrintStream stream;

	public Appender(PrintStream stream) {
		this.stream = stream;
	}
	
	public Appender() {
		
	}
	
	public abstract void print(String...strings);
	
	public void println(String...strings) {
		strings[strings.length-1] += LINE_SEPERATOR;
		print(strings);
	}
		
	public PrintStream getStream() {
		return stream;
	}
	
	public static Appender createFromStream(PrintStream stream) {
		return new Appender(stream) {
			public void print(String...strings) {
				if(strings.length > 1) {
					String tmp = "";
					for(int i = 0; i < strings.length-1; i++) {
						tmp += strings[i];
					}
					tmp += ": ";
					tmp += strings[strings.length-1];
					this.stream.print(tmp);
				}
				else
					this.stream.print(strings[0]);
			}
		};
	}
	
}
