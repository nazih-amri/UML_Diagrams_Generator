package org.mql.java.xml;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
	private Node node;
	private XMLNode children[];
	private Document document;

	public XMLNode(String source) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(source);
			Node node = document.getFirstChild();
			while (node.getNodeType() != Node.ELEMENT_NODE) {
				node = node.getNextSibling();
			}
			setNode(node);
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	public XMLNode(Node node) {
		super();
		setNode(node);
	}

	public void setNode(Node node) {
		this.node = node;
		extractChildren();
	}

	private void extractChildren() {
		NodeList list = node.getChildNodes();
		LinkedList<XMLNode> nodes = new LinkedList<XMLNode>();
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
				nodes.add(new XMLNode(list.item(i)));
			}
		}
		children = new XMLNode[nodes.size()];
		nodes.toArray(children);
	}

	public Node getNode() {
		return node;
	}

	public String getName() { // Delegate Method
		return node.getNodeName();
	}

	public boolean isNamed(String name) {
		return node.getNodeName().equals(name);
	}

	public XMLNode[] getChildren() {
		return children;
	}

	public XMLNode getChild(String name) {
		for (XMLNode child : children) {
			if (child.isNamed(name)) {
				return child;
			}
		}
		return null;
	}

	public String getValue() {
		Node child = node.getFirstChild();
		if (child != null && child.getNodeType() == Node.TEXT_NODE) {
			return child.getNodeValue();
		}
		return "";
	}

	public String getAttribute(String name) {
		Node att = node.getAttributes().getNamedItem(name);
		if (att == null)
			return "";
		return att.getNodeValue();
	}
	
	public List<XMLNode> getNodeComposants(String targetModel,String tagName) {
	    NodeList classList = document.getElementsByTagName(tagName);
	    for (int i = 0; i < classList.getLength(); i++) {
	        Element classElement = (Element) classList.item(i);
	        if (targetModel.equals(classElement.getAttribute("id"))) {
	            XMLNode result = new XMLNode((Node) classList.item(i));
	            return Arrays.asList(result.getChildren());
	        }
	    }
	    // System.out.println("className not found!");
	    return Collections.emptyList();
	}
	
	public List<XMLNode> getChilds(String tagName) {
	    NodeList childList = document.getElementsByTagName(tagName);
	    List<XMLNode> nodes = new Vector<>(); 
	    for (int i = 0; i < childList.getLength(); i++) {
	        nodes.add(new XMLNode((Node) childList.item(i)));
	    }
	    return nodes;
	}

}
