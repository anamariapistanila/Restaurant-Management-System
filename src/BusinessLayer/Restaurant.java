package BusinessLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Restaurant extends Observable implements IProcessingRestaurant {
	private Map<Order, ArrayList<MenuItem>> comenzi;
	private ArrayList<MenuItem> produse;

	public Restaurant() {
		produse = new ArrayList<>();
		comenzi = new HashMap<>();
	}

	public Map<Order, ArrayList<MenuItem>> getComenzi() {
		return comenzi;
	}

	public void setComenzi(Map<Order, ArrayList<MenuItem>> comenzi) {
		this.comenzi = comenzi;
	}

	public ArrayList<MenuItem> getProduse() {
		return produse;
	}

	public void setProduse(ArrayList<MenuItem> produse) {
		this.produse = produse;
	}

	/**
	 * Metoda creeaza un base product dupa un pret si un nume date ca argumente,
	 * acest obiect fiind adaugat in lista de produse ale restaurantului
	 */
	public MenuItem creazaBase(double pret, String nume) throws Exception {
		assert pret > 0 && !nume.isEmpty(); // preconditie

		for (MenuItem produs : this.produse) {
			if (produs.getNume().equals(nume)) {
				throw new Exception("Numele trebuie sa fie unic");
			}
		}

		MenuItem meniu = new BaseProduct(pret, nume);
		produse.add(meniu);
		return meniu;
	}

	/**
	 * Metoda creeaza un composite product, avand un nume si o lista de produse de
	 * tipul MenuItem
	 */
	public MenuItem creazaComposite(String nume, ArrayList<MenuItem> items) throws Exception {
		assert !nume.isEmpty() && !items.isEmpty(); // preconditie

		for (MenuItem item : this.produse) {
			if (item.getNume().equals(nume)) {
				throw new Exception("Numele trebuie sa fie unic");
			}
		}

		MenuItem meniu = new CompositeProduct(nume, items);
		((CompositeProduct) meniu).setProduse(items);
		produse.add(meniu);
		return meniu;
	}

	/**
	 * Metoda sterge un produs din lista de MenuItem a restaurantului dupa nume
	 */
	public void deleteProdus(String nume) {
		for (MenuItem m : this.produse) {
			if (m.getNume().equals(nume)) {
				this.produse.remove(m);
				break;
			}
		}
	}

	/**
	 * Metoda modifica un produs din lista meniului restaurantului dupa id
	 */
	public void updateProdus(int id, MenuItem menuItem) {
		for (int index = 0; index < produse.size(); index++) {
			if (produse.get(index).getId() == id) {
				produse.set(index, menuItem);
				break;
			}
		}
	}

	/**
	 * Metoda creeaza o noua comanda dupa o lista de produse si id-ul mesei
	 */
	public Order creazaComanda(ArrayList<MenuItem> meniu, int id_tabel) {
		assert meniu.size() > 0 && id_tabel > 0; // preconditie

		Order order = new Order(id_tabel);
		comenzi.put(order, meniu);

		return order;
	}

	/**
	 * Metoda calculeaza pretul unei comenzi
	 */
	public Double computePrice(Integer id_comanda) {
		assert id_comanda > 0; // preconditie

		Double pret = 0.0;

		Map.Entry<Order, ArrayList<MenuItem>> o = this.getComandaDupaId(id_comanda);
		if (o == null) {
			return pret;
		}

		for (MenuItem m : o.getValue()) {
			pret += m.getPret();
		}

		assert pret > 0; // postconditie
		return pret;
	}

	/**
	 * Se cauta comanda in hashmap dupa un id Se returneaza null in caz contrar
	 */
	private Map.Entry<Order, ArrayList<MenuItem>> getComandaDupaId(Integer id) {
		for (Map.Entry<Order, ArrayList<MenuItem>> o : comenzi.entrySet()) {
			if (o.getKey().getId_comanda() == id) {
				return o;
			}
		}

		return null;
	}

	/**
	 * Metoda genereaza factura ca si format txt
	 */
	public void Bill(Integer id) throws Exception {
		File file = new File("bill" + id + ".txt");
		FileWriter fileWriter = new FileWriter(file);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		Map.Entry<Order, ArrayList<MenuItem>> o = this.getComandaDupaId(id);
		if (o == null) {
			printWriter.close();
			throw new Exception("Order not found");

		}

		printWriter.println("Comanda: " + o.getKey().getId_comanda());
		printWriter.println("Data Comenzii: " + o.getKey().getData());
		printWriter.println("Numarul mesei: " + o.getKey().getNumar_tabel());
		printWriter.println("Totalul comenzii: " + this.computePrice(id));
		printWriter.println("---------------------------------");
		printWriter.close();
		fileWriter.close();
	}

}
