package org.mql.java.reflection;

import static org.mql.java.helpers.ExplorerHelper.scanPackage;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.Class;
import org.mql.java.models.Package;

public class PackageExplorer {

	public PackageExplorer() {
	}
	
	public static Package parse(File projectPath, String packageName) {
		Package pack = new Package(packageName);
		List<Class> cls = new Vector<>();
		for (String name : scanPackage(projectPath, packageName)) {
			Class c = new Class(name);
			cls.add(c);
		}
		pack.setClasses(cls);
		return pack;
	}

}
