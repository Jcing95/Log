package de.jcing.utillities.log.appender;

import java.io.PrintStream;

public class PrintStreamAppender extends Appender {
	
	protected PrintStream stream;
	
	public PrintStreamAppender(PrintStream stream) {
		this.stream = stream;
	}
	
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
}
