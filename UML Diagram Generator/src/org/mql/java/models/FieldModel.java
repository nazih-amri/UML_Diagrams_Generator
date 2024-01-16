package org.mql.java.models;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class FieldModel {

	private String name;
	private String modifier;
	private Type type;
	private boolean isPrimitive;
	private boolean isMemberClass;

	public FieldModel(String name) {
		this.name = name;

	}

	public FieldModel(java.lang.reflect.Field field) {
		this.name = field.getName();
		this.modifier = Modifier.toString(field.getModifiers());
		this.type = field.getType();
		this.isPrimitive = field.getType().isPrimitive();
		this.isMemberClass = field.getType().isMemberClass();

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isPrimitive() {
		return isPrimitive;
	}

	public boolean isMemberClass() {
		return isMemberClass;
	}

}
