package project.gui;

import java.awt.Desktop;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import project.code.CodeGenerator;
import project.code.ImageFilter;
import project.data.ClassData;
import project.data.LinkData;

import java.io.File;

public class Dialogues {
	private DrawingPanel dPanel;
	private ArrayList<ClassData> classData;
	private ArrayList<LinkData> linkData;
	private int classIndex;
	private CodeGenerator codeGenerator;
	private String path;

	public Dialogues(DrawingPanel dP, ArrayList<ClassData> cD,
			ArrayList<LinkData> lD, CodeGenerator cG) {
		dPanel = dP;
		classData = cD;
		codeGenerator = cG;
		linkData = lD;
	}

	// set current class index to i
	public void setClassIndex(int i) {
		classIndex = i;
	}

	// add class
	public void addClass() {
		// pop a dialog to enter the class name
		String className = (String) JOptionPane.showInputDialog(dPanel,
				"Please enter the class name: ");
		// if anything has been entered into box and OK has been pressed
		if (className != null && className.length() > 0) {
			// check if the first character is a letter
			if (Character.isLetter(className.charAt(0))) {
				// check if all the characters are either letters or digits,
				// otherwise set legal to false
				boolean legal = true;
				for (int i = 0; i < className.length(); i++) {
					if (!Character.isLetter(className.charAt(i))
							&& !Character.isDigit(className.charAt(i)))
						legal = false;
					if (className.charAt(i) == ' ')
						legal = false;
				}
				if (legal == true) {
					// check if first character is upper case, otherwise change
					// it to upper case
					if (!Character.isUpperCase(className.charAt(0))) {
						className = className.substring(0, 1).toUpperCase()
								+ className.substring(1);
					}
					// check if class with this name already exists
					boolean exists = false;
					for (int i = 0; i < classData.size(); i++) {
						if (classData.get(i).getName().equals(className))
							exists = true;
					}
					// if it doesn't, create the class
					if (exists == false)
						dPanel.addClass(className);
					else
						JOptionPane
								.showMessageDialog(
										dPanel,
										"Class with the same name already exists. ",
										"Class naming error",
										JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(dPanel,
							"Illegal class name. ", "Class naming error",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(dPanel, "Illegal class name. ",
						"Class naming error", JOptionPane.ERROR_MESSAGE);
		} else if (className != null)
			JOptionPane.showMessageDialog(dPanel,
					"Class name has not been entered. ", "Class naming error",
					JOptionPane.ERROR_MESSAGE);
	}

	// change class name
	public void editClassName() {
		// pop a dialog to enter new class name
		String className = (String) JOptionPane.showInputDialog(dPanel,
				"Please enter the class name: ");
		// if anything has been entered into box and OK has been pressed
		if (className != null && className.length() > 0) {
			// check if the first character is a letter
			if (Character.isLetter(className.charAt(0))) {
				// check if all the characters are either letters or digits,
				// otherwise set legal to false
				boolean legal = true;
				for (int i = 0; i < className.length(); i++) {
					if (!Character.isLetter(className.charAt(i))
							&& !Character.isDigit(className.charAt(i)))
						legal = false;
					if (className.charAt(i) == ' ')
						legal = false;
				}
				if (legal == true) {
					// check if first character is upper case, otherwise change
					// it to upper case
					if (!Character.isUpperCase(className.charAt(0))) {
						className = className.substring(0, 1).toUpperCase()
								+ className.substring(1);
					}
					// check if class with this name already exists
					boolean exists = false;
					for (int i = 0; i < classData.size(); i++) {
						if (classData.get(i).getName().equals(className))
							exists = true;
					}
					// if it doesn't, create the class
					if (exists == false)
						dPanel.editClassName(classIndex, className);
					else
						JOptionPane
								.showMessageDialog(
										dPanel,
										"Class with the same name already exists. ",
										"Class naming error",
										JOptionPane.ERROR_MESSAGE);
				} else
					JOptionPane.showMessageDialog(dPanel,
							"Illegal class name. ", "Class naming error",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(dPanel, "Illegal class name. ",
						"Class naming error", JOptionPane.ERROR_MESSAGE);
		} else if (className != null)
			JOptionPane.showMessageDialog(dPanel,
					"Class name has not been entered. ", "Class naming error",
					JOptionPane.ERROR_MESSAGE);
	}

	// delete the class
	public void deleteClass() {
		int delete = JOptionPane.showConfirmDialog(dPanel,
				"Are you sure you want to permanently remove this class? ",
				"Confirm class removing", JOptionPane.YES_NO_OPTION);
		if (delete == 0)
			dPanel.deleteClass(classIndex);
	}

	// add a variable
	public void addVariable() {
		// pop a dialog to enter the variable name
		String varName = (String) JOptionPane.showInputDialog(dPanel,
				"Please enter the variable name: ");
		// if anything has been entered into box and OK has been pressed
		if (varName != null && varName.length() > 0) {
			// check if the first character is a letter
			if (Character.isLetter(varName.charAt(0))) {
				// check if first character is lower case, otherwise change
				// it to lower case
				if (!Character.isLowerCase(varName.charAt(0))) {
					varName = varName.substring(0, 1).toLowerCase()
							+ varName.substring(1);
				}
				// check if all the characters are either letters or digits,
				// otherwise set legal to false
				boolean legal = true;
				for (int i = 0; i < varName.length(); i++) {
					if (!Character.isLetter(varName.charAt(i))
							&& !Character.isDigit(varName.charAt(i)))
						legal = false;
					if (varName.charAt(i) == ' ')
						legal = false;
				}
				if (legal == true) {
					// ask user to select access modifier
					Object[] accessMoidfiers = { "public", "private",
							"protected", "default" };
					String access = (String) JOptionPane
							.showInputDialog(
									dPanel,
									"Please choose the access modifier for this variable: ",
									"Access modifiers",
									JOptionPane.PLAIN_MESSAGE, null,
									accessMoidfiers, "public");
					if (access != null) {
						// ask user to enter the variable type
						String type = (String) JOptionPane.showInputDialog(
								dPanel, "Please enter the variable type: ");
						if (type != null) {
							if (!type.equals("")) {
								// ask user if he wants to generate set and get
								// methods
								int setget = JOptionPane
										.showConfirmDialog(
												dPanel,
												"Do you want to add set and get methods for this variable? ",
												"Add set/get methods",
												JOptionPane.YES_NO_OPTION);
								if (setget == 0)
									dPanel.addVariable(classIndex, access,
											type, varName, true);
								else
									dPanel.addVariable(classIndex, access,
											type, varName, false);
							} else
								JOptionPane
										.showMessageDialog(
												dPanel,
												"Type of the variable has not been entered. ",
												"No type. ",
												JOptionPane.ERROR_MESSAGE);
						}
					}
				} else
					JOptionPane.showMessageDialog(dPanel,
							"Illegal variable name. ", "Variable naming error",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(dPanel,
						"Illegal variable name. ", "Variable naming error",
						JOptionPane.ERROR_MESSAGE);
		} else if (varName != null)
			JOptionPane.showMessageDialog(dPanel,
					"Variable name has not been entered. ",
					"Variable naming error", JOptionPane.ERROR_MESSAGE);
	}

	public void deleteVariable() {
		// check if the class has any variables
		if (classData.get(classIndex).getVariables().size() >= 1) {
			// if so, get their index and name to tmp array
			Object[] tmp = new Object[classData.get(classIndex).getVariables()
					.size()];
			for (int i = 0; i < classData.get(classIndex).getVariables().size(); i++) {
				tmp[i] = i + ":"
						+ classData.get(classIndex).getVariables().get(i);
			}
			// user selects the variable to delete...
			String delete = (String) JOptionPane.showInputDialog(dPanel,
					"Select variable to delete: ", "Delete a variable",
					JOptionPane.PLAIN_MESSAGE, null, tmp, "");
			if (delete != null) {
				// get variable index from the deleted string (all the digits to
				// ":")
				int deleteIndex = 0;
				for (int i = 0; i < delete.length(); i++) {
					if (delete.charAt(i) == ':')
						break;
					Character temp = delete.charAt(i);
					deleteIndex += Integer.parseInt(temp.toString());
				}
				// delete variable
				dPanel.deleteVariable(classIndex, deleteIndex);
			}
		} else
			JOptionPane.showMessageDialog(dPanel,
					"The class " + classData.get(classIndex).getName()
							+ " does not have any variables. ",
					"Delete a variable", JOptionPane.ERROR_MESSAGE);
	}

	public void addMethod() {
		String mName = (String) JOptionPane.showInputDialog(dPanel,
				"Please enter the method name: ");
		if (mName != null && mName.length() > 0) {
			if (Character.isLetter(mName.charAt(0))) {
				if (!Character.isLowerCase(mName.charAt(0))) {
					mName = mName.substring(0, 1).toLowerCase()
							+ mName.substring(1);
				}
				boolean legal = true;
				for (int i = 0; i < mName.length(); i++) {
					if (!Character.isLetter(mName.charAt(i))
							&& !Character.isDigit(mName.charAt(i)))
						legal = false;
					if (mName.charAt(i) == ' ')
						legal = false;
				}
				if (legal == true) {
					Object[] accessModifiers = { "public", "private",
							"protected", "default" };
					String access = (String) JOptionPane.showInputDialog(
							dPanel,
							"Please choose access modifier for this method: ",
							"Access modifiers", JOptionPane.PLAIN_MESSAGE,
							null, accessModifiers, "public");
					if (access != null) {
						String type = (String) JOptionPane.showInputDialog(
								dPanel, "Please enter the return type: ");
						if (type != null) {
							if (!type.equals("")) {
								dPanel.addMethod(classIndex, access, type,
										mName);
							} else
								JOptionPane
										.showMessageDialog(
												dPanel,
												"Type of the method has not been entered. ",
												"No type. ",
												JOptionPane.ERROR_MESSAGE);
						}
					}
				} else
					JOptionPane.showMessageDialog(dPanel,
							"Illegal method name. ", "Method naming error",
							JOptionPane.ERROR_MESSAGE);
			} else
				JOptionPane.showMessageDialog(dPanel,
						"Method name should start with a lowercase letter. ",
						"Method naming error", JOptionPane.ERROR_MESSAGE);
		} else if (mName != null)
			JOptionPane.showMessageDialog(dPanel,
					"Method name has not been entered. ",
					"Method naming error", JOptionPane.ERROR_MESSAGE);
	}

	public void addConstructor() {
		dPanel.addConstructor(classIndex);
	}

	public void addMainMethod() {
		dPanel.addMainMethod(classIndex);
	}

	public void deleteMethod() {
		if (classData.get(classIndex).getMethods().size() >= 1) {
			Object[] tmp = new Object[classData.get(classIndex).getMethods()
					.size()];
			for (int i = 0; i < classData.get(classIndex).getMethods().size(); i++) {
				tmp[i] = i + ":"
						+ classData.get(classIndex).getMethods().get(i);
			}
			String delete = (String) JOptionPane.showInputDialog(dPanel,
					"Select method to delete: ", "Delete a method",
					JOptionPane.PLAIN_MESSAGE, null, tmp, "");
			if (delete != null) {
				int deleteIndex = 0;
				for (int i = 0; i < delete.length(); i++) {
					if (delete.charAt(i) == ':')
						break;
					Character temp = delete.charAt(i);
					deleteIndex += Integer.parseInt(temp.toString());
				}
				dPanel.deleteMethod(classIndex, deleteIndex);
			}
		} else
			JOptionPane.showMessageDialog(dPanel,
					"The class " + classData.get(classIndex).getName()
							+ " does not have any methods. ",
					"Delete a method", JOptionPane.ERROR_MESSAGE);
	}

	public void addLink() {
		if (classData.size() >= 2) {
			Object[] tmp = new Object[classData.size()];
			for (int i = 0; i < classData.size(); i++) {
				tmp[i] = classData.get(i).getID() + ":"
						+ classData.get(i).getName();
			}
			String from = (String) JOptionPane.showInputDialog(dPanel,
					"Please choose a class from which you want to link: ",
					"Linking", JOptionPane.PLAIN_MESSAGE, null, tmp, "");
			if (from != null) {
				int fromID = 0;
				for (int i = 0; i < from.length(); i++) {
					if (from.charAt(i) == ':')
						break;
					Character temp = from.charAt(i);
					fromID += Integer.parseInt(temp.toString());
				}

				ArrayList<ClassData> classData2 = new ArrayList<ClassData>();
				for (int i = 0; i < classData.size(); i++) {
					if (classData.get(i).getID() != fromID) {
						classData2.add(classData.get(i));
					}
				}

				Object[] tmp2 = new Object[classData2.size()];
				for (int i = 0; i < classData2.size(); i++) {
					tmp2[i] = classData2.get(i).getID() + ":"
							+ classData2.get(i).getName();
				}

				String to = (String) JOptionPane.showInputDialog(dPanel,
						"Please choose a class to which you want to link: ",
						"Linking", JOptionPane.PLAIN_MESSAGE, null, tmp2, 0);

				if (to != null) {
					int toID = 0;
					for (int i = 0; i < to.length(); i++) {
						if (from.charAt(i) == ':')
							break;
						Character temp = to.charAt(i);
						toID += Integer.parseInt(temp.toString());
					}
					Object[] cardinalities = { "0..1", "0..*" };
					String cardinality = (String) JOptionPane.showInputDialog(
							dPanel, "Please select link cardinality: ",
							"Cardinality", JOptionPane.PLAIN_MESSAGE, null,
							cardinalities, "0..1");
					if (cardinality != null) {
						String linkName = (String) JOptionPane.showInputDialog(
								dPanel, "Please enter the name of the link: ");
						if (linkName != null)
							if (!linkName.equals(""))
								dPanel.addLink(fromID, toID, cardinality,
										linkName);
							else
								JOptionPane
										.showMessageDialog(
												dPanel,
												"Name of the link has not been entered. ",
												"No name. ",
												JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else
			JOptionPane.showMessageDialog(dPanel,
					"You need at least two classes to do this. ", "Linking",
					JOptionPane.ERROR_MESSAGE);
	}

	public void removeLink() {
		if (linkData.size() >= 1) {
			Object[] tmp = new Object[linkData.size()];
			for (int i = 0; i < linkData.size(); i++) {
				tmp[i] = i + ":" + linkData.get(i).getName();
			}
			String link = (String) JOptionPane.showInputDialog(dPanel,
					"Please choose a link to remove: ", "Remove a link",
					JOptionPane.PLAIN_MESSAGE, null, tmp, "");
			if (link != null) {
				int index = 0;
				for (int i = 0; i < link.length(); i++) {
					if (link.charAt(i) == ':')
						break;
					Character temp = link.charAt(i);
					index += Integer.parseInt(temp.toString());
				}
				dPanel.removeLink(index);
			}
		}
	}

	public void generateImage() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new ImageFilter());
		int rVal = fileChooser.showSaveDialog(null);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			String fileName = fileChooser.getSelectedFile().toString();
			String extension = "";
			for (int i = 0; i < 4; i++)
				extension = extension
						+ fileName.charAt(fileName.length() - 4 + i);
			if (!extension.equals(".png"))
				fileName = fileName + ".png";
			dPanel.paintToImage(fileName);
		}
	}

	public void generateCode() {
		if (classData.size() >= 1) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);
			int rVal = fileChooser.showOpenDialog(null);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				path = fileChooser.getSelectedFile().toString();
				String result = codeGenerator.generate(path);
				if (result != null)
					JOptionPane.showMessageDialog(dPanel,
							"An error occured while trying to save the generated code: \n"
									+ result, "Error generating code",
							JOptionPane.ERROR_MESSAGE);
				else {
					int open = JOptionPane.showConfirmDialog(dPanel,
							"Do you want to open the code now?",
							"Open the code", JOptionPane.YES_NO_OPTION);
					if (open == 0)
						openCode();
				}
			}
		}
	}

	public void openCode() {
		Desktop desktop = Desktop.getDesktop();
		if (path != null) {
			for (int i = 0; i < classData.size(); i++) {
				try {
					desktop.open(new File(path + "/"
							+ classData.get(i).getName() + ".java"));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(dPanel,
							"There has occured an error while trying to open the code: \n"
									+ e, "Error opening code",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			int generate = JOptionPane
					.showConfirmDialog(
							dPanel,
							"Code has not been generated yet. Do you want to generate the code now?",
							"Generate the code", JOptionPane.YES_NO_OPTION);
			if (generate == 0)
				generateCode();
		}
	}

	public void printCode() {
		Desktop desktop = Desktop.getDesktop();
		if (path != null) {
			try {
				desktop.print(new File(path + "/FullCode.txt"));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(dPanel,
						"There has occured an error while trying to print the code: \n"
								+ e, "Error printing code",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			int generate = JOptionPane
					.showConfirmDialog(
							dPanel,
							"Code has not been generated yet. Do you want to generate the code now?",
							"Generate the code", JOptionPane.YES_NO_OPTION);
			if (generate == 0)
				generateCode();
		}
	}

	// start new project
	public void newProject() {
		int answer = (JOptionPane.showConfirmDialog(dPanel,
				"Do you want to abandon this project and start a new one?",
				"Start a new project", JOptionPane.YES_NO_OPTION));
		if (answer == 0)
			dPanel.removeAll();
	}

	// exit
	public void exitApplication() {
		int answer = (JOptionPane.showConfirmDialog(dPanel,
				"Do you want to exit the program? Your project will be lost. ",
				"Exit", JOptionPane.YES_NO_OPTION));
		if (answer == 0)
			System.exit(0);
	}
}
