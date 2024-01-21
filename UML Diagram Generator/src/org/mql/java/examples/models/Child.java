package org.mql.java.examples.models;

import java.util.List;

public class Child extends Parent implements Person {
	private String name;
	private List<Parent> parents;
	public Child() {
	}

	public Child(String parentName,String name) {
		super();
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int age() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
