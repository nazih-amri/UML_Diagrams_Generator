package org.mql.java.models;


import java.util.List;
import java.util.Vector;

import org.mql.java.enumerations.BaseModelType;

public class EnumerationModel implements BaseModel {
	
	private String name;
	private String simpleName;
	List<ConstantModel> constants;

	public EnumerationModel(String name) {
		this.name=name;
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
	
	public BaseModelType getModelType() {
		return BaseModelType.ENUMERATION;
	}
	
	public List<ConstantModel> getConstants() {
		return constants;
	}
	
	public void setConstants(List<ConstantModel> constants) {
		this.constants = constants;
	}

}
