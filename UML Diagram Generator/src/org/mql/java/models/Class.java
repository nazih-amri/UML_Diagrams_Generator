package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Class {

	private String name;
	private Class superClass;
	private List<Interface> interfaces;
	private List<Field> fields;
	private List<Constructor> constructors;
	private List<Method> methods;

	public Class(String name) {
		this.name = name;
		this.superClass=null;
		this.interfaces= new Vector<Interface>();
		this.fields= new Vector<Field>();
		this.constructors= new Vector<Constructor>();
		this.methods= new Vector<Method>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Class getSuperClass() {
		return superClass;
	}



	public void setSuperClass(Class superClass) {
		this.superClass = superClass;
	}



	public List<Interface> getInterfaces() {
		return interfaces;
	}



	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}



	public List<Field> getFields() {
		return fields;
	}



	public void setFields(List<Field> fields) {
		this.fields = fields;
	}



	public List<Constructor> getConstructors() {
		return constructors;
	}



	public void setConstructors(List<Constructor> constructors) {
		this.constructors = constructors;
	}



	public List<Method> getMethods() {
		return methods;
	}



	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	
	
}
