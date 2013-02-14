package project.listeners;

import java.awt.event.*;

import javax.swing.SwingUtilities;

import project.gui.DrawingPanel;

public class DPListener implements MouseListener {
	private DrawingPanel dPanel;

	public DPListener(DrawingPanel dP) {
		dPanel = dP;
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e))
			dPanel.startMoveClass();
	}

	public void mouseReleased(MouseEvent e) {
		dPanel.endMoveClass();
		if (SwingUtilities.isRightMouseButton(e))
			dPanel.rightClick(e);
	}
}
