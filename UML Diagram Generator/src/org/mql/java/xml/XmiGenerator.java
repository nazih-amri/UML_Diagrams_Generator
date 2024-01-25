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
import static org.mql.java.helpers.ParserHelper.*;
public class XmiGenerator {

	private static Document document;
	
	public XmiGenerator() {
	}

	public static void generateXMI(ProjectModel project) {
			
		File file = new File("resources/xml/projectXmiFormat.xmi");
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
		Element root = document.createElement("xmi:XMI");
		setAttribute(root, "xmlns:xmi", "http://www.omg.org/XMI");
		setAttribute(root, "xmlns:uml", "http://www.omg.org/UML/2001");
		document.appendChild(root);
		Element mod = createNode("uml:Model");
		setAttribute(mod, "name", project.getName());
		root.appendChild(mod);
		// using Optional to handle nullable results
		Optional.ofNullable(project.getPackages())
        .ifPresent(packages -> packages.forEach(p -> appendPackage(p, root)));
	}


	private static void appendPackage(PackageModel p, Element parent) {
		Element packag = createNode("packagedElement");
		setAttribute(packag, "xmi:id", generateXmiId());
		setAttribute(packag, "xmi:type", "uml:Package");
		setAttribute(packag, "name", p.getName());
		p.getClasses().forEach(cls -> appendClass(cls, packag));
		p.getAnnotations().forEach(an -> appendAnnotation(an, packag));
		p.getInterfaces().forEach(in -> appendInterface(in, packag));
		p.getEnumerations().forEach(em -> appendEnumeration(em, packag));
		parent.appendChild(packag);
	}

	private static void appendFields(FieldModel field, Element parent) {
		Element f = createNode("ownedAttribute");
		setAttribute(f, "xmi:id", generateXmiId());
		setAttribute(f, "xmi:type", "uml:Property");
		setAttribute(f, "name", field.getName());
		setAttribute(f, "type", field.getType().getTypeName());
		setAttribute(f, "visibility", field.getModifier());
		parent.appendChild(f);
	}

	private static void appendConstructors(ConstructorModel cons, Element parent) {
		
	    Element constructor = createNode("ownedOperation");
	    setAttribute(constructor, "xmi:id", generateXmiId());
	    setAttribute(constructor, "xmi:type", "uml:Operation");
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
		Element method = createNode("ownedOperation");
		setAttribute(method, "xmi:id", generateXmiId());
		setAttribute(method, "xmi:type", "uml:Operation");
	    setAttribute(method, "name", meth.getName());
	    setAttribute(method, "visibility", meth.getModifier());
	    meth.getParameters().forEach(par -> {
	        Element parameter = createNode("ownedParameter");
	        setAttribute(parameter, "xmi:id", generateXmiId());
	        setAttribute(parameter, "xmi:type", "uml:Parameter");
	        setAttribute(parameter, "name", par.getName());
	        setAttribute(parameter, "type", par.getType().getName());
	        method.appendChild(parameter);
	    });

	    parent.appendChild(method);
	}
	
	private static void setAttribute(Element item, String name, String value) {
		item.setAttribute(name, value);
	}
	
	private static void appendRelationsV2(RelationModel relation, Element parent) {
		Element rel= createNode("packagedElement");
		setAttribute(rel, "target", relation.getTarget());
		setAttribute(rel, "xmi:id", generateXmiId());
		setAttribute(rel, "xmi:type", "uml:"+relation.getNameEnum().name());
		parent.appendChild(rel);
	}

	private static void appendClass(ClassModel cls, Element parent) {
		Element c = createNode("packagedElement");
		setAttribute(c, "xmi:id", generateXmiId());
		setAttribute(c, "xmi:type", "uml:Class");
		setAttribute(c, "name", cls.getSimpleName());
		cls.getFields().stream().forEach(field -> appendFields(field, c));
		cls.getConstructors().stream().forEach(cons -> appendConstructors(cons, c));
		cls.getMethods().stream().forEach(meth -> appendMethods(meth, c));
		cls.getRelations().stream().forEach(rel->appendRelationsV2(rel, c));
		parent.appendChild(c);
	}

	private static void appendInterface(InterfaceModel in, Element parent) {
		Element i = createNode("packagedElement");
		setAttribute(i, "xmi:id", generateXmiId());
		setAttribute(i, "xmi:type", "uml:Interface");
		setAttribute(i, "name", in.getSimpleName());
		in.getFields().stream().forEach(field -> appendFields(field, i));
		in.getMethods().stream().forEach(meth -> appendMethods(meth, i));
		parent.appendChild(i);
	}

	private static void appendAnnotation(AnnotationModel an, Element parent) {
		Element i = createNode("packagedElement");
		setAttribute(i, "xmi:id", generateXmiId());
		setAttribute(i, "xmi:type", "uml:Annotation");
		setAttribute(i, "name", an.getSimpleName());
		an.getMethods().stream().forEach(meth -> appendMethods(meth, i));
		parent.appendChild(i);
	}

	private static void appendEnumeration(EnumerationModel em, Element parent) {
		Element e = createNode("packagedElement");
		setAttribute(e, "xmi:id", generateXmiId());
		setAttribute(e, "xmi:type", "uml:Enumeration");
		setAttribute(e, "name", em.getSimpleName());
		if (em.getConstants().size() > 0) {
			for (ConstantModel con : em.getConstants()) {
				Element f = createNode("ownedLiteral");
				setAttribute(f, "xmi:type","uml:EnumerationLiteral");
				setAttribute(f, "name", (String) con.getConstant());
				e.appendChild(f);
			}
		}
		parent.appendChild(e);
	}
	
	public static Element createNode(String name) {
		return document.createElement(name);
	}

	private static void write(File file) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			System.out.println("Xmi File Generated with Success!");
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}
}
