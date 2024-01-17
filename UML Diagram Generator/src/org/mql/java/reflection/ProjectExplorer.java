package org.mql.java.reflection;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.ClassModel;
import org.mql.java.models.EnumerationModel;
import org.mql.java.models.InterfaceModel;
import org.mql.java.models.PackageModel;
import org.mql.java.models.ProjectModel;
import static org.mql.java.helpers.ExplorerHelper.*;
public class ProjectExplorer {
	
	private ProjectModel project;
	
	public ProjectExplorer(File path) {
		this.project = new ProjectModel(path);
	}
	
	public  ProjectModel parse() {
		 if(project!=null) {
				loadPackages();
				loadClasses();
				return project;
		 }      return null;
	}
	
	private void loadPackages() {
		List<String> packNames = getListFilledPackages(project);
		List<PackageModel> packages = new Vector<>();
		for (String name : packNames) {
			packages.add(PackageExplorer.parse(name,project.getAbsolutePath()));
		}
		project.setPackages(packages);
	}
	
	private void loadClasses() {
	    for (PackageModel pack : project.getPackages()) {
	        List<ClassModel> classes = new Vector<>();
	        List<InterfaceModel> interfaces = new Vector<>();
	        List<EnumerationModel> enumerations = new Vector<>();
	        for (ClassModel c : pack.getClasses()) {
	            String classPath = pack.getAbsolutePath()+"\\"+c.getName().replaceAll("\\\\class$", ".class");                
	            ModelParser parser = new ModelParser(new File(classPath));
	            Object returned = parser.parse();
	            if (returned instanceof ClassModel)
	                classes.add((ClassModel) returned);
	            else if (returned instanceof InterfaceModel)
	                interfaces.add((InterfaceModel) returned);
	            else if (returned instanceof EnumerationModel)
	                enumerations.add((EnumerationModel) returned);
	        }

	        pack.setClasses(classes);
	        pack.setInterfaces(interfaces);
	        pack.setEnumerations(enumerations);
	    }
	}
}
	
