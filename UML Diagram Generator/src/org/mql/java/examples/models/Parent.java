package org.mql.java.examples.models;

public class Parent {
	
	private String name;
	
	public Parent() {}

	public Parent(String name, int age) {
		this.name=name;
	}
	
	public class inner{
		public void hell() {}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}