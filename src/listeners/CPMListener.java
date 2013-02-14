package project.listeners;

import java.awt.event.*;
import java.util.ArrayList;

import project.code.CodeGenerator;
import project.data.ClassData;
import project.data.LinkData;
import project.gui.Dialogues;
import project.gui.DrawingPanel;

public class CPMListener implements ActionListener {
	private Dialogues dialogues;

	public CPMListener(DrawingPanel dP, int i, ArrayList<ClassData> cD,
			ArrayList<LinkData> lD, CodeGenerator cG) {
		dialogues = new Dialogues(dP, cD, lD, cG);
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("Edit class name"))
			dialogues.editClassName();
		else if (actionCommand.equals("Delete this class")) {
			dialogues.deleteClass();
		} else if (actionCommand.equals("Delete a variable")) {
			dialogues.deleteVariable();
		} else if (actionCommand.equals("Add new variable")) {
			dialogues.addVariable();
		} else if (actionCommand.equals("Delete a method")) {
			dialogues.deleteMethod();
		} else if (actionCommand.equals("Add new method")) {
			dialogues.addMethod();
		} else if (actionCommand.equals("Add constructor")) {
			dialogues.addConstructor();
		} else if (actionCommand.equals("Add main method")) {
			dialogues.addMainMethod();
		} else
			System.out.println("Error. ");
	}

	public void setClassIndex(int i) {
		dialogues.setClassIndex(i);
	}
}
