package org.mql.java.models;


import org.mql.java.enumerations.BaseModelType;
import org.mql.java.enumerations.RelationType;

public class RelationModel implements BaseModel {

	private RelationType name;
	private String source;
	private String target;

	public RelationModel(RelationType name, String source, String target) {
		this.name = name;
		this.target = target;
		this.source = source;
	}

	public RelationType getNameEnum() {
		return name;
	}
	
	@Override
	public String getName() {
		return name.name().toLowerCase();
	}

	public void setName(RelationType name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public BaseModelType getModelType() {
		return BaseModelType.RELATION;
	}

}
