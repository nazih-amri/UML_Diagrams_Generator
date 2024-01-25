package org.mql.java.reflection;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.loggers.ConsoleLogger;
import org.mql.java.loggers.FileLogger;
import org.mql.java.loggers.Logger;
import org.mql.java.models.AnnotationModel;
import org.mql.java.models.BaseModel;
import org.mql.java.models.ClassModel;
import org.mql.java.models.EnumerationModel;
import org.mql.java.models.InterfaceModel;
import org.mql.java.models.PackageModel;
import org.mql.java.models.ProjectModel;
import static org.mql.java.helpers.ExplorerHelper.*;
import static org.mql.java.enumerations.BaseModelType.*;

public class ProjectExplorer {

	private ProjectModel project;
	private Logger logger;
	List<ClassModel> projectClasses;

	public ProjectExplorer(File path) {
		this.project = new ProjectModel(path);
		this.projectClasses = new Vector<>();
	}

	public ProjectModel parse() {
		if (project != null) {
			loadPackages();
			loadModels();
			loadRelations();
			print(new ConsoleLogger());
			print(new FileLogger("resources/logs/projectSummary.txt"));
			return project;
		}
		return null;
	}

	private void loadPackages() {
		List<String> packNames = getListFilledPackages(project);
		List<PackageModel> packages = new Vector<>();
		for (String name : packNames) {
			packages.add(PackageExplorer.parse(name, project.getAbsolutePath()));
		}
		project.setPackages(packages);
	}

	private void loadModels() {
		setLogger(new FileLogger("resources/logs/explorer.log"));
		log("Project", project.getName());
		for (PackageModel pack : project.getPackages()) {
			log("package", pack.getName());
			List<BaseModel> models= new Vector<BaseModel>();
			for (ClassModel c : pack.getClasses()) {
				String classPath = pack.getAbsolutePath() + "\\" + c.getName().replaceAll("\\\\class$", ".class");
				BaseModel returned = ModelParser.parse(new File(classPath));
				models.add(returned);
			}
			models.forEach(model-> log(model.getModelType().name(), model.getName()));
			pack.setClasses(Arrays.asList(models.stream().filter(item->CLASS.equals(item.getModelType())).toArray(ClassModel[]::new)));
			pack.setInterfaces(Arrays.asList(models.stream().filter(item->INTERFACE.equals(item.getModelType())).toArray(InterfaceModel[]::new)));
			pack.setEnumerations(Arrays.asList(models.stream().filter(item->ENUMERATION.equals(item.getModelType())).toArray(EnumerationModel[]::new)));
			pack.setAnnotations(Arrays.asList(models.stream().filter(item->ANNOTATION.equals(item.getModelType())).toArray(AnnotationModel[]::new)));
		}
	}

	private void loadRelations() {
		for (PackageModel pack : project.getPackages()) {
			for (ClassModel c : pack.getClasses()) {
				projectClasses.add(c);
			}
		}
		project.setRelations(RelationFinder.find(projectClasses));
	}
	
	private void print(Logger logger) {
	    setLogger(logger);
	    log("Project", project.getName());
	    for (PackageModel pack : project.getPackages()) {
	        log("Package", pack.getName());
	        pack.getClasses().forEach(cls -> printClsInfo(cls, logger));
	        pack.getInterfaces().forEach(in -> printIntInfo(in, logger));
	        pack.getAnnotations().forEach(an -> printAnntInfo(an, logger));
	        pack.getEnumerations().forEach(en -> printEnumtInfo(en, logger));
	    }
	    project.getRelations().forEach(relation ->
	        logger.log("Relation", relation.getSource() + " -- " + relation.getNameEnum() + " -- " + relation.getTarget())
	    );
	}
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	private void log(String level, String msg) {
		if (logger != null)
			logger.log(level, msg);
	}
}
