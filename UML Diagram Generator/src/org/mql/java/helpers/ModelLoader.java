package org.mql.java.helpers;

import static org.mql.java.helpers.ExplorerHelper.getClassPackageName;
import static org.mql.java.helpers.ExplorerHelper.getRootFromPath;
import static org.mql.java.helpers.ParserHelper.parseConstructors;
import static org.mql.java.helpers.ParserHelper.parseFields;
import static org.mql.java.helpers.ParserHelper.parseInterfaces;
import static org.mql.java.helpers.ParserHelper.parseMethods;
import static org.mql.java.helpers.ParserHelper.parseSupCls;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.mql.java.models.AnnotationModel;
import org.mql.java.models.ClassModel;
import org.mql.java.models.ConstantModel;
import org.mql.java.models.EnumerationModel;
import org.mql.java.models.InterfaceModel;
import org.mql.java.models.MethodModel;

public class ModelLoader {

	public ModelLoader() {
	}

	@SuppressWarnings("resource")
	public static Class<?> loadModel(File path) {
		try {
			URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { getRootFromPath(path).toURI().toURL() });
			return urlClassLoader.loadClass(getClassPackageName(path.getAbsolutePath()));
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public static ClassModel classParser(Class<?> model) {
		ClassModel cls = new ClassModel(model.getName());
		cls.setSimpleName(model.getSimpleName());
		cls.setFields(parseFields(model));
		cls.setConstructors(parseConstructors(model));
		cls.setMethods(parseMethods(model));
		cls.setInterfaces(parseInterfaces(model));
		cls.setSuperClass(parseSupCls(model));
		return cls;
	}

	public static AnnotationModel annotationParser(Class<?> model) {
		AnnotationModel annotation = new AnnotationModel(model.getName());
		annotation.setSimpleName(model.getSimpleName());
		annotation.setMethods(
				Arrays.stream(model.getDeclaredMethods()).map(MethodModel::new).collect(Collectors.toList()));
//		annotation.setType("annotation");
		return annotation;
	}

	public static InterfaceModel interfaceParser(Class<?> model) {
		InterfaceModel myinterface = new InterfaceModel(model.getName());
		myinterface.setSimpleName(model.getSimpleName());
		myinterface.setFields(parseFields(model));
		myinterface.setImplemetingInterfaces(parseInterfaces(model));
		myinterface.setMethods(parseMethods(model));
		return myinterface;
	}

	public static EnumerationModel enumerationParser(Class<?> model) {
		EnumerationModel eModel = new EnumerationModel(model.getName());
		eModel.setSimpleName(model.getSimpleName());
		List<ConstantModel> constants = new Vector<>();
		for (Object cons : model.getEnumConstants()) {
			constants.add(new ConstantModel(cons));
		}
		eModel.setConstants(constants);
		return eModel;
	}

}
