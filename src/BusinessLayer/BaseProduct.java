package BusinessLayer;

public class BaseProduct extends MenuItem {

	private double pret;
	private String nume;

	public BaseProduct(double pret, String nume) {
		super();
		this.pret = pret;
		this.nume = nume;
	}

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String toString() {
		return nume;
	}

}
