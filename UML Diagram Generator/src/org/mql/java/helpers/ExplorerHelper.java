package org.mql.java.helpers;

import java.io.File;
import java.util.List;
import java.util.Vector;

public class ExplorerHelper {

	public ExplorerHelper() {
	}
	
	public static List<String> getListAllPackages(File dir) {
		List<String> packageNames = new Vector<>();
		scanAllPackages(dir, "", packageNames);
		return packageNames;
	}
	
	public static List<String> getListFilledPackages(File dir) {
		List<String> packageNames = new Vector<>();
		scanFilledPackages(dir, "", packageNames);
		return packageNames;
	}
	
	private static void scanAllPackages(File dir, String currentP, List<String> packageNames) {
		if (dir.listFiles() != null) {
			for (File file : dir.listFiles()) {
				if (file.isDirectory()) {
					String packageName = currentP.isEmpty() ? file.getName()
							: currentP + "." + file.getName();
					packageNames.add(packageName);
					scanAllPackages(file, packageName, packageNames);
				}
			}
		}
	}
	
	private static void scanFilledPackages(File dir, String currentPackage, List<String> packageNames) {
		if (dir.listFiles() != null) {
			for (File file : dir.listFiles()) {
				if (file.isDirectory()) {
					String packageName = currentPackage.isEmpty() ? file.getName()
							: currentPackage + "." + file.getName();
					if (isConatainsClasses(file)) {
						packageNames.add(packageName);
					}
					scanFilledPackages(file, packageName, packageNames);
				}
			}
		}
	}
	
	private static boolean isConatainsClasses(File file) {
		List<File> files = new Vector<File>();
		for (File f : file.listFiles()) {
			if (f.getName().endsWith(".class")) {
				files.add(f);
			}
		}
		return files != null && files.size() > 0;
	}

	public static String[] scanPackage(File projectPath, String packageName) {
		String packageFolder = projectPath+"\\"+ packageName.replace('.', '\\');
		List<String> list = new Vector<String>();
		File dir = new File(packageFolder);
		File classes[] = dir.listFiles();
		for (File f : classes) {
			if(f.getAbsolutePath().endsWith(".class"))
			list.add(f.getName());
		}
		return list.toArray(new String[list.size()]);
	}

}
