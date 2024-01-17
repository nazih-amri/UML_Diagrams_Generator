package org.mql.java.helpers;

import java.io.File;
import java.util.List;
import java.util.Vector;
import org.mql.java.models.ProjectModel;

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
	
	public static File getRootFromPath(File file) {
		return new File(file.getAbsolutePath().substring(0,
				file.getAbsolutePath().lastIndexOf("\\bin\\") + 4)); 

	}
	

}
