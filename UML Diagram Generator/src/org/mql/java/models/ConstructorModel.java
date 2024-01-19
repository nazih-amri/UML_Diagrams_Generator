package org.mql.java.models;

import static org.mql.java.enumerations.BaseModelType.ANNOTATION;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.enumerations.BaseModelType;

public class ConstructorModel implements BaseModel {
	
	private String name;
	private String modifier;
	private List<Parameter> parameters;
	private Class<?>[] parameterTypes;

	public ConstructorModel(String name) {
		this.name=name;
		this.parameters= new Vector<Parameter>();
	}

	public ConstructorModel(Constructor<?> constructor) {
		this(constructor.getName());
		this.modifier= Modifier.toString(constructor.getModifiers());
		parameters = Arrays.asList(constructor.getParameters());
		this.parameterTypes=constructor.getParameterTypes();
		
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

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}
	
	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}
	


	@Override
	public String toString() {
		return "Constructor [name=" + name + ", modifier=" + modifier + ", parameters=" + parameters + "]";
	}

	public BaseModelType getModelType() {
		return BaseModelType.CONSTRUCTOR;
	}
	
	
	
	

}
