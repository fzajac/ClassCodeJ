package project.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import project.code.ClassOptions;
import project.code.CodeGenerator;
import project.code.LinkOptions;
import project.data.ClassData;
import project.data.LinkData;

public class DrawingPanel extends JPanel {
	private Graphics graph;

	private ArrayList<ClassData> classData;
	private ArrayList<LinkData> linkData;

	private CodeGenerator codeGenerator;
	private ClassOptions classOptions;
	private LinkOptions linkOptions;
	private ClassPropertiesMenu cPMenu;

	private int mouseX = 0;
	private int mouseY = 0;
	private int mousePrevX;
	private int mousePrevY;

	private Font title = new Font("SansSerif", Font.BOLD, 12);
	private Font content = new Font("SansSerif", Font.PLAIN, 12);
	private Color classBoxColor = Color.white;

	private boolean aaEnabled = true;

	public DrawingPanel(ArrayList<ClassData> cD, ArrayList<LinkData> lD,
			CodeGenerator cG) {
		// try to set system look & feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println(e);
		}

		this.setLayout(null);
		this.setBackground(Color.white);
		classData = cD;
		linkData = lD;
		codeGenerator = cG;
		classOptions = new ClassOptions(this, classData, linkData);
		linkOptions = new LinkOptions(classData, linkData);
		cPMenu = new ClassPropertiesMenu(this, classData, linkData,
				codeGenerator);
	}

	// this method paints on the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintAA(g); // set anti-aliasing, if enabled
		paintClasses(g); // call the function painting all the classes
		paintLinks(g); // call the function painting all the links
		graph = g;
	}

	// this method paints to image
	public void paintToImage(String path) {
		BufferedImage image = new BufferedImage(this.getWidth(),
				this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		paintAA(g); // set anti-aliasing, if enabled

		// paint background
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		paintClasses(g); // call the function painting all the classes
		paintLinks(g); // call the function painting all the links

		// try to create the image
		try {
			ImageIO.write(image, "png", new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void paintAA(Graphics g) {
		if (aaEnabled == true) {
			// turn on anti-aliasing
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
	}

	private void paintClasses(Graphics g) {
		for (int i = 0; i < classData.size(); i++) {
			// auto-size:
			g.setFont(title);
			FontMetrics metrics = g.getFontMetrics();
			int width = metrics.stringWidth(classData.get(i).getName()) + 10;
			g.setFont(content);
			for (int j = 0; j < classData.get(i).getVariables().size(); j++) {
				int newWidth = metrics.stringWidth(classData.get(i)
						.getVariables().get(j)) + 10;
				if (newWidth > width)
					width = newWidth;
			}
			for (int j = 0; j < classData.get(i).getMethods().size(); j++) {
				int newWidth = metrics.stringWidth(classData.get(i)
						.getMethods().get(j)) + 10;
				if (newWidth > width)
					width = newWidth;
			}
			classData.get(i).setWidth(width);

			// draw class:
			drawClass(g, classData.get(i).getX(), (classData.get(i)).getY(),
					(classData.get(i)).getWidth(),
					(classData.get(i)).getHeight(),
					(classData.get(i)).getName(), i);
		}
	}

	private void paintLinks(Graphics g) {
		for (int j = 0; j < linkData.size(); j++) {
			// set fromIndex and toIndex
			int fromIndex = 0;
			int toIndex = 0;
			for (int i = 0; i < classData.size(); i++) {
				if (classData.get(i).getID() == linkData.get(j).getFrom())
					fromIndex = i;
				else if (classData.get(i).getID() == linkData.get(j).getTo())
					toIndex = i;
			}

			// set the starting and ending positions of the link line
			int x1, y1, x2, y2;
			if (classData.get(fromIndex).getX() < classData.get(toIndex).getX()
					&& (classData.get(fromIndex).getWidth() + classData.get(
							fromIndex).getX()) < classData.get(toIndex).getX()) {
				x1 = classData.get(fromIndex).getX()
						+ classData.get(fromIndex).getWidth();
				x2 = classData.get(toIndex).getX();
			} else if (classData.get(fromIndex).getX() <= (classData.get(
					toIndex).getX() + classData.get(toIndex).getWidth())
					&& (classData.get(fromIndex).getWidth() + classData.get(
							fromIndex).getX()) >= classData.get(toIndex).getX()) {
				x1 = classData.get(fromIndex).getX()
						+ (classData.get(fromIndex).getWidth()) / 2;
				x2 = classData.get(toIndex).getX()
						+ (classData.get(toIndex).getWidth()) / 2;
			} else {
				x1 = classData.get(fromIndex).getX();
				x2 = classData.get(toIndex).getX()
						+ classData.get(toIndex).getWidth();
			}
			if (classData.get(fromIndex).getY() < classData.get(toIndex).getY()
					&& (classData.get(fromIndex).getHeight() + classData.get(
							fromIndex).getY()) < classData.get(toIndex).getY()) {
				y1 = classData.get(fromIndex).getY()
						+ classData.get(fromIndex).getHeight();
				y2 = classData.get(toIndex).getY();
			} else if (classData.get(fromIndex).getY() <= (classData.get(
					toIndex).getY() + classData.get(toIndex).getHeight())
					&& (classData.get(fromIndex).getHeight() + classData.get(
							fromIndex).getY()) >= classData.get(toIndex).getY()) {
				y1 = classData.get(fromIndex).getY()
						+ (classData.get(fromIndex).getHeight()) / 2;
				y2 = classData.get(toIndex).getY()
						+ (classData.get(toIndex).getHeight()) / 2;
			} else {
				y1 = classData.get(fromIndex).getY();
				y2 = classData.get(toIndex).getY()
						+ classData.get(toIndex).getHeight();
			}

			// draw the link
			drawLink(g, x1, y1, x2, y2, linkData.get(j).getCardinality(),
					linkData.get(j).getName());
		}
	}

	// set aaEnabled flag
	public void setAntiAliasing() {
		if (aaEnabled == true)
			aaEnabled = false;
		else
			aaEnabled = true;
		repaint();
	}

	// set the color of the class box
	public void setClassBoxColor(Color c) {
		classBoxColor = c;
	}

	private void drawClass(Graphics g, int x, int y, int width, int height,
			String name, int classID) {
		int i;

		// draw the box filling
		g.setColor(classBoxColor);
		g.fillRect(x, y, width, height);

		// set color back to black
		g.setColor(Color.black);

		// draw the box lines
		g.drawRect(x, y, width, height);

		// draw the title
		g.setFont(title);
		g.drawString(name, x + 5, y + 15);

		// draw the line below the title
		g.drawLine(x, y + 20, x + width, y + 20);

		// draw the variables
		g.setFont(content);
		for (i = 0; i < classData.get(classID).getVariables().size(); i++)
			g.drawString(classData.get(classID).getVariables().get(i), x + 5, y
					+ 35 + i * 13);
		if (i == 0)
			i = 1;

		// draw the line separating variables and methods
		g.drawLine(x, y + 27 + i * 13, x + width, y + 27 + i * 13);

		// draw the methods
		for (int j = 0; j < classData.get(classID).getMethods().size(); j++)
			g.drawString(classData.get(classID).getMethods().get(j), x + 5, y
					+ 55 + (i - 1) * 13 + j * 13);
	}

	private void drawLink(Graphics g, int x1, int y1, int x2, int y2,
			String cardinality, String name) {
		// set color to black
		g.setColor(Color.black);

		// draw line and name of the link
		g.drawLine(x1, y1, x2, y2);
		g.drawString(name, x1 + ((x2 - x1) / 2), y1 + ((y2 - y1) / 2));

		// some trigonometric black magic to draw arrow and cardinality in the
		// right place:
		int a = x1 - x2;
		if (a < 0)
			a = a * (-1);
		int b = y1 - y2;
		if (b < 0)
			b = b * (-1);
		double c = Math.sqrt((double) ((b * b) + (a * a)));
		double cos = a / c;
		double a2 = cos * 5;
		double sin = b / c;
		double b2 = sin * 5;
		FontMetrics metrics = g.getFontMetrics();
		if (x1 < x2) {
			if (y1 < y2) {
				int[] cx = { x2, (int) (x2 - a2 - b2), (int) (x2 - a2 + b2) };
				int[] cy = { y2, (int) (y2 - b2 + a2), (int) (y2 - b2 - a2) };
				g.fillPolygon(cx, cy, 3);
				g.drawString(
						cardinality,
						(int) (x2 - (cos * metrics.stringWidth(cardinality) - 2)),
						(int) (y2 - (sin * metrics.stringWidth(cardinality) + 5)));
			} else {
				int[] cx = { x2, (int) (x2 - a2 - b2), (int) (x2 - a2 + b2) };
				int[] cy = { y2, (int) (y2 + b2 - a2), (int) (y2 + b2 + a2) };
				g.fillPolygon(cx, cy, 3);
				g.drawString(
						cardinality,
						(int) (x2 - (cos * metrics.stringWidth(cardinality) - 2)),
						(int) (y2 + (sin * metrics.stringWidth(cardinality) - 5)));
			}
		} else {
			if (y1 < y2) {
				int[] cx = { x2, (int) (x2 + a2 + b2), (int) (x2 + a2 - b2) };
				int[] cy = { y2, (int) (y2 - b2 + a2), (int) (y2 - b2 - a2) };
				g.fillPolygon(cx, cy, 3);
				g.drawString(
						cardinality,
						(int) (x2 + (cos * metrics.stringWidth(cardinality) - 2)),
						(int) (y2 - (sin * metrics.stringWidth(cardinality) + 5)));
			} else {
				int[] cx = { x2, (int) (x2 + a2 + b2), (int) (x2 + a2 - b2) };
				int[] cy = { y2, (int) (y2 + b2 - a2), (int) (y2 + b2 + a2) };
				g.fillPolygon(cx, cy, 3);
				g.drawString(
						cardinality,
						(int) (x2 + (cos * metrics.stringWidth(cardinality) - 2)),
						(int) (y2 + (sin * metrics.stringWidth(cardinality) - 5)));
			}
		}
	}

	public void setMousePosition(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	public void rightClick(MouseEvent e) {
		for (int ind = 0; ind < classData.size(); ind++)
			if (mouseX > (classData.get(ind)).getX()
					&& mouseX < (classData.get(ind)).getX()
							+ (classData.get(ind)).getWidth()
					&& mouseY > (classData.get(ind)).getY()
					&& mouseY < (classData.get(ind)).getY()
							+ (classData.get(ind)).getHeight())
				cPMenu.show(e.getComponent(), e.getX(), e.getY(), ind);
	}

	public void startMoveClass() {
		for (int i = classData.size() - 1; i >= 0; i--) {
			if (mouseX > (classData.get(i)).getX()
					&& mouseX < (classData.get(i)).getX()
							+ (classData.get(i)).getWidth()
					&& mouseY > (classData.get(i)).getY()
					&& mouseY < (classData.get(i)).getY()
							+ (classData.get(i)).getHeight()) {

				// set this class to active
				(classData.get(i)).setActive(true);

				// set mousePrevX to know where the dragging started
				mousePrevX = mouseX - (classData.get(i)).getX();
				mousePrevY = mouseY - (classData.get(i)).getY();

				// this way the selected class will be drawn on the top
				classData.add(classData.get(i));
				classData.remove(i);

				repaint(); // repaint the panel
				break;
			}
		}
	}

	public void endMoveClass() {
		// no class is now active
		for (int i = classData.size() - 1; i >= 0; i--)
			(classData.get(i)).setActive(false);
	}

	public void tryMoveClass(int x, int y) {
		for (int i = classData.size() - 1; i >= 0; i--) {
			// move only active class
			if ((classData.get(i)).isActive() == true) {
				(classData.get(i)).setPosition(x - mousePrevX, y - mousePrevY);
				repaint();// repaint the panel

				// change the position
				if (x - mousePrevX < 0)
					(classData.get(i)).setX(0);
				if (y - mousePrevY < 0)
					(classData.get(i)).setY(0);

				repaint(); // repaint the panel
				break;
			}
		}
	}

	public void addLink(int fromID, int toID, String cardinality, String name) {
		linkOptions.addLink(fromID, toID, cardinality, name);
		repaint(); // repaint the panel
	}

	public void removeLink(int index) {
		linkOptions.removeLink(index);
		repaint(); // repaint the panel
	}

	public void addClass(String name) {
		classOptions.addClass(name, graph);
		repaint(); // repaint the panel
	}

	public void editClassName(int i, String name) {
		classOptions.editClassName(i, name);
		repaint(); // repaint the panel
	}

	public void deleteClass(int index) {
		classOptions.deleteClass(index);
		repaint(); // repaint the panel
	}

	public void addVariable(int index, String access, String type, String name,
			boolean setget) {
		classOptions.addVariable(index, access, type, name, setget);
		repaint(); // repaint the panel
	}

	public void deleteVariable(int classIndex, int variableIndex) {
		classOptions.deleteVariable(classIndex, variableIndex);
		repaint(); // repaint the panel
	}

	public void deleteMethod(int classIndex, int methodIndex) {
		classOptions.deleteMethod(classIndex, methodIndex);
		repaint(); // repaint the panel
	}

	public void addMethod(int index, String access, String type, String name) {
		classOptions.addMethod(index, access, type, name);
		repaint(); // repaint the panel
	}

	public void addConstructor(int index) {
		classOptions.addConstructor(index);
		repaint(); // repaint the panel
	}

	public void addMainMethod(int index) {
		classOptions.addMainMethod(index);
		repaint(); // repaint the panel
	}

	public void removeAll() {
		classData.removeAll(classData); // remove all classes
		linkData.removeAll(linkData); // remove all links
		repaint(); // repaint the panel
	}
}
