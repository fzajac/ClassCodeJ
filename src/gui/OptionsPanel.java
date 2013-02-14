package project.gui;

import java.awt.*;

import javax.swing.*;

import project.listeners.OPListener;

public class OptionsPanel extends JPanel {
	private JButton addClass, addLink, removeLink, openCode, generateCode;

	public OptionsPanel(Dialogues d) {
		// try to set system look & feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}

		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				Color.lightGray));

		// add the listener
		OPListener listener = new OPListener(d);

		// add the buttons
		addClass = new JButton("Add class");
		this.add(addClass, BorderLayout.WEST);
		addClass.addActionListener(listener);

		addLink = new JButton("Add link");
		this.add(addLink, BorderLayout.WEST);
		addLink.addActionListener(listener);

		removeLink = new JButton("Remove link");
		this.add(removeLink, BorderLayout.WEST);
		removeLink.addActionListener(listener);

		generateCode = new JButton("Generate code");
		this.add(generateCode, BorderLayout.WEST);
		generateCode.addActionListener(listener);

		openCode = new JButton("Open code");
		this.add(openCode, BorderLayout.WEST);
		openCode.addActionListener(listener);
	}
}