package project.data;

public class LinkData {
	// this class stores data for the generated link (name, cardinality)
	// and IDs of the classes it's coming from and going to

	private int fromID;
	private int toID;
	private String cardinality;
	private String name;

	public LinkData(int from, int to, String c, String n) {
		fromID = from;
		toID = to;
		cardinality = c;
		name = n;
	}

	public int getFrom() {
		return fromID;
	}

	public int getTo() {
		return toID;
	}

	public String getCardinality() {
		return cardinality;
	}

	public String getName() {
		return name;
	}
}
