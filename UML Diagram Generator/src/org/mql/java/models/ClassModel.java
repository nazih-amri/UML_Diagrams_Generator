package org.mql.java.models;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.mql.java.enumerations.BaseModelType;

public class ClassModel implements BaseModel {

	private String name;
	private ClassModel superClass;
	private List<InterfaceModel> interfaces;
	private List<FieldModel> fields;
	private List<ConstructorModel> constructors;
	private List<MethodModel> methods;
	private List<RelationModel> relations;
	private String simpleName;

	public ClassModel(String name) {
		this.name = name;
		this.superClass = null;
		this.interfaces = new Vector<InterfaceModel>();
		this.fields = new Vector<FieldModel>();
		this.constructors = new Vector<ConstructorModel>();
		this.methods = new Vector<MethodModel>();
		this.relations = new Vector<RelationModel>();
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

	public BaseModelType getModelType() {
		return BaseModelType.CLASS;
	}

	public List<RelationModel> getRelations() {
		return relations;
	}

	public void setRelations(List<RelationModel> relations) {
		this.relations = relations;
	}

	public boolean add(RelationModel e) {
		return relations.add(e);
	}

	public boolean addAll(Collection<? extends RelationModel> c) {
		return relations.addAll(c);
	}

}
