package org.mql.java.ui;

import javax.swing.*;
import org.mql.java.models.PackageModel;
import org.mql.java.models.ProjectModel;

public class ProjectPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ProjectModel project;

	public ProjectPanel(ProjectModel project) {
		this.project = project;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initComponents();
	}

	private void initComponents() {
		for (PackageModel pack : project.getPackages()) {
			add(new PackageEntity(pack));
	        add(Box.createVerticalStrut(10));
		}
	}
}