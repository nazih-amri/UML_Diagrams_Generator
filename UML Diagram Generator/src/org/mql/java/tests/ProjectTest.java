package org.mql.java.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;
import org.mql.java.helpers.ModelLoader;
import org.mql.java.models.ProjectModel;
import org.mql.java.reflection.ProjectExplorer;

class ProjectTest {

	File file = new File("C:\\Users\\na-zi\\git\\repository3\\UML Diagram Generator");
	@Test
	void testProjectNotNull() {
		ProjectExplorer projectExplorer = new ProjectExplorer(file);
		ProjectModel project = projectExplorer.getProject();
		assertTrue(project!=null);
	}
	
	@Test
	void testProjectPackagesNotNull() {
		ProjectExplorer projectExplorer = new ProjectExplorer(file);
		ProjectModel project = projectExplorer.getProject();
		assertTrue(project.getPackages().size()>0);
	}
	
	@Test
	void testProjectName() {
		ProjectExplorer projectExplorer = new ProjectExplorer(file);
		ProjectModel project = projectExplorer.getProject();
		assertEquals("UML Diagram Generator",project.getName());
	}
	
	@Test
	void testModelLoader() {
		Class<?> cls = ModelLoader.loadModel(new File("C:\\Users\\na-zi\\git\\repository3\\UML Diagram Generator\\bin\\org\\mql\\java\\examples\\models\\Building.class"));
		assertNotNull(cls);
		assertEquals("Building", cls.getSimpleName());
	}
	
	@Test
	void testPackages() {
		ProjectExplorer projectExplorer = new ProjectExplorer(new File("C:\\Users\\na-zi\\OneDrive\\Bureau\\MQL_NAZIH_AMRI\\S1\\JAVA\\p04-XML-Parsers"));
		ProjectModel project = projectExplorer.getProject();
		assertEquals("p04-XML-Parsers",project.getName());
		assertEquals("org.mql.java.xml", project.getPackages().get(0).getName());
		assertEquals("ANNOTATION", project.getPackages().get(0).getAnnotations().get(0).getModelType().name());
	}
	
	@Test
	void testClasses() {
		ProjectExplorer projectExplorer = new ProjectExplorer(new File("C:\\Users\\na-zi\\OneDrive\\Bureau\\MQL_NAZIH_AMRI\\S1\\JAVA\\p04-XML-Parsers"));
		ProjectModel project = projectExplorer.getProject();
		assertEquals("p04-XML-Parsers",project.getName());
		assertEquals("org.mql.java.xml", project.getPackages().get(0).getName());
		assertEquals("CLASS", project.getPackages().get(0).getClasses().get(0).getModelType().name());
		assertEquals(0, project.getPackages().get(0).getClasses().get(0).getFields().size());
		assertEquals(7, project.getPackages().get(0).getClasses().get(0).getMethods().size());
		assertEquals(1, project.getPackages().get(0).getClasses().get(0).getConstructors().size());
	}
	
	
	
	
}
