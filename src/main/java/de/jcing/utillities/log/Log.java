package de.jcing.utillities.log;

import java.util.HashMap;

import de.jcing.utillities.log.appender.Appender;
import de.jcing.utillities.log.appender.AppenderFactory;

public class Log {
	
	public static final Appender CONSOLE = AppenderFactory.getConsoleAppender();
	public static final Appender CONSOLE_ERROR = AppenderFactory.getConsoleErrorAppender();
	
	public static enum LOG_LEVEL {
		debug,
		info,
		error
	}
	
	public static enum SETTINGS {
		print_time,
		print_names,
		print_level		
	}
	
	public static enum VALUE {
		on,
		off,
	}
	
	private static final int LEVEL_DEBUG = 0;
	private static final int LEVEL_INFO = 10;
	private static final int LEVEL_ERROR = 20;
	
	protected static HashMap<LOG_LEVEL,Appender> _globalAppenders = new HashMap<LOG_LEVEL,Appender>();

	protected static HashMap<SETTINGS, VALUE> _globalSettings = new HashMap<SETTINGS, VALUE>();
	
	static {
		_globalAppenders.put(LOG_LEVEL.debug, CONSOLE);
		_globalAppenders.put(LOG_LEVEL.info, CONSOLE);
		_globalAppenders.put(LOG_LEVEL.error, CONSOLE_ERROR);
		_globalSettings.put(SETTINGS.print_level, VALUE.on);
		_globalSettings.put(SETTINGS.print_names, VALUE.on);
		_globalSettings.put(SETTINGS.print_time, VALUE.off);
	}
	
	protected static boolean _printName = true;
	protected static boolean _printLevel = true;	
	//TODO: printTime - use Settings enum
	
	protected static int _logLevel = LEVEL_DEBUG;
	
	protected static final String DEBUG = "DBG - ";
	protected static final String INFO = "INF - ";
	protected static final String ERROR = "ERR - ";

	protected Class<?> clazz;
	protected String name;
	
	protected HashMap<LOG_LEVEL, Appender> appenders;
	protected HashMap<SETTINGS,VALUE> settings;
	
	protected int level;
	protected boolean mute;
	
	public Log (Class<?> clazz) {
		this(clazz.getName());
		this.clazz = clazz;
	}
	
	public Log(String name) {
		this.name = name;
		appenders = new HashMap<LOG_LEVEL, Appender>();
		appenders.putAll(_globalAppenders);
		level = _logLevel;
		settings = new HashMap<SETTINGS,VALUE>();
		settings.putAll(_globalSettings);
	}
	
	public Log mute(boolean mute) {
		this.mute = mute;
		return this;
	}
	
	public void debug(String s) {
		if(mute || level > LEVEL_DEBUG)
			return;
		if(_printName || _printLevel) {
			if(!_printName)
				appenders.get(LOG_LEVEL.debug).println(DEBUG,s);
			else if(!_printLevel)
				appenders.get(LOG_LEVEL.debug).println(name,s);
			else
				appenders.get(LOG_LEVEL.debug).println(DEBUG, name,s);
		} else
			appenders.get(LOG_LEVEL.debug).println(s);
	}
	
	public void info(String s) {
		if(mute || level > LEVEL_INFO)
			return;
		if(_printName || _printLevel) {
			if(!_printName)
				appenders.get(LOG_LEVEL.info).println(INFO,s);
			else if(!_printLevel)
				appenders.get(LOG_LEVEL.info).println(name,s);
			else
				appenders.get(LOG_LEVEL.info).println(INFO, name,s);
		} else
			appenders.get(LOG_LEVEL.info).println(s);
	}
	
	public void error(String s) {
		if(mute || level > LEVEL_ERROR)
			return;
		if(_printName || _printLevel) {
			if(!_printName)
				appenders.get(LOG_LEVEL.error).println(ERROR,s);
			else if(!_printLevel)
				appenders.get(LOG_LEVEL.error).println(name,s);
			else
				appenders.get(LOG_LEVEL.error).println(ERROR, name,s);
		} else
			appenders.get(LOG_LEVEL.error).println(s);
	}
	
	public void setAppender(LOG_LEVEL level, Appender appender) {
		appenders.put(level, appender);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void appendName(String suffix) {
		this.name += " - " + suffix;
	}
	
	public Log setLogLevel(LOG_LEVEL logLevel) {
		switch(logLevel) {
		case error:
			level = LEVEL_ERROR;
			break;
		case info:
			level = LEVEL_INFO;
			break;
		case debug:
			level = LEVEL_DEBUG;	
		}
		return this;
	}
	
	public Appender getAppender(LOG_LEVEL level) {
		return appenders.get(level);
	}
	
	
	//static Functions
	public static void setDefaultAppender(LOG_LEVEL level, Appender appender) {
		_globalAppenders.put(level, appender);
	}
	
	public static void printNames(boolean print) {
		_printName = print;
	}
	
	public static void printLogLevel(boolean print) {
		_printLevel = print;
	}
	
	public static void setGlobalLogLevel(LOG_LEVEL logLevel) {
		switch(logLevel) {
		case error:
			_logLevel = LEVEL_ERROR;
			break;
		case info:
			_logLevel = LEVEL_INFO;
			break;
		case debug:
			_logLevel = LEVEL_DEBUG;	
		}
	}
	
}
