package org.mql.java.reflection;

import static org.mql.java.helpers.ExplorerHelper.scanPackage;

import java.io.File;
import java.util.List;
import java.util.Vector;
import org.mql.java.models.ClassModel;
import org.mql.java.models.PackageModel;

public class PackageExplorer {

	public PackageExplorer() {
	}
	
	public static PackageModel parse(String packageName, String projectPath) {
		PackageModel pack = new PackageModel(packageName,projectPath);
		List<ClassModel> cls = new Vector<>();
		for (String name : scanPackage(new File(pack.getAbsolutePath()))) {
			ClassModel c = new ClassModel(name);
			cls.add(c);
		}
		pack.setClasses(cls);
		return pack;
	}
	
	

}
