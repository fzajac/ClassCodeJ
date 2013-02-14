package project.gui;

import javax.swing.*;

import project.listeners.MenuListener;

public class Menu extends JMenuBar {
	private JMenu mFile, mCode, mOptions;

	Menu(DrawingPanel dP, Dialogues d) {
		// try to set system look & feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//set the listener
		MenuListener listener = new MenuListener(dP, d);

		//add the menus
		mFile = new JMenu("File");
		mCode = new JMenu("Code");
		mOptions = new JMenu("Options");
		this.add(mFile);
		this.add(mCode);
		this.add(mOptions);

		//add the menu items
		JMenuItem fileNew = new JMenuItem("New project");
		mFile.add(fileNew);
		fileNew.addActionListener(listener);

		JMenuItem fileSaveImage = new JMenuItem("Save as image");
		mFile.add(fileSaveImage);
		fileSaveImage.addActionListener(listener);

		mFile.addSeparator();

		JMenu fileAdd = new JMenu("Add");
		mFile.add(fileAdd);

		JMenuItem addClass = new JMenuItem("Class");
		fileAdd.add(addClass);
		addClass.addActionListener(listener);

		JMenuItem addLink = new JMenuItem("Link");
		fileAdd.add(addLink);
		addLink.addActionListener(listener);

		/*
		 * Commenting out cause not implemented and not essential. We will worry
		 * about these if we will have enough time.
		 * 
		 * menuItem = new JMenuItem("Open"); mFile.add(menuItem);
		 * menuItem.addActionListener(listener); menuItem = new
		 * JMenuItem("Save"); mFile.add(menuItem);
		 * menuItem.addActionListener(listener); mFile.addSeparator();
		 */

		mFile.addSeparator();

		JMenuItem fileExit = new JMenuItem("Exit");
		mFile.add(fileExit);
		fileExit.addActionListener(listener);

		JMenuItem codeGenerate = new JMenuItem("Generate code");
		mCode.add(codeGenerate);
		codeGenerate.addActionListener(listener);

		JMenuItem codeOpen = new JMenuItem("Open code");
		mCode.add(codeOpen);
		codeOpen.addActionListener(listener);

		JMenuItem codePrint = new JMenuItem("Print code");
		mCode.add(codePrint);
		codePrint.addActionListener(listener);

		JCheckBoxMenuItem optionsAA = new JCheckBoxMenuItem("AntiAliasing");
		optionsAA.setState(true);
		mOptions.add(optionsAA);
		optionsAA.addActionListener(listener);

		mOptions.addSeparator();

		JMenu optionsBG = new JMenu("Background");
		mOptions.add(optionsBG);

		JMenuItem blue = new JMenuItem("Cyan");
		optionsBG.add(blue);
		blue.addActionListener(listener);

		JMenuItem gray = new JMenuItem("Gray");
		optionsBG.add(gray);
		gray.addActionListener(listener);

		JMenuItem white = new JMenuItem("White");
		optionsBG.add(white);
		white.addActionListener(listener);

		JMenuItem red = new JMenuItem("Yellow");
		optionsBG.add(red);
		red.addActionListener(listener);

		JMenu cBG = new JMenu("Classes background");
		mOptions.add(cBG);

		JMenuItem blue2 = new JMenuItem("Cyan Box");
		cBG.add(blue2);
		blue2.addActionListener(listener);

		JMenuItem gray2 = new JMenuItem("Gray Box");
		cBG.add(gray2);
		gray2.addActionListener(listener);

		JMenuItem white2 = new JMenuItem("White Box");
		cBG.add(white2);
		white2.addActionListener(listener);

		JMenuItem red2 = new JMenuItem("Yellow Box");
		cBG.add(red2);
		red2.addActionListener(listener);
	}
}
