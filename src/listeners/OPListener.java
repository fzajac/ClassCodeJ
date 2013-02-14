package project.listeners;

import java.awt.event.*;

import project.gui.Dialogues;

public class OPListener implements ActionListener {
	private Dialogues dialogues;

	public OPListener(Dialogues d) {
		dialogues = d;
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("Add class")) {
			dialogues.addClass();
		} else if (actionCommand.equals("Add link")) {
			dialogues.addLink();
		} else if (actionCommand.equals("Remove link")) {
			dialogues.removeLink();
		} else if (actionCommand.equals("Generate code")) {
			dialogues.generateCode();
		} else if (actionCommand.equals("Open code")) {
			dialogues.openCode();
		} else {
			System.out.println("Error");
		}
	}
}
