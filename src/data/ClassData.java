package project.data;

import java.util.ArrayList;

public class ClassData {
	// this class stores data for the generated class (name, position, size, id)
	// and its variables and methods

	private String name;
	private int posX;
	private int posY;
	private int width;
	private int height;
	private boolean active;
	private ArrayList<String> variables = new ArrayList<String>();
	private ArrayList<String> variablesInCode = new ArrayList<String>();
	private ArrayList<String> methods = new ArrayList<String>();
	private ArrayList<String> methodsInCode = new ArrayList<String>();
	private int classID;

	public ClassData(String n, int x, int y, int w, int h, int id) {
		name = n;
		posX = x;
		posY = y;
		width = w;
		height = h;
		active = false;
		classID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public void setX(int x) {
		posX = x;
	}

	public void setY(int y) {
		posY = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int w) {
		width = w;
	}

	public void setHeight(int h) {
		height = h;
	}

	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean a) {
		active = a;
	}

	public void addVariable(String code, String diagram) {
		variablesInCode.add(code);
		variables.add(diagram);
	}

	public void addMethod(String code, String diagram) {
		methodsInCode.add(code);
		methods.add(diagram);
	}

	public ArrayList<String> getVariables() {
		return variables;
	}

	public ArrayList<String> getMethods() {
		return methods;
	}

	public ArrayList<String> getVariables4Code() {
		return variablesInCode;
	}

	public ArrayList<String> getMethods4Code() {
		return methodsInCode;
	}

	public int getID() {
		return classID;
	}
}
