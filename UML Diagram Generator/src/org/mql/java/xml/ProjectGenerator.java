package org.mql.java.xml;

import java.io.File;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.mql.java.models.AnnotationModel;
import org.mql.java.models.ClassModel;
import org.mql.java.models.ConstantModel;
import org.mql.java.models.ConstructorModel;
import org.mql.java.models.EnumerationModel;
import org.mql.java.models.FieldModel;
import org.mql.java.models.InterfaceModel;
import org.mql.java.models.MethodModel;
import org.mql.java.models.PackageModel;
import org.mql.java.models.ProjectModel;
import org.mql.java.models.RelationModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ProjectGenerator {

	private static Document document;
	
	public ProjectGenerator() {
	}

	public static void generateXML(ProjectModel project) {
			
		File file = new File("resources/xml/project.xml");
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		create(project);
		write(file);
	}
	private static void create(ProjectModel project) {
		Element root = document.createElement("project");
		setAttribute(root, "path", project.getAbsolutePath().replace("\\bin", ""));
		document.appendChild(root);
		Element name = createNode("name");
		Text value = document.createTextNode(project.getName());
		name.appendChild(value);
		root.appendChild(name);
		// using Optional to handle nullable results
		Optional.ofNullable(project.getPackages())
        .ifPresent(packages -> packages.forEach(p -> appendPackage(p, root)));
	}

	public static Element createNode(String name) {
		return document.createElement(name);
	}

	private static void appendPackage(PackageModel p, Element parent) {
		Element packag = createNode("package");
		setAttribute(packag, "name", p.getName());
		p.getClasses().forEach(cls -> appendClass(cls, packag));
		p.getAnnotations().forEach(an -> appendAnnotation(an, packag));
		p.getInterfaces().forEach(in -> appendInterface(in, packag));
		p.getEnumerations().forEach(em -> appendEnumeration(em, packag));
		parent.appendChild(packag);
	}

	private static void appendFields(FieldModel field, Element parent) {
		Element f = createNode("field");
		setAttribute(f, "name", field.getName());
		setAttribute(f, "type", field.getType().getTypeName());
		setAttribute(f, "visibility", field.getModifier());
		parent.appendChild(f);
	}

	private static void appendConstructors(ConstructorModel cons, Element parent) {
	    Element constructor = createNode("constructor");
	    setAttribute(constructor, "name", cons.getName());
	    setAttribute(constructor, "visibility", cons.getModifier());

	    cons.getParameters().forEach(par -> {
	        Element parameter = createNode("parameter");
	        setAttribute(parameter, "type", par.getType().getName());
	        constructor.appendChild(parameter);
	    });

	    parent.appendChild(constructor);
	}

	private static void appendMethods(MethodModel meth, Element parent) {
	    Element method = createNode("method");
	    setAttribute(method, "name", meth.getName());
	    setAttribute(method, "visibility", meth.getModifier());
	    setAttribute(method, "return-type", meth.getReturnType());
	    meth.getParameters().forEach(par -> {
	        Element parameter = createNode("parameter");
	        setAttribute(parameter, "type", par.getType().getName());
	        method.appendChild(parameter);
	    });

	    parent.appendChild(method);
	}
	
	private static void appendRelationsV2(RelationModel relation, Element parent) {
		Element rel= createNode("relation");
		setAttribute(rel, "type", relation.getNameEnum().name().toLowerCase());
		Element target = createNode("target");
		target.appendChild(document.createTextNode(relation.getTarget()));
		rel.appendChild(target);
		parent.appendChild(rel);
	}

	private static void appendClass(ClassModel cls, Element parent) {
		Element c = createNode(cls.getModelType().toString().toLowerCase());
		setAttribute(c, "id", cls.getName());
		Element name = createNode("name");
		name.appendChild(document.createTextNode(cls.getSimpleName()));
		c.appendChild(name);
		cls.getFields().stream().forEach(field -> appendFields(field, c));
		cls.getConstructors().stream().forEach(cons -> appendConstructors(cons, c));
		cls.getMethods().stream().forEach(meth -> appendMethods(meth, c));
		cls.getRelations().stream().forEach(rel->appendRelationsV2(rel, c));
		parent.appendChild(c);
	}

	private static void appendInterface(InterfaceModel in, Element parent) {
		Element i = createNode(in.getModelType().toString().toLowerCase());
		setAttribute(i, "id", in.getName());
		Element name = createNode("name");
		name.appendChild(document.createTextNode(in.getSimpleName()));
		i.appendChild(name);
		in.getFields().stream().forEach(field -> appendFields(field, i));
		in.getMethods().stream().forEach(meth -> appendMethods(meth, i));
		parent.appendChild(i);
	}

	private static void appendAnnotation(AnnotationModel an, Element parent) {
		Element i = createNode(an.getModelType().toString().toLowerCase());
		setAttribute(i, "id", an.getName());
		Element name = createNode("name");
		name.appendChild(document.createTextNode(an.getSimpleName()));
		i.appendChild(name);
		an.getMethods().stream().forEach(meth -> appendMethods(meth, i));
		parent.appendChild(i);
	}

	private static void appendEnumeration(EnumerationModel em, Element parent) {
		Element e = createNode(em.getModelType().toString().toLowerCase());
		setAttribute(e, "id", em.getName());
		Element name = createNode("name");
		name.appendChild(document.createTextNode(em.getSimpleName()));
		e.appendChild(name);
		if (em.getConstants().size() > 0) {
			for (ConstantModel con : em.getConstants()) {
				Element f = createNode("constant");
				setAttribute(f, "name", (String) con.getConstant());
				e.appendChild(f);
			}
		}
		parent.appendChild(e);
	}

	private static void setAttribute(Element item, String name, String value) {
		item.setAttribute(name, value);
	}

	private static void write(File file) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "project.dtd");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			System.out.println("Xml File Generated with Success!");
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
}
