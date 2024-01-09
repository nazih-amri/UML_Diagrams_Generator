package org.mql.java;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.Class;
import org.mql.java.models.Package;
import org.mql.java.models.Project;
import static org.mql.java.reflection.ProjectExplorer.*;
import static org.mql.java.reflection.PackageExplorer.*;

public class Examples {

	public Examples() {
		exp2();
	}
	
	void exp1() {
		File file = new File("C:\\Users\\na-zi\\git\\repository3\\UML Diagram Generator\\bin");
			Project project = parseAllPackages(file);
			for (Package pack : project.getPackages()) {
				System.out.println(pack.getName());
			}
		}
	
	void exp2() {
		File file = new File("C:\\Users\\na-zi\\git\\repository3\\UML Diagram Generator\\bin");
		Project project = parseFilledPackages(file);
		List<Package> packs = new Vector<Package>();
		for (Package pack : project.getPackages()) {
			packs.add(pack);
		}
		
		for (Package p : packs) {
			Package pa= parse(file, p.getName());
			System.out.println("package name : "+pa.getName());
			if(pa.getClasses()!=null) {
				System.out.println("classes : ");
				for (Class c : pa.getClasses()) {
					System.out.println(c.getName());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Examples();
	}

}
