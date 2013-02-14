package project.code;

import java.util.ArrayList;

import project.data.*;

public class LinkOptions {
	private ArrayList<ClassData> classData;
	private ArrayList<LinkData> linkData;

	public LinkOptions(ArrayList<ClassData> cD, ArrayList<LinkData> lD) {
		classData = cD;
		linkData = lD;
	}

	public void addLink(int fromID, int toID, String cardinality, String name) {
		// add the link
		linkData.add(new LinkData(fromID, toID, cardinality, name));
		// find the name of the class the link is coming from and index of the
		// class the link is going to
		String fromName = "";
		int toIndex = 0;
		for (int i = 0; i < classData.size(); i++) {
			if (classData.get(i).getID() == fromID)
				fromName = classData.get(i).getName();
			else if (classData.get(i).getID() == toID)
				toIndex = i;
		}

		// if cardinality is 0..* generate an ArrayList and an add method for it
		if (cardinality == "0..*") {
			classData.get(toIndex).addVariable(
					"private ArrayList<" + fromName + "> " + name + ";",
					"-" + name + ":ArrayList<" + fromName + ">");

			if (classData.get(toIndex).getVariables().size() > 1)
				classData.get(toIndex).setHeight(
						classData.get(toIndex).getHeight() + 13);

			classData.get(toIndex).addMethod(
					"public void " + "add" + name + "(" + fromName + " a)"
							+ "()\n\t{\n\t\t" + name + ".add(a);\n\t}",
					"+add" + name + "(a:" + fromName + "):" + "void");

			if (classData.get(toIndex).getMethods().size() > 1)
				classData.get(toIndex).setHeight(
						classData.get(toIndex).getHeight() + 13);
		}
		// otherwise, if cardinality is 0..1, generate an instance of the class
		// object and set and get methods
		else {
			classData.get(toIndex).addVariable(
					"private " + fromName + " " + name + ";",
					"-" + name + ":" + fromName);

			if (classData.get(toIndex).getVariables().size() > 1)
				classData.get(toIndex).setHeight(
						classData.get(toIndex).getHeight() + 13);

			String codeSetMethod = "public void set" + name + "(" + fromName
					+ " a)\n\t{\n\t\t" + name + " = a;\n\t}";
			String diagramSetMethod = "+set" + name + "(a:" + fromName
					+ "):void";

			classData.get(toIndex).addMethod(codeSetMethod, diagramSetMethod);

			if (classData.get(toIndex).getMethods().size() > 1)
				classData.get(toIndex).setHeight(
						classData.get(toIndex).getHeight() + 13);

			String codeGetMethod = "public " + fromName + " get" + name
					+ "()\n\t{\n\t\t return " + name + ";\n\t}";
			String diagramGetMethod = "+get" + name + "():" + fromName;

			classData.get(toIndex).addMethod(codeGetMethod, diagramGetMethod);

			if (classData.get(toIndex).getMethods().size() > 1)
				classData.get(toIndex).setHeight(
						classData.get(toIndex).getHeight() + 13);
		}
	}

	public void removeLink(int index) {
		// remove the link
		linkData.remove(index);
	}
}
