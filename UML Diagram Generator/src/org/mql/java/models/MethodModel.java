package org.mql.java.models;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.enumerations.BaseModelType;

public class MethodModel implements BaseModel {
	
	private String name;
	private String modifier;
	private String returnType;
	private List<Parameter> parameters;

	public MethodModel(String name) {
		this.name=name;
		parameters = new Vector<Parameter>();
	}
	
	public MethodModel(Method method) {
		this(method.getName());
		setModifier(method.getModifiers());
		this.returnType = method.getReturnType().getSimpleName();
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

	public void setModifier(int modifier) {
		this.modifier = Modifier.toString(modifier);
		if("".equals(this.modifier))
			this.modifier="public";
	}
	
	public void setModifier(String modifier) {
		this.modifier = modifier;
		if("".equals(this.modifier))
			this.modifier="public";
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public BaseModelType getModelType() {
		return BaseModelType.METHOD;
	}

}
