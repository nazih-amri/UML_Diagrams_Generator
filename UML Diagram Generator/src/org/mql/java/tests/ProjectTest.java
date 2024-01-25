package org.mql.java.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;
import org.mql.java.models.ProjectModel;
import org.mql.java.reflection.ProjectExplorer;

class ProjectTest {

	File file = new File("C:\\Users\\na-zi\\git\\repository3\\UML Diagram Generator");
	@Test
	void testProjectNotNull() {
		ProjectExplorer projectExplorer = new ProjectExplorer(file);
		ProjectModel project = projectExplorer.parse();
		assertTrue(project!=null);
	}
	
	@Test
	void testProjectPackagesNotNull() {
		ProjectExplorer projectExplorer = new ProjectExplorer(file);
		ProjectModel project = projectExplorer.parse();
		assertTrue(project.getPackages().size()>0);
	}
	
	@Test
	void testProjectName() {
		ProjectExplorer projectExplorer = new ProjectExplorer(file);
		ProjectModel project = projectExplorer.parse();
		assertEquals("UML Diagram Generator",project.getName());
	}
	
}
