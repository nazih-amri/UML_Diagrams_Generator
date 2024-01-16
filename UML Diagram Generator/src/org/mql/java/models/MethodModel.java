package org.mql.java.models;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class MethodModel {
	
	private String name;
	private String modifier;
	private String returnType;
	private List<Parameter> parameters;

	public MethodModel(String name) {
		this.name=name;
		parameters = new Vector<java.lang.reflect.Parameter>();
	}
	
	public MethodModel(Method method) {
		this(method.getName());
		this.modifier=Modifier.toString(method.getModifiers());
		this.returnType = method.getReturnType().getName();
		this.parameters= Arrays.asList(method.getParameters());
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

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<java.lang.reflect.Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<java.lang.reflect.Parameter> parameters) {
		this.parameters = parameters;
	}
	
	

}
