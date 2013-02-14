package project.code;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import project.data.*;
import project.gui.DrawingPanel;

public class ClassOptions {
	private DrawingPanel dPanel;
	private int classID = 0;
	private ArrayList<ClassData> classData;
	private ArrayList<LinkData> linkData;

	public ClassOptions(DrawingPanel dP, ArrayList<ClassData> cD,
			ArrayList<LinkData> lD) {
		dPanel = dP;
		classData = cD;
		linkData = lD;
	}

	public void addClass(String name, Graphics g) {
		// find out the width of the class first
		FontMetrics metrics = g.getFontMetrics();
		int width = metrics.stringWidth(name) + 10;
		// now add the class
		classData.add(new ClassData(name, (dPanel.getWidth() - width) / 2,
				(dPanel.getHeight() - 60) / 2, width, 60, classID));
		// and increase classID
		classID++;
	}

	public void editClassName(int index, String name) {
		// set the name
		classData.get(index).setName(name);
	}

	public void deleteClass(int index) {
		// check if there are any links from / to this class and remove them:
		for (int i = 0; i < linkData.size(); i++) {
			if (classData.get(index).getID() == linkData.get(i).getFrom()
					|| classData.get(index).getID() == linkData.get(i).getTo())
				linkData.remove(i);
		}
		// remove the class itself
		classData.remove(index);
	}

	public void addVariable(int index, String access, String type, String name,
			boolean setget) {
		// default access modifier = write nothing in front
		if (access.equals("default"))
			access = "";
		// set the code part
		String code = access + " " + type + " " + name + ";";
		// change the access modifiers names to symbols
		if (access.equals("public"))
			access = "+";
		else if (access.equals("private"))
			access = "-";
		else if (access.equals("protected"))
			access = "#";
		// set the diagram part
		String diagram = access + name + ":" + type;
		// add the variable
		classData.get(index).addVariable(code, diagram);
		// change the height of the class (+13) if necessary
		if (classData.get(index).getVariables().size() > 1)
			classData.get(index).setHeight(
					classData.get(index).getHeight() + 13);

		// if user decides to automatically generate set and get methods...
		if (setget == true) {
			// set method in code
			String codeSetMethod = "public void set" + name + "(" + type
					+ " a)\n\t{\n\t\t" + name + " = a;\n\t}";
			// set method in diagram
			String diagramSetMethod = "+set" + name + "(a:" + type + "):void";
			// add the set method
			classData.get(index).addMethod(codeSetMethod, diagramSetMethod);
			// change the height of the class (+13) if necessary
			if (classData.get(index).getMethods().size() > 1)
				classData.get(index).setHeight(
						classData.get(index).getHeight() + 13);
			// get method in code
			String codeGetMethod = "public " + type + " get" + name
					+ "()\n\t{\n\t\t return " + name + ";\n\t}";
			// get method in diagram
			String diagramGetMethod = "+get" + name + "():" + type;
			// add the get method
			classData.get(index).addMethod(codeGetMethod, diagramGetMethod);
			// change the height of the class (+13) if necessary
			if (classData.get(index).getMethods().size() > 1)
				classData.get(index).setHeight(
						classData.get(index).getHeight() + 13);
		}
	}

	public void deleteVariable(int classIndex, int variableIndex) {
		// change the height of the class (-13) if necessary
		if (classData.get(classIndex).getVariables().size() > 1)
			classData.get(classIndex).setHeight(
					classData.get(classIndex).getHeight() - 13);
		// remove the variable
		classData.get(classIndex).getVariables().remove(variableIndex);
	}

	public void addMethod(int index, String access, String type, String name) {
		// default access modifier = write nothing in front
		if (access.equals("default"))
			access = "";
		// set the code part
		String code = access + " " + type + " " + name
				+ "()\n\t{\n\t\t//Your code here...\n\t}";
		// change the access modifiers names to symbols
		if (access.equals("public"))
			access = "+";
		else if (access.equals("private"))
			access = "-";
		else if (access.equals("protected"))
			access = "#";
		// set the diagram part
		String diagram = access + name + "():" + type;
		// add the method
		classData.get(index).addMethod(code, diagram);
		// change the height of the class (+13) if necessary
		if (classData.get(index).getMethods().size() > 1)
			classData.get(index).setHeight(
					classData.get(index).getHeight() + 13);
	}

	public void deleteMethod(int classIndex, int methodIndex) {
		// change the height of the class (-13) if necessary
		if (classData.get(classIndex).getMethods().size() > 1)
			classData.get(classIndex).setHeight(
					classData.get(classIndex).getHeight() - 13);
		// remove the method
		classData.get(classIndex).getMethods().remove(methodIndex);
	}

	public void addConstructor(int index) {
		// add the constructor
		classData.get(index).addMethod(
				classData.get(index).getName()
						+ "()\n\t{\n\t\t//Your code here...\n\t}",
				classData.get(index).getName() + "()");
		// change the height of the class (+13) if necessary
		if (classData.get(index).getMethods().size() > 1)
			classData.get(index).setHeight(
					classData.get(index).getHeight() + 13);
	}

	public void addMainMethod(int index) {
		// add main method
		classData
				.get(index)
				.addMethod(
						"public static void main(String[] args)\n\t{\n\t\t//Your code here...\n\t}",
						"+main(String[] args):static void");
		// change the height of the class (+13) if necessary
		if (classData.get(index).getMethods().size() > 1)
			classData.get(index).setHeight(
					classData.get(index).getHeight() + 13);
	}

}
