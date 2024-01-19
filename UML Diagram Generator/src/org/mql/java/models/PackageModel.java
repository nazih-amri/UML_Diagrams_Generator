package org.mql.java.models;

import static org.mql.java.enumerations.BaseModelType.ANNOTATION;

import java.util.List;
import java.util.Vector;

import org.mql.java.enumerations.BaseModelType;

public class PackageModel implements BaseModel {

	private String name;
	private List<ClassModel> classes;
	private List<InterfaceModel> interfaces;
	private List<EnumerationModel> enumerations;
	private List<AnnotationModel> annotations;
	private String absolutePath;

	public PackageModel(String name,String projectPath) {
		this.name = name;
		classes = new Vector<ClassModel>();
		this.absolutePath= projectPath+"\\"+ name.replace('.', '\\');
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ClassModel getClassByName(String name) {
		for (ClassModel classModel : classes) {
			if(name.equals(classModel.getName()))
				return classModel;
		}
		return null;
	}

	public List<ClassModel> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassModel> classes) {
		this.classes = classes;
	}
	
	public String getAbsolutePath() {
		return absolutePath;
	}
	
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	
	public List<InterfaceModel> getInterfaces() {
		return interfaces;
	}
	
	public void setInterfaces(List<InterfaceModel> interfaces) {
		this.interfaces = interfaces;
	}
	
	public List<EnumerationModel> getEnumerations() {
		return enumerations;
	}
	
	public void setEnumerations(List<EnumerationModel> enumerations) {
		this.enumerations = enumerations;
	}
	
	public List<AnnotationModel> getAnnotations() {
		return annotations;
	}
	
	public void setAnnotations(List<AnnotationModel> annotations) {
		this.annotations = annotations;
	}
	
	public BaseModelType getModelType() {
		return BaseModelType.PACKAGE;
	}

	@Override
	public String toString() {
		return "Package [name=" + name + ", classes=" + classes + "]";
	}
	

}
