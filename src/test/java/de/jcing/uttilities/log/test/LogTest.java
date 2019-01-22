package de.jcing.uttilities.log.test;
import de.jcing.utillities.log.Log;

public class LogTest {
	
	public static void main(String[] args) {
		
		Log.setDefaultAppender(Log.LOG_LEVEL.error, Log.CONSOLE);
		
		Log log = new Log(LogTest.class);
		log.debug("debug" );
		log.info("info");
		log.error("error");
		
		Log.printLogLevel(false);
		log.debug("debug" );
		log.info("info");
		log.error("error");
		
		Log.printNames(false);
		log.debug("debug" );
		log.info("info");
		log.error("error");
		
		Log.printLogLevel(true);
		log.debug("debug" );
		log.info("info");
		log.error("error");
		
	}
	
	
}
