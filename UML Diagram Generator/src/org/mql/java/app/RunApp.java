package org.mql.java.app;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.mql.java.helpers.UiHelper;
import org.mql.java.models.ProjectModel;
import org.mql.java.reflection.ProjectExplorer;
import org.mql.java.ui.ProjectEntity;
import org.mql.java.xml.ProjectGenerator;
import org.mql.java.xml.ProjectParser;
import org.mql.java.xml.XmiGenerator;

public class RunApp extends JFrame {
	private static final long serialVersionUID = 1L;

	public RunApp(String path) {
		// Console display generated automatically after running the app.
		ProjectExplorer explorer = new ProjectExplorer(new File(path));
		ProjectModel project = explorer.getProject();
		ProjectGenerator.generateXML(project);
		XmiGenerator.generateXMI(project);
		drawProject(project);
	}

	public void projectParser() {
		// load a project from a given xml file.
		ProjectModel project = ProjectParser.parse("resources/xml/project.xml");
		System.out.println(project.getName());

	}

	public static void main(String[] args) {
		new RunApp("C:\\Users\\na-zi\\OneDrive\\Bureau\\MQL_NAZIH_AMRI\\S1\\JAVA\\p04-XML-Parsers");

	}

	private void drawProject(ProjectModel project) {
		if (project != null) {
			setTitle("UML Diagrams Generator");
			ProjectEntity panel = new ProjectEntity(project);
			panel.setBackground(UiHelper.packageColor());
			JScrollPane scrollPane = new JScrollPane(panel);
			setContentPane(scrollPane);
			config();
		}
	}

	private void config() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
