package org.mql.java.models;


import org.mql.java.enumerations.BaseModelType;
import org.mql.java.enumerations.RelationType;

public class RelationModel implements BaseModel {

	private RelationType name;
	private String source;
	private String target;
	private FieldModel relationField;


	public RelationModel(RelationType name, String source, String target) {
		this.name = name;
		this.target = target;
		this.source = source;
		this.relationField = null;
	}

	public RelationModel(RelationType name, String source, String target, FieldModel relationField) {
		this.name = name;
		this.target = target;
		this.source = source;
		this.relationField = relationField;
	}

	public RelationType getName() {
		return name;
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

	public FieldModel getRelationField() {
		return relationField;
	}

	public void setRelationField(FieldModel relationField) {
		this.relationField = relationField;
	}
	
	public BaseModelType getModelType() {
		return BaseModelType.RELATION;
	}

	@Override
	public String toString() {
		return "RelationModel [name=" + name + ", source=" + source + ", target=" + target + ", relationField="
				+ relationField + "]" ;
	}
	




}
