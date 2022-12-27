package Main;

import BusinessLayer.Restaurant;
import PresentationLayer.AdminGUI;
import PresentationLayer.ChefGUI;
import PresentationLayer.WaiterGUI;
import dataLayer.RestaurantSerializator;

public class Main {
	public static void main(String[] args) {
		Restaurant r = new Restaurant();
		RestaurantSerializator serializator = new RestaurantSerializator();
		//serializator.writeData(r, "restaurant.ser");
		String argument=args[0];
		serializator.readData(r, argument);
		AdminGUI admin = new AdminGUI(r,serializator,argument);
		WaiterGUI waiter = new WaiterGUI(r);
		ChefGUI chef=new ChefGUI(r);
	}
}
