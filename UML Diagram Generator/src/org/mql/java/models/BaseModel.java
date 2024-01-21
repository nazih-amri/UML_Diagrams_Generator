package org.mql.java.models;

import org.mql.java.enumerations.BaseModelType;

public interface BaseModel {

	public BaseModelType getModelType();
	public String getName();
}
