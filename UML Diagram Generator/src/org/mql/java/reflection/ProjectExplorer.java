package org.mql.java.reflection;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.loggers.FileLogger;
import org.mql.java.loggers.Logger;
import org.mql.java.models.AnnotationModel;
import org.mql.java.models.ClassModel;
import org.mql.java.models.EnumerationModel;
import org.mql.java.models.InterfaceModel;
import org.mql.java.models.PackageModel;
import org.mql.java.models.ProjectModel;
import static org.mql.java.helpers.ExplorerHelper.*;
public class ProjectExplorer {
	
	private ProjectModel project;
	private Logger logger;
	List<ClassModel> projectClasses;
	public ProjectExplorer(File path) {
		this.project = new ProjectModel(path);
		this.projectClasses= new Vector<>();
	}
	
	public  ProjectModel parse() {
		 if(project!=null) {
				loadPackages();
				loadModels();
				return project;
		 }      
		 return null;
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	private void log(String level,String msg) {
		if(logger!=null) logger.log(level,msg);
	}
	
	private void loadPackages() {
		List<String> packNames = getListFilledPackages(project);
		List<PackageModel> packages = new Vector<>();
		for (String name : packNames) {
			packages.add(PackageExplorer.parse(name,project.getAbsolutePath()));
		}
		project.setPackages(packages);
	}
	
	private void loadModels() {
		setLogger(new FileLogger("resources/logs/explorer.log"));
    	log("Project",project.getName());
    	
	    for (PackageModel pack : project.getPackages()) {
			log("package", pack.getName());
	        List<ClassModel> classes = new Vector<>();
	        List<InterfaceModel> interfaces = new Vector<>();
	        List<AnnotationModel> annotations = new Vector<>();
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
	            else if (returned instanceof AnnotationModel)
	                annotations.add((AnnotationModel) returned);
	        }
			classes.forEach(cls -> log("class", cls.getName()));
			interfaces.forEach(in -> log("interface", in.getName()));
			annotations.forEach(an -> log("annotation", an.getName()));
			enumerations.forEach(em -> log("enumeration", em.getName()));
			
	        pack.setClasses(classes);
	        pack.setInterfaces(interfaces);
	        pack.setEnumerations(enumerations);
	    }
	}

}
	
