package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class PackageModel {

	private String name;
	private List<ClassModel> classes;
	private List<InterfaceModel> interfaces;
	private List<EnumerationModel> enumerations;
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

	@Override
	public String toString() {
		return "Package [name=" + name + ", classes=" + classes + "]";
	}
	

}
