package org.mql.java.reflection;

import java.io.File;

import org.mql.java.models.BaseModel;

import static org.mql.java.helpers.ModelLoader.*;

public class ModelParser {

	public ModelParser() {
	}

	
	public static BaseModel parse(File path) {
		Class<?> model = loadModel(path);
	    if (model != null) {
	        if (model.isAnnotation()) return annotationParser(model);
	        if (model.isInterface()) return interfaceParser(model);
	        if (model.isEnum()) return enumerationParser(model);
	        return classParser(model);
	    }
	    System.out.println("no class found!");
	    return null;
	}
}
