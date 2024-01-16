package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class ClassModel {

	private String name;
	private ClassModel superClass;
	private List<InterfaceModel> interfaces;
	private List<FieldModel> fields;
	private List<ConstructorModel> constructors;
	private List<MethodModel> methods;
	private String simpleName;
	private String type;

	public ClassModel(String name) {
		this.name = name;
		this.superClass = null;
		this.interfaces = new Vector<InterfaceModel>();
		this.fields = new Vector<FieldModel>();
		this.constructors = new Vector<ConstructorModel>();
		this.methods = new Vector<MethodModel>();
		this.type = "class";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public ClassModel getSuperClass() {
		return superClass;
	}

	public void setSuperClass(ClassModel superClass) {
		this.superClass = superClass;
	}

	public List<InterfaceModel> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<InterfaceModel> interfaces) {
		this.interfaces = interfaces;
	}

	public List<FieldModel> getFields() {
		return fields;
	}

	public void setFields(List<FieldModel> fields) {
		this.fields = fields;
	}

	public List<ConstructorModel> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<ConstructorModel> constructors) {
		this.constructors = constructors;
	}

	public List<MethodModel> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodModel> methods) {
		this.methods = methods;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
