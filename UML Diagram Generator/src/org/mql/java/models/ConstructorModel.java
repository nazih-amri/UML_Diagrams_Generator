package org.mql.java.models;

import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class ConstructorModel {
	
	private String name;
	private String modifier;
	private List<Parameter> parameters;

	public ConstructorModel(String name) {
		this.name=name;
		this.parameters= new Vector<java.lang.reflect.Parameter>();
	}

	public ConstructorModel(java.lang.reflect.Constructor<?> constructor) {
		this(constructor.getName());
		this.modifier= Modifier.toString(constructor.getModifiers());
		parameters = Arrays.asList(constructor.getParameters());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public List<java.lang.reflect.Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<java.lang.reflect.Parameter> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "Constructor [name=" + name + ", modifier=" + modifier + ", parameters=" + parameters + "]";
	}
	
	
	
	

}
