package org.mql.java.models;

public class ParameterModel {
	
	private String name;
	private String type;

	public ParameterModel(String name, String type) {
		this.name=name;
		this.type=type;
	}
	
	public ParameterModel() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Parameter [name=" + name + ", type=" + type + "]";
	}
	
	

}
