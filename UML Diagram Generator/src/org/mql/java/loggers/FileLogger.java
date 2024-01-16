package org.mql.java.loggers;

import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;


public class FileLogger implements Logger {
	private String source;
	private List<String> messages;

	public FileLogger(String source) {
		this.source=source;
		messages = new Vector<>();
	}
	
	@Override
	public void log(String level, String msg) {
		messages.add("### "+ level+" : "+msg);
		try {
			PrintWriter out = new PrintWriter(source);
			for (String message : messages) {
				out.println(message);
			}
			out.close();
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
			}
		
	}

}

