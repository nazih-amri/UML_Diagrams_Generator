package org.mql.java.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.enumerations.BaseModelType;

public class InterfaceModel implements BaseModel {

	private String name;
	private List<InterfaceModel> implemetingInterfaces;
	private List<FieldModel> fields;
	private List<MethodModel> methods;
	private String simpleName;

	public InterfaceModel(String name) {
		this.name = name;
		this.implemetingInterfaces = new Vector<InterfaceModel>();
		this.fields = new Vector<FieldModel>();
		this.methods = new Vector<MethodModel>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<InterfaceModel> getImplemetingInterfaces() {
		return implemetingInterfaces;
	}

	public void setImplemetingInterfaces(List<InterfaceModel> implemetingInterfaces) {
		this.implemetingInterfaces = implemetingInterfaces;
	}

	public List<FieldModel> getFields() {
		return fields;
	}

	public void setFields(List<FieldModel> fields) {
		this.fields = fields;
	}

	public List<MethodModel> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodModel> methods) {
		this.methods = methods;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getSimpleName() {
		return simpleName;
	}

	@Override
	public String toString() {
		return "Interface [name=" + name + ", implemetingInterfaces=" + implemetingInterfaces + ", fields=" + fields
				+ ", methods=" + methods + ", simpleName=" + simpleName + "]";
	}

	public BaseModelType getModelType() {
		return BaseModelType.INTERFACE;
	}

}
