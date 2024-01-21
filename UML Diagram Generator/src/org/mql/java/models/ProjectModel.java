package org.mql.java.models;

import java.io.File;
import java.util.List;

import org.mql.java.enumerations.BaseModelType;

public class ProjectModel implements BaseModel {

	private String name;
	private List<PackageModel> packages;
	private String absolutePath;
	private List<RelationModel> relations;

	public ProjectModel(File file) {
		if (file.exists()) {
			if (!file.getAbsolutePath().endsWith("bin")) {
				this.absolutePath = file.getAbsolutePath() + "\\bin";
			} else {
				this.absolutePath = file.getAbsolutePath();
			}
			setName();
		} else {
			System.out.println("wrong path!");
		}

	}

	public String getName() {
		return name;
	}

	public void setName() {
		this.name = absolutePath.replace("\\bin", "")
				.substring(absolutePath.replace("\\bin", "").lastIndexOf("\\") + 1);
	}

	public List<PackageModel> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageModel> packages) {
		this.packages = packages;
	}
	
	public PackageModel getPackage(String name) {
		for (PackageModel packageModel : packages) {
			if(name.equals(packageModel.getName())) {
				return packageModel;
			}
		}
		return null; // in case if no package found
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public List<RelationModel> getRelations() {
		return relations;
	}

	public void setRelations(List<RelationModel> relations) {
		this.relations = relations;
	}
	
	public BaseModelType getModelType() {
		return BaseModelType.PROJECT;
	}

}
