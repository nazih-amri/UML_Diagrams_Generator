package org.mql.java.examples.models;

public class Child extends Parent {
	private String name;

	public Child(String parentName,String name) {
		super(parentName);
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
