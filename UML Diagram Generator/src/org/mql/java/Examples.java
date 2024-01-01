package org.mql.java;

import java.io.File;
import org.mql.java.models.Package;
import org.mql.java.models.Project;
import org.mql.java.reflection.ProjectExplorer;

public class Examples {

	public Examples() {
		exp1();
	}
	
	void exp1() {
		File file = new File("C:\\Users\\na-zi\\OneDrive\\Bureau\\MQL_NAZIH_AMRI\\S1\\JAVA\\UML Diagrams Generator\\bin");
			Project project = ProjectExplorer.parseAllPackages(file);
			for (Package pack : project.getPackages()) {
				System.out.println(pack.getName());
			}
		}
	
	public static void main(String[] args) {
		new Examples();
	}

}
