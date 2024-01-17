package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class EnumerationModel {
	
	private String name;
	private String simpleName;
	private String type;
	List<ConstantModel> constants;

	public EnumerationModel(String name) {
		this.name=name;
		this.type="enumeration";
		this.constants = new Vector<>();
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public List<ConstantModel> getConstants() {
		return constants;
	}
	
	public void setConstants(List<ConstantModel> constants) {
		this.constants = constants;
	}

}
