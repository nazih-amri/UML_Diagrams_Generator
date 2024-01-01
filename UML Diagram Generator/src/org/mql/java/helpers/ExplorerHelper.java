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

}
