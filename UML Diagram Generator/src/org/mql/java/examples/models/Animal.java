package org.mql.java.examples.models;

public class Animal {
	
	private String type;
	private String name;
	private String voice;

	public Animal() {
	}

	public Animal(String type, String name, String voice) {
		super();
		this.type = type;
		this.name = name;
		this.voice = voice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}
	
	

}
