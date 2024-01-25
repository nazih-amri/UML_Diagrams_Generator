package org.mql.java.ui;

import javax.swing.*;
import org.mql.java.models.InterfaceModel;
import java.awt.*;
import static org.mql.java.helpers.UiHelper.*;

public class InterfaceEntity extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel container;
	public InterfaceEntity(InterfaceModel in) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		container.add(appendName(in.getSimpleName()));
		if (!in.getFields().isEmpty()) container.add(appendFields(in.getFields(),"in"));
		if (!in.getMethods().isEmpty()) container.add(appendMethods(in.getMethods(),null));
		container.setBackground(interfaceColor());
		setBackground(interfaceColor());
		add(container);
	}

	private JPanel appendName(String content) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel label = new JLabel("<<interface>>");
		JLabel name = new JLabel(content);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
		panel.add(name);
		panel.setBackground(interfaceColor());
		return panel;
	}
}
