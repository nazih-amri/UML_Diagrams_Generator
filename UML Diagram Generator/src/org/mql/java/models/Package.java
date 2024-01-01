package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Package {

	private String name;
	private List<Class> classes;

	public Package(String name) {
		this.name = name;
		classes = new Vector<Class>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "Package [name=" + name + ", classes=" + classes + "]";
	}

}
