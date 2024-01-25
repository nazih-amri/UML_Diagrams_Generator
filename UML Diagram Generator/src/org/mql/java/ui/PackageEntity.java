package org.mql.java.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import org.mql.java.models.PackageModel;
import static org.mql.java.helpers.UiHelper.*;

public class PackageEntity extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel container;
	PackageModel pack;

	public PackageEntity(PackageModel pack) {
		this.pack = pack;
		this.container = new JPanel();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new BorderLayout());
		initContainerLayout();
		initNamePanel();
		addComponents();
		loadChilds();
	}

	private void initContainerLayout() {
		int margin = 20;
		int rows = Toolkit.getDefaultToolkit().getScreenSize().width / (200 + 2 * margin);
		container.setBackground(packageColor());
		container.setLayout(new GridLayout(0, rows, margin * 2, margin * 2));
	}

	private void initNamePanel() {
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.setBackground(packageColor());
		JLabel label = new JLabel(pack.getName());
		label.setFont(label.getFont().deriveFont(20f));
		label.setBorder(new LineBorder(Color.BLACK, 1));
		ImageIcon folder = new ImageIcon("resources/folder.png");
		Image resized = folder.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(resized);
		label.setIcon(icon);
		namePanel.add(label);
		add(namePanel, BorderLayout.NORTH);
	}

	private void addComponents() {
		add(container, BorderLayout.CENTER);
		add(createMarginPanel(new EmptyBorder(0, 0, 10, 0)), BorderLayout.SOUTH);
		add(createMarginPanel(new EmptyBorder(0, 10, 0, 0)), BorderLayout.WEST);
		add(createMarginPanel(new EmptyBorder(0, 0, 0, 10)), BorderLayout.EAST);
	}

	private void loadChilds() {
		pack.getClasses().forEach(cls -> container.add(new ClassEntity(cls)));
		pack.getInterfaces().forEach(in -> container.add(new InterfaceEntity(in)));
		pack.getEnumerations().forEach(en -> container.add(new EnumEntity(en)));
	}

}
