package de.jcing.utillities.log.appender;

import java.io.PrintStream;

public abstract class AppenderFactory {
	
	private AppenderFactory() {}
	
	
	public static Appender createFromStream(PrintStream stream) {
		return new PrintStreamAppender(stream);
	}
	
	public static Appender getConsoleAppender() {
		return new PrintStreamAppender(System.out);
	}
	
	public static Appender getConsoleErrorAppender() {
		return new PrintStreamAppender(System.err);
	}
	
}
