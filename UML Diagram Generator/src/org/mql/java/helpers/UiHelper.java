package org.mql.java.helpers;

import java.awt.Color;
import java.lang.reflect.Parameter;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

import org.mql.java.models.ClassModel;
import org.mql.java.models.ConstantModel;
import org.mql.java.models.ConstructorModel;
import org.mql.java.models.FieldModel;
import org.mql.java.models.MethodModel;

public class UiHelper {

	public UiHelper() {
	}

	public static String fieldToString(FieldModel field) {
		String res = "+";
		if ("private".equals(field.getModifier())) {
			res = "-";
		}
		return res += field.getName() + ": " + field.getType().getSimpleName();
	}

	public static String constructorToString(ConstructorModel cons) {
		StringBuffer res = new StringBuffer();
		if ("private".equals(cons.getModifier())) {
			res.append("-");
		} else
			res.append("+");
		res.append(cons.getName().substring(cons.getName().lastIndexOf(".") + 1) + "(");
		List<Parameter> parameters = cons.getParameters();
		if (parameters.size() > 0) {
			for (Parameter parameter : parameters) {
				res.append(parameter.getName()).append(": ").append(parameter.getType().getSimpleName()).append(", ");
			}
			if (res.length() > 0)
				res.setLength(res.length() - 2);
		}

		res.append(")");
		return "" + res;
	}

	public static String methodToString(MethodModel method) {
		StringBuffer res = new StringBuffer();
		if ("private".equals(method.getModifier())) {
			res.append("-");
		} else
			res.append("+");
		res.append(method.getName() + "(");
		List<Parameter> parameters = method.getParameters();
		if (parameters.size() > 0) {
			for (Parameter parameter : parameters) {
				res.append(parameter.getName()).append(": ").append(parameter.getType().getSimpleName()).append(", ");
			}
			if (res.length() > 0)
				res.setLength(res.length() - 2);
		}

		res.append(")" + ": " + method.getReturnType());
		return "" + res;
	}

	public static Color classColor() {
		return new Color(233, 228, 186);
	}

	public static Color packageColor() {
		return new Color(252, 242, 228);
	}

	public static Color interfaceColor() {
		return new Color(174, 216, 229);
	}

	public static Color enumColor() {
		return new Color(254, 183, 194);
	}

	public static int getHeightSize(ClassModel cls) {
		int elementHeight = 15;
		return 50 + elementHeight * (cls.getFields().size() + cls.getConstructors().size() + cls.getMethods().size());
	}

	public static JScrollPane appendFields(List<FieldModel> fields, String type) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		fields.forEach(f -> panel.add(new JLabel(fieldToString(f))));
		panel.setBackground("cls".equals(type) ? classColor() : interfaceColor());
		JScrollPane scrollPane = new JScrollPane(panel);
		return scrollPane;
	}

	public static JScrollPane appendMethods(List<MethodModel> methods, List<ConstructorModel> cons) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		if (cons != null && !cons.isEmpty())
			cons.forEach(c -> panel.add(new JLabel(constructorToString(c))));
		methods.stream().forEach(meth -> panel.add(new JLabel(methodToString(meth))));
		panel.setBackground(cons != null ? classColor() : interfaceColor());
		JScrollPane scrollPane = new JScrollPane(panel);
		return scrollPane;
	}

	public static JScrollPane appendConstant(List<ConstantModel> constants) {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		constants.forEach(c -> panel.add(new JLabel("" + c.getConstant())));
		panel.setBackground(enumColor());
		JScrollPane scrollPane = new JScrollPane(panel);
		return scrollPane;
	}

	public static JPanel createMarginPanel(Border border) {
		JPanel marginPanel = new JPanel();
		marginPanel.setBorder(border);
		marginPanel.setBackground(packageColor());
		return marginPanel;
	}

}
