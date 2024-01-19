package org.mql.java.models;

import static org.mql.java.enumerations.BaseModelType.ANNOTATION;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import org.mql.java.enumerations.BaseModelType;

public class FieldModel implements BaseModel {

	private String name;
	private String modifier;
	private Class<?> type;
	private boolean isPrimitive;
	private boolean isMemberClass;
	private Type genericType;

	public FieldModel(String name) {
		this.name = name;

	}

	public FieldModel(Field field) {
		this.name = field.getName();
		this.modifier = Modifier.toString(field.getModifiers());
		this.type = field.getType();
		this.isPrimitive = field.getType().isPrimitive();
		this.isMemberClass = field.getType().isMemberClass();
		this.genericType = field.getGenericType();
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

	public Class<?> getType() {
		return type;
	}
	
	@Override
	public BaseModelType getModelType() {
		return BaseModelType.FIELD;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public boolean isPrimitive() {
		return isPrimitive;
	}

	public boolean isMemberClass() {
		return isMemberClass;
	}
	
	public Type getGenericType() {
		return genericType;
	}
	
	public void setGenericType(Type genericType) {
		this.genericType = genericType;
	}


}
