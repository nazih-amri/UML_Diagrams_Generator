package org.mql.java.models;

import java.io.File;
import java.util.List;

public class Project {
	
	private String name;
	private List<Package> packages;
	private String absolutePath;

	public Project(File file) {
		if(file.exists()) {
			this.absolutePath=file.getAbsolutePath();
			this.name = absolutePath.substring(absolutePath.lastIndexOf("\\"));
		}
		else {
			System.out.println("wrong path!");
		}

	}

	public String getName() {
		return name;
	}

	public void setName() {
		this.name = absolutePath.replace("\\bin", "").substring(absolutePath.replace("\\bin", "").lastIndexOf("\\") + 1);
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

}
