package project.gui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

import project.code.CodeGenerator;
import project.data.ClassData;
import project.data.LinkData;
import project.listeners.CPMListener;

public class ClassPropertiesMenu extends JPopupMenu {
	private JPopupMenu menu;
	private int classIndex;
	private CPMListener listener;

	public ClassPropertiesMenu(DrawingPanel dP, ArrayList<ClassData> cD,
			ArrayList<LinkData> lD, CodeGenerator cG) {
		// try to set the system look & feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}
		// add CPMListener
		listener = new CPMListener(dP, classIndex, cD, lD, cG);
		// initialize the menu
		menu = new JPopupMenu();

		//create and add the items + listener
		JMenuItem editClassName = new JMenuItem("Edit class name");
		menu.add(editClassName);
		editClassName.addActionListener(listener);

		JMenuItem deleteClass = new JMenuItem("Delete this class");
		menu.add(deleteClass);
		deleteClass.addActionListener(listener);

		menu.addSeparator();

		JMenuItem editVariables = new JMenuItem("Delete a variable");
		menu.add(editVariables);
		editVariables.addActionListener(listener);

		JMenuItem addVariable = new JMenuItem("Add new variable");
		menu.add(addVariable);
		addVariable.addActionListener(listener);

		menu.addSeparator();

		JMenuItem editMethods = new JMenuItem("Delete a method");
		menu.add(editMethods);
		editMethods.addActionListener(listener);

		JMenuItem addMethod = new JMenuItem("Add new method");
		menu.add(addMethod);
		addMethod.addActionListener(listener);

		JMenuItem addConstructor = new JMenuItem("Add constructor");
		menu.add(addConstructor);
		addConstructor.addActionListener(listener);

		JMenuItem addMainMethod = new JMenuItem("Add main method");
		menu.add(addMainMethod);
		addMainMethod.addActionListener(listener);
	}

	public void show(Component invoker, int x, int y, int i) {
		// show the menu
		menu.show(invoker, x, y);
		classIndex = i;
		listener.setClassIndex(i);
	}
}
