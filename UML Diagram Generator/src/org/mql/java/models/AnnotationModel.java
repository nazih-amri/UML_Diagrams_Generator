package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class AnnotationModel {

	private String name;
	private String simpleName;
	private List<MethodModel> methods;
	private String type;
	public AnnotationModel(String name) {
		this.name=name;
		this.methods= new Vector<MethodModel>();
		this.simpleName=null;
		this.type="annotation";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSimpleName() {
		return simpleName;
	}
	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	public List<MethodModel> getMethods() {
		return methods;
	}
	public void setMethods(List<MethodModel> methods) {
		this.methods = methods;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
	

}
