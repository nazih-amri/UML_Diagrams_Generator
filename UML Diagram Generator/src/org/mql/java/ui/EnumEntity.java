package org.mql.java.ui;

import javax.swing.*;
import org.mql.java.models.EnumerationModel;
import java.awt.*;
import static org.mql.java.helpers.UiHelper.*;

public class EnumEntity extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel container;

	public EnumEntity(EnumerationModel en) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		container.add(appendName(en.getSimpleName()));
		if (!en.getConstants().isEmpty()) container.add(appendConstant(en.getConstants()));
		container.setBackground(enumColor());
		setBackground(enumColor());
		add(container);
	}

	private JPanel appendName(String content) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel label = new JLabel("<<"+content+">>");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
		panel.setBackground(enumColor());
		return panel;
	}
}
