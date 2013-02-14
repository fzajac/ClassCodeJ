package project.listeners;

import java.awt.event.*;

import project.gui.DrawingPanel;

public class DPPositionListener implements MouseMotionListener {
	private DrawingPanel dPanel;

	public DPPositionListener(DrawingPanel dP) {
		dPanel = dP;
	}

	public void mouseMoved(MouseEvent e) {
		dPanel.setMousePosition(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {
		dPanel.setMousePosition(e.getX(), e.getY());
		dPanel.tryMoveClass(e.getX(), e.getY());
	}

}
