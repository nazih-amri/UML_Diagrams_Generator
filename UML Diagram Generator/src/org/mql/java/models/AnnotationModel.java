package org.mql.java.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.enumerations.BaseModelType;
import static org.mql.java.enumerations.BaseModelType.*;

public class AnnotationModel implements BaseModel {

	private String name;
	private String simpleName;
	private List<MethodModel> methods;
	public AnnotationModel(String name) {
		this.name=name;
		this.methods= new Vector<MethodModel>();
		this.simpleName=null;
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
	public BaseModelType getModelType() {
		return ANNOTATION;
	}

	

}
