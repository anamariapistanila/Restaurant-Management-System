package BusinessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
	private static int increment = 0;

	private int id;

	public MenuItem() {
		id = increment++;
	}

	public static int getIncrement() {
		return increment;
	}

	public static void setIncrement(int increment) {
		MenuItem.increment = increment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public abstract double getPret();

	public abstract String getNume();

	public String toString() {
		return "MenuItem{" + "id=" + id + '}';
	}

}
