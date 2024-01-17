package org.mql.java.models;

public class ConstantModel {

	private final Object constant;
	public ConstantModel(Object constant) {
		this.constant=constant;
	}
	
	public Object getConstant() {
		return ""+constant;
	}
	

}
