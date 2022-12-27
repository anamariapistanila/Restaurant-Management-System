package BusinessLayer;

import java.util.ArrayList;
import java.util.Map;

public interface IProcessingRestaurant {

	public interface IRestaurantProcessing {
		 /**
	     * @pre pret >0.0
	     * @pre !nume.isEmpty()
	     */
		MenuItem creazaBase(double pret, String nume) throws Exception;
		 /**
	     * @pre items.size() > 0
	     * @pre !nume.isEmpty()
	     */
		MenuItem creazaComposite(String nume, ArrayList<MenuItem> items) throws Exception;

		void deleteProdus(String nume);

		void updateProdus(int id,MenuItem menuItem);
		  /**
	     * @pre meniu.size() > 0
	     * @pre id_tabel> 0
	     */
		Order creazaComanda(ArrayList<MenuItem> meniu, int id_tabel);
		/**
	     * @pre id_comanda >= 0
	     * @post pret > 0
	     */
		Double computePrice(Integer id_comanda);

		void Bill(Integer id) throws Exception;

		Map.Entry<Order, ArrayList<MenuItem>> getComandaDupaId(Integer id);

		Map<Order, ArrayList<MenuItem>> getComenzi();

		ArrayList<MenuItem> getProduse();
	}
}
