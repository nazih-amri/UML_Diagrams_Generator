package org.mql.java.xml;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;
import org.mql.java.models.AnnotationModel;
import org.mql.java.models.BaseModel;
import org.mql.java.models.ClassModel;
import org.mql.java.models.ConstructorModel;
import org.mql.java.models.EnumerationModel;
import org.mql.java.models.FieldModel;
import org.mql.java.models.InterfaceModel;
import org.mql.java.models.MethodModel;
import org.mql.java.models.PackageModel;
import org.mql.java.models.ProjectModel;
import org.mql.java.models.RelationModel;
import static org.mql.java.helpers.ExplorerHelper.*
;public class ProjectParser {

	private static XMLNode root;

	public ProjectParser() {
	}

	public static ProjectModel parse(String source) {
		root = new XMLNode(source);
		File file = new File(root.getAttribute("path"));
		ProjectModel project = new ProjectModel(file);
		List<PackageModel> packages = new Vector<>();
		root.getChilds("package").forEach(pack -> parsePackage(pack, packages, project));
		project.setPackages(packages);
		return project;
	}

	private static void parsePackage(XMLNode pack, List<PackageModel> packages, ProjectModel project) {
		PackageModel p = new PackageModel(pack.getAttribute("name"), project.getAbsolutePath());
		parseModels(pack, p, project);
		packages.add(p);
	}

	private static void parseModels(XMLNode pack, PackageModel p, ProjectModel project) {
		Map<String, List<BaseModel>> modelMap = new HashMap<>();
		for (XMLNode node : pack.getChildren()) {
			String nodeName = node.getNode().getNodeName();
			BaseModel model = createModel(node, nodeName, project);
			modelMap.computeIfAbsent(nodeName, key -> new Vector<>()).add(model);
		}
		setModelsInPackage(p, modelMap);
	}

	private static BaseModel createModel(XMLNode node, String nodeName, ProjectModel project) {
		switch (nodeName) {
		case "class":
			ClassModel classModel = new ClassModel(node.getAttribute("id"));
			setFields(node, classModel);
			setConstructors(node, classModel);
			setMethods(node, classModel);
			setRelations(node, classModel, project);
			return classModel;
		case "annotation":
			AnnotationModel annot = new AnnotationModel(node.getAttribute("id"));
			setMethods(node, annot);
			return annot;
		case "interface":
			InterfaceModel inter = new InterfaceModel(node.getAttribute("id"));
			setFields(node, inter);
			setMethods(node, inter);
			return inter;
		case "enumeration":
			return new EnumerationModel(node.getAttribute("id"));
		default:
			return null;
		}
	}

	private static void setModelsInPackage(PackageModel p, Map<String, List<BaseModel>> modelMap) {
		for (Map.Entry<String, List<BaseModel>> entry : modelMap.entrySet()) {
			String key = entry.getKey();
			List<BaseModel> value = entry.getValue();
			switch (key) {
			case "class": p.setClasses(castList(value, ClassModel.class)); break;
			case "annotation": p.setAnnotations(castList(value, AnnotationModel.class)); break;
			case "interface": p.setInterfaces(castList(value, InterfaceModel.class)); break;
			case "enumeration": p.setEnumerations(castList(value, EnumerationModel.class)); break;
			}
		}
	}

	private static <T extends BaseModel> List<T> castList(List<? extends BaseModel> list, Class<T> cls) {
		// using functional programming
		return list.stream().filter(cls::isInstance).map(cls::cast).collect(Collectors.toList());
	}

	private static void setFields(XMLNode c, BaseModel model) {
		List<XMLNode> fields = Optional
				.ofNullable(root.getNodeComposants(model.getName(), model.getModelType().name().toLowerCase()))
				.map(composants -> composants.stream().filter(item -> "field".equals(item.getName())).toList())
				.orElse(Collections.emptyList());
		List<FieldModel> filledFields = new Vector<>();
		for (XMLNode f : fields) {
			FieldModel fd = new FieldModel(f.getAttribute("name"));
			fd.setModifier(f.getAttribute("visibility"));
			filledFields.add(fd);
		}
		if (model instanceof ClassModel) {
			((ClassModel) model).setFields(filledFields);
		} else if (model instanceof InterfaceModel) {
			((InterfaceModel) model).setFields(filledFields);
		}
	}

	private static void setConstructors(XMLNode c, ClassModel cm) {
		List<XMLNode> constructors = root.getNodeComposants(cm.getName(), "class").stream()
				.filter(item -> "constructor".equals(item.getName())).toList();
		List<ConstructorModel> filledConstructors = new Vector<>();
		for (XMLNode cons : constructors) {
			ConstructorModel cr = new ConstructorModel(cons.getAttribute("name"));
			cr.setModifier(cons.getAttribute("visibility"));
			filledConstructors.add(cr);
		}
		cm.setConstructors(filledConstructors);
	}

	private static void setMethods(XMLNode c, BaseModel model) {
		List<XMLNode> methodes = Optional
				.ofNullable(root.getNodeComposants(model.getName(), model.getModelType().name().toLowerCase()))
				.map(composants -> composants.stream().filter(item -> "method".equals(item.getName())).toList())
				.orElse(Collections.emptyList());
		List<MethodModel> filledMethods = new Vector<>();
		for (XMLNode meth : methodes) {
			MethodModel md = new MethodModel(meth.getAttribute("name"));
			md.setModifier(meth.getAttribute("visibility"));
			md.setReturnType(meth.getAttribute("return-type"));
			filledMethods.add(md);
		}
		if (model instanceof ClassModel) {
			((ClassModel) model).setMethods(filledMethods);
		} else if (model instanceof InterfaceModel) {
			((InterfaceModel) model).setMethods(filledMethods);
		} else if (model instanceof AnnotationModel) {
			((AnnotationModel) model).setMethods(filledMethods);
		}
	}

	private static void setRelations(XMLNode c, ClassModel cm, ProjectModel project) {
		List<XMLNode> relations = root.getNodeComposants(cm.getName(), "class").stream()
				.filter(item -> "relation".equals(item.getName())).toList();
		List<RelationModel> filledRelations = new Vector<>();
		for (XMLNode rel : relations) {
			String target = rel.getChild("target").getValue();
			RelationModel r = new RelationModel(getRelationType(rel.getAttribute("type")), cm.getName(), target);
			project.add(r);
			filledRelations.add(r);
		}
		cm.setRelations(filledRelations);
	}


}
