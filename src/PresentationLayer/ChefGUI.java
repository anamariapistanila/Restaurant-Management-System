package PresentationLayer;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import BusinessLayer.Restaurant;



public class ChefGUI extends JFrame implements Observer  {

    public void update(Observable arg0, Object arg1) {
     
    
    }

   
    public ChefGUI(Restaurant restaurant) {
        this.setTitle("Chef");
       
    }


}
