package BusinessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

	private String nume;
	private ArrayList<MenuItem> produse;

	public CompositeProduct(String nume, ArrayList<MenuItem> produse) {
		super();
		this.nume = nume;
		this.produse = produse;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public ArrayList<MenuItem> getProduse() {
		return produse;
	}

	public void setProduse(ArrayList<MenuItem> produse) {
		this.produse = produse;
	}

	public double getPret() {
		double pret = 0.0;
		for (MenuItem menuItem : this.produse) {
			pret += menuItem.getPret();
		}

		return pret;
	}

	public String toString() {
		return nume;
	}
}
