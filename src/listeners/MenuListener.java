package project.listeners;

import java.awt.Color;
import java.awt.event.*;

import project.gui.Dialogues;
import project.gui.DrawingPanel;

public class MenuListener implements ActionListener {
	private DrawingPanel dPanel;
	private Dialogues dialogues;

	public MenuListener(DrawingPanel dP, Dialogues d) {
		dPanel = dP;
		dialogues = d;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "New project") {
			dialogues.newProject();
		} else if (e.getActionCommand() == "Save as image") {
			dialogues.generateImage();
		} else if (e.getActionCommand() == "Class") {
			dialogues.addClass();
		} else if (e.getActionCommand() == "Link") {
			dialogues.addLink();
		} else if (e.getActionCommand() == "Exit") {
			dialogues.exitApplication();
		} else if (e.getActionCommand() == "Generate code") {
			dialogues.generateCode();
		} else if (e.getActionCommand() == "Open code") {
			dialogues.openCode();
		} else if (e.getActionCommand() == "Print code") {
			dialogues.printCode();
		} else if (e.getActionCommand() == "AntiAliasing") {
			dPanel.setAntiAliasing();
		} else if (e.getActionCommand() == "Cyan") {
			dPanel.setBackground(Color.cyan);
			dPanel.repaint();
		} else if (e.getActionCommand() == "Gray") {
			dPanel.setBackground(Color.lightGray);
			dPanel.repaint();
		} else if (e.getActionCommand() == "White") {
			dPanel.setBackground(Color.white);
			dPanel.repaint();
		} else if (e.getActionCommand() == "Yellow") {
			dPanel.setBackground(Color.yellow);
			dPanel.repaint();
		} else if (e.getActionCommand() == "Cyan Box") {
			dPanel.setClassBoxColor(Color.cyan);
			dPanel.repaint();
		} else if (e.getActionCommand() == "Gray Box") {
			dPanel.setClassBoxColor(Color.lightGray);
			dPanel.repaint();
		} else if (e.getActionCommand() == "White Box") {
			dPanel.setClassBoxColor(Color.white);
			dPanel.repaint();
		} else if (e.getActionCommand() == "Yellow Box") {
			dPanel.setClassBoxColor(Color.yellow);
			dPanel.repaint();
		}
	}
}
