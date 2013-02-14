package project.code;

import java.io.*;
import java.util.ArrayList;

import project.data.ClassData;

public class CodeGenerator {
	private ArrayList<ClassData> classData;

	public CodeGenerator(ArrayList<ClassData> cD) {
		classData = cD;
	}

	public String generate(String path) {
		String result = null;
		String fullCode = "";
		for (int i = 0; i < classData.size(); i++) {
			try {
				File file = new File(path + "/" + classData.get(i).getName()
						+ ".java");
				file.createNewFile();
				PrintWriter printer = new PrintWriter(file);
				// begin printing with the class name
				printer.println("public class " + classData.get(i).getName() + "\n{");
				fullCode += "public class " + classData.get(i).getName() + "\n{";
				// print variables
				for (int j = 0; j < classData.get(i).getVariables4Code().size(); j++) {
					printer.println("\t"
							+ classData.get(i).getVariables4Code().get(j));
					fullCode += "\t"
							+ classData.get(i).getVariables4Code().get(j) + "\n";
				}
				// print methods
				for (int j = 0; j < classData.get(i).getMethods4Code().size(); j++) {
					printer.println("\t\n\t"
							+ classData.get(i).getMethods4Code().get(j));
					fullCode += "\t\n\t"
							+ classData.get(i).getMethods4Code().get(j) + "\n";
				}
				// print "}" at the end of the class
				printer.println("}");
				fullCode += "}\n\n------\n\n";
				printer.close();
			} catch (IOException e) {
				result = e.toString();
			}
			try {
				File file = new File(path + "/FullCode.txt");
				file.createNewFile();
				PrintWriter printer = new PrintWriter(file);
				printer.println(fullCode);
				printer.close();
			}
			catch (IOException e) {
				result = e.toString();
			}
		}
		// it will return null if everything went well, otherwise it will return
		// the exception
		return result;
	}
}
