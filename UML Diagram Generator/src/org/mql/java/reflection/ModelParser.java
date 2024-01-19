package org.mql.java.reflection;

import java.io.File;

import static org.mql.java.helpers.ModelLoader.*;

public class ModelParser {

	private Class<?> model;
	public ModelParser(File path) {
		this.model = loadModel(path);
	}

	
	public Object parse() {
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
