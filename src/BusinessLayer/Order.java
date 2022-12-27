package BusinessLayer;

import java.util.Date;

public class Order {
	private static int increment = 0;
 
	private int id_comanda;
    private Date data;
    private int numar_tabel;
    
    public Order(int numar_tabel) {
       // this.id_comanda= increment++;
        this.numar_tabel = numar_tabel;
        this.data = new Date();
    }

	public static int getIncrement() {
		return increment;
	}

	public static void setIncrement(int increment) {
		Order.increment = increment;
	}

	public int getId_comanda() {
		return id_comanda;
	}

	public void setId_comanda(int id_comanda) {
		this.id_comanda = id_comanda;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getNumar_tabel() {
		return numar_tabel;
	}

	public void setNumar_tabel(int numar_tabel) {
		this.numar_tabel = numar_tabel;
	}
    public boolean equals(Object obj) {
        Order o = (Order)obj;
        if(this.id_comanda == o.id_comanda) {
            if(this.data.equals(o.data)) {
                if(this.numar_tabel== o.numar_tabel) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode(){
        return ((id_comanda+numar_tabel)*99)/13;
    }
    
    
}
