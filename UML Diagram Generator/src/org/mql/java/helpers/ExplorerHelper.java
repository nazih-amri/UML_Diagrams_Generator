package org.mql.java.helpers;

import static org.mql.java.enumerations.RelationType.AGGREGATION;
import static org.mql.java.enumerations.RelationType.ASSOCIATION;
import static org.mql.java.enumerations.RelationType.COMPOSITION;

import java.io.File;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.ClassModel;
import org.mql.java.models.ConstructorModel;
import org.mql.java.models.FieldModel;
import org.mql.java.models.ProjectModel;
import org.mql.java.models.RelationModel;

public class ExplorerHelper {

	public ExplorerHelper() {
	}
	
	
	public static List<String> getListFilledPackages(ProjectModel project) {
		File file = new File(project.getAbsolutePath());
		List<String> packageNames = new Vector<>();
		scanFilledPackages(file, "", packageNames);
		return packageNames;
	}
	
	
	private static void scanFilledPackages(File dir, String currentPackage, List<String> packageNames) {
		if (dir.listFiles() != null) {
			for (File file : dir.listFiles()) {
				if (file.isDirectory()) {
					String packageName = currentPackage.isEmpty() ? file.getName()
							: currentPackage + "." + file.getName();
					if (isContainsClasses(file)) {
						packageNames.add(packageName);
					}
					scanFilledPackages(file, packageName, packageNames);
				}
			}
		}
	}
	
	private static boolean isContainsClasses(File file) {
		List<File> files = new Vector<File>();
		for (File f : file.listFiles()) {
			if (f.getName().endsWith(".class")) {
				files.add(f);
			}
		}
		return files != null && files.size() > 0;
	}

	public static String[] scanPackage(File packageFolder) {
		List<String> list = new Vector<String>();
		File classes[] = packageFolder.listFiles();
		for (File f : classes) {
			if(f.getAbsolutePath().endsWith(".class"))
			list.add(f.getName());
		}
		return list.toArray(new String[list.size()]);
	}
	
	public static String getClassPackageName(String absolutePath) {
		return absolutePath.substring(absolutePath.lastIndexOf("\\bin\\") + 5)
				                 .replace(".class", "").replace(File.separator, ".");
	}
	
	public static String getAbsoluteClassPath(String path) {
		String res = path.replace(".", "\\");
		return res;
	}
	
	public static File getRootFromPath(File file) {
		return new File(file.getAbsolutePath().substring(0,
				file.getAbsolutePath().lastIndexOf("\\bin\\") + 4)); 

	}
	
	public static boolean isWrapper(Class<?> type) {
		return  type == Boolean.class ||
				type == Integer.class ||
				type == Double.class ||
				type == Float.class ||
				type == Character.class ||
				type == Long.class ||
				type == Short.class ||
				type == Byte.class ||
				type == String.class;				
	}
	
    public static boolean isCollectionOfType(FieldModel field, ClassModel target) {
        if (Collection.class.isAssignableFrom(field.getType()) && field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) field.getGenericType();
            Type[] current = pt.getActualTypeArguments();
            if (current.length > 0 && current[0] instanceof Class) {
                Class<?> genericType = (Class<?>) current[0];
                return genericType.getName().equals(target.getName());
            }
        }
        return false;
    }
    
    public static RelationModel checkConstructors(ClassModel source, ClassModel target,FieldModel field) {
        for (ConstructorModel constructor : source.getConstructors()) {
            if (constructor.getParameters().size() > 0) {
                for (Parameter p : constructor.getParameters()) {
                    if (p.getType().equals(field.getType())) {
                        return new RelationModel(AGGREGATION, source.getName(), target.getName());
                    }
                }
                return new RelationModel(COMPOSITION, source.getName(), target.getName());
            }
            return new RelationModel(ASSOCIATION, source.getName(), target.getName());
        }
		return null;
    }
	
	

}
