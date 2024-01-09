package org.mql.java.reflection;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.Package;
import org.mql.java.models.Project;
import static org.mql.java.helpers.ExplorerHelper.*;
public class ProjectExplorer {
	
	
	public static Project parseAllPackages(File path) {
		Project project = new Project(path);
		List<Package> packages = new Vector<>();
		for (String pack : getListAllPackages(path)) {
			Package p = new Package(pack);
			packages.add(p);
		}
		project.setPackages(packages);
		return project;
	}
	
	public static Project parseFilledPackages(File path) {
		Project project = new Project(path);
		List<Package> packages = new Vector<Package>();
		for (String n : getListFilledPackages(path)) {
			Package p = new Package(n);
			packages.add(p);
		}
		project.setPackages(packages);
		return project;
	}


}
	
