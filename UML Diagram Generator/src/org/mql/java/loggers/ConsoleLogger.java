package org.mql.java.loggers;


public class ConsoleLogger implements Logger {

	public ConsoleLogger() {
	}
	 @Override
	public void log(String level, String msg) {
		 	System.out.println("### "+ level+" : "+msg);
	}

}
