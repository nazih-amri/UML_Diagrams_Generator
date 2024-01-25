package org.mql.java.helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.stream.Collectors;

import org.mql.java.models.ClassModel;
import org.mql.java.models.ConstructorModel;
import org.mql.java.models.FieldModel;
import org.mql.java.models.InterfaceModel;
import org.mql.java.models.MethodModel;

public class ParserHelper {

	public ParserHelper() {
	}

	public static ClassModel parseSupCls(Class<?> model) {
		if (model.getSuperclass() != null) {
			org.mql.java.models.ClassModel sup = new org.mql.java.models.ClassModel(model.getSuperclass().getName());
			return sup;
		} else {
			return null;
		}
	}

	public static List<MethodModel> parseMethods(Class<?> model) {
		return Arrays.stream(model.getDeclaredMethods()).map(MethodModel::new).collect(Collectors.toList());
	}

	public static List<FieldModel> parseFields(Class<?> model) {
		List<FieldModel> fields = new Vector<>();
		for (Field field : model.getDeclaredFields()) {
			fields.add(new FieldModel(field));
		}
		return fields;
	}

	public static List<ConstructorModel> parseConstructors(Class<?> model) {
		List<ConstructorModel> constructors = new Vector<>();
		for (Constructor<?> cons : model.getDeclaredConstructors()) {
			constructors.add(new ConstructorModel(cons));
		}
		return constructors;
	}

	public static List<InterfaceModel> parseInterfaces(Class<?> model) {
		List<InterfaceModel> interfaces = new Vector<InterfaceModel>();
		for (Class<?> i : model.getInterfaces()) {
			InterfaceModel in = new InterfaceModel(i.getName());
			in.setSimpleName(i.getSimpleName());
			List<FieldModel> fields = new Vector<>();
			for (Field field : i.getDeclaredFields()) {
				fields.add(new FieldModel(field));
			}
			in.setFields(fields);
			List<MethodModel> methods = new Vector<MethodModel>();
			for (Method meth : i.getDeclaredMethods()) {
				methods.add(new MethodModel(meth));
			}
			in.setMethods(methods);
			interfaces.add(in);
		}
		return interfaces;
	}
	
    public static String generateXmiId() {
        return "_" + UUID.randomUUID().toString().substring(0,8);
    }

}
