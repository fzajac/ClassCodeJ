package project.gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import project.code.CodeGenerator;
import project.data.ClassData;
import project.data.LinkData;
import project.listeners.DPListener;
import project.listeners.DPPositionListener;

public class MainFrame extends JFrame {
	private ArrayList<ClassData> classData = new ArrayList<ClassData>();
	private ArrayList<LinkData> linkData = new ArrayList<LinkData>();
	private CodeGenerator codeGenerator = new CodeGenerator(classData);
	private DrawingPanel dPanel = new DrawingPanel(classData, linkData,
			codeGenerator);
	private Dialogues dialogues = new Dialogues(dPanel, classData, linkData,
			codeGenerator);
	private OptionsPanel oPanel = new OptionsPanel(dialogues);
	private DPListener dPListener = new DPListener(dPanel);
	private DPPositionListener dPPListener = new DPPositionListener(dPanel);
	private Menu menuBar = new Menu(dPanel, dialogues);

	public MainFrame() {
		// try to set system look & feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}

		this.setJMenuBar(menuBar);
		this.setSize(600, 450); // set frame size
		this.setLocationRelativeTo(null); // position the frame (center)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("ClassCodeJ"); //frame's title
		this.setVisible(true); 
		
		//add panels
		add(oPanel, BorderLayout.NORTH);
		add(dPanel, BorderLayout.CENTER);
		
		//add mouse listeners to the dPanel
		dPanel.addMouseListener(dPListener);
		dPanel.addMouseMotionListener(dPPListener);
	}
}
