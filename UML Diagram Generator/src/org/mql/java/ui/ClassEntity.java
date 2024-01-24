package org.mql.java.ui;

import javax.swing.*;
import org.mql.java.models.ClassModel;
import java.awt.*;
import static org.mql.java.helpers.UiHelper.*;

public class ClassEntity extends JPanel {
	private static final long serialVersionUID = 1L;

	public ClassEntity(ClassModel cls) {
		 JPanel container = new JPanel();;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		container.add(appendName(cls.getSimpleName()));
		if (!cls.getFields().isEmpty()) container.add(appendFields(cls.getFields(),"cls"));
		if (!cls.getConstructors().isEmpty() || !cls.getMethods().isEmpty()) container.add(appendMethods(cls.getMethods(),cls.getConstructors()));
		container.setBackground(classColor());
		setPreferredSize(new Dimension(230, getHeightSize(cls)));
		setBackground(classColor());
		add(container);
	}

	private JPanel appendName(String content) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel label = new JLabel(content);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
		panel.setBackground(classColor());
		return panel;
	}
}
