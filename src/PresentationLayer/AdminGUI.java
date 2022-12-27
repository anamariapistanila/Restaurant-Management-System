package PresentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import dataLayer.RestaurantSerializator;

public class AdminGUI extends JFrame {

	private JTable table = new JTable();
	private JPanel administratorPanel;
	private JList<MenuItem> itemsJList;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	static Restaurant restaurant = new Restaurant();
	DefaultTableModel model = new DefaultTableModel();
	String argument = new String();

	public AdminGUI(Restaurant restaurant, RestaurantSerializator serializator, String argument) {
		this.argument = argument;
		this.restaurant = restaurant;
		this.setTitle("Admin");
		administratorPanel = new JPanel();
		administratorPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		administratorPanel.setLayout(null);

		tableFiller(table, this.restaurant.getProduse(), model);
		table.setModel(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(508, 82, 437, 282);
		administratorPanel.add(scrollPane);

		itemsJList = new JList<>();
		JScrollPane itemsScrollPane = new JScrollPane(itemsJList);
		itemsScrollPane.setBounds(231, 82, 165, 116);
		administratorPanel.add(itemsScrollPane);
		setBounds(600, 100, 1012, 431);

		JButton btnNewButton = new JButton("Vizualizare");
		btnNewButton.setBounds(60, 49, 100, 23);
		administratorPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Adauga Produs");
		btnNewButton_1.setBounds(41, 96, 126, 23);
		administratorPanel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Modifica Produs");
		btnNewButton_2.setBounds(41, 144, 126, 23);
		administratorPanel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Sterge Produs");
		btnNewButton_3.setBounds(50, 190, 130, 23);
		administratorPanel.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(71, 282, 11, 14);
		administratorPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(94, 279, 73, 20);
		administratorPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("NUME");
		lblNewLabel_1.setBounds(193, 282, 282, 14);
		administratorPanel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(231, 279, 86, 20);
		administratorPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("PRET");
		lblNewLabel_2.setBounds(54, 327, 282, 14);
		administratorPanel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(94, 324, 73, 20);
		administratorPanel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("INGREDIENTE");
		lblNewLabel_3.setBounds(203, 327, 732, 14);
		administratorPanel.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(285, 324, 155, 20);
		administratorPanel.add(textField_3);
		textField_3.setColumns(10);
		setContentPane(this.administratorPanel);
		this.invalidate();
		this.validate();
		this.repaint();

		this.setVisible(true);
		Vector<MenuItem> vector = new Vector<MenuItem>();

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 1;

				for (int i = 0; i < AdminGUI.this.restaurant.getProduse().size(); i++) {
					for (int j = 0; j < table.getRowCount(); j++) {

						if (AdminGUI.this.restaurant.getProduse().get(i).getNume()
								.equals(AdminGUI.this.model.getValueAt(j, 1))) {
							AdminGUI.this.model.removeRow(j);
						}
					}
				}
				for (MenuItem m1 : AdminGUI.this.restaurant.getProduse()) {

					Object[] obj = { id, m1.getNume(), m1.getPret() };
					AdminGUI.this.model.addRow(obj);
					m1.setId(id);
					id++;
					AdminGUI.this.table.setModel(model);
				}
				AdminGUI.this.repaint();
				AdminGUI.this.revalidate();
			}

		});
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String name = textField_1.getText();
				if (!textField_2.getText().equals("")) {
					double p = Double.parseDouble(textField_2.getText());
					MenuItem m = new BaseProduct(p, name);
					try {
						AdminGUI.this.restaurant.creazaBase(p, name);
					} catch (Exception e) {
						e.printStackTrace();
					}

					vector.add(m);
				} else {

					ArrayList<MenuItem> prod = new ArrayList<MenuItem>();

					String ingr = textField_3.getText();
					for (String i : ingr.split("\\, ")) {
						String numeProdus = i;
						for (MenuItem it : AdminGUI.this.restaurant.getProduse()) {
							if (numeProdus.equals(it.getNume())) {
								MenuItem menu = new BaseProduct(it.getPret(), i);

								prod.add(menu);

							}
						}
					}

					try {
						MenuItem add = new CompositeProduct(name, prod);

						AdminGUI.this.restaurant.creazaComposite(name, prod);
						vector.add(add);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				itemsJList.setListData(vector);
				// RestaurantSerializator serializator = new RestaurantSerializator();
				serializator.writeData(AdminGUI.this.restaurant, argument);
				AdminGUI.this.repaint();
				AdminGUI.this.revalidate();

			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = textField_1.getText();

				for (MenuItem it : AdminGUI.this.restaurant.getProduse()) {
					if (it.getNume().equals(name)) {
						AdminGUI.this.restaurant.deleteProdus(name);
						break;
					}
				}

				int rowCount = model.getRowCount();
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}

				// RestaurantSerializator serializator = new RestaurantSerializator();
				// serializator.writeData(AdminGUI.this.restaurant, "restaurant.ser");

			}

		});

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = textField_1.getText();
				int id = Integer.parseInt(textField.getText());
				if (!textField_2.getText().equals("")) {
					double p = Double.parseDouble(textField_2.getText());
					MenuItem m = new BaseProduct(p, name);

					AdminGUI.this.restaurant.updateProdus(id, m);

					int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}

				} else {

					ArrayList<MenuItem> prod1 = new ArrayList<MenuItem>();

					String ingr = textField_3.getText();
					for (String i : ingr.split("\\, ")) {
						String numeProdus = i;
						for (MenuItem it : AdminGUI.this.restaurant.getProduse()) {
							if (numeProdus.equals(it.getNume())) {
								MenuItem menu = new BaseProduct(it.getPret(), i);
								prod1.add(menu);
							}
						}
					}
					MenuItem add = new CompositeProduct(name, prod1);

					AdminGUI.this.restaurant.updateProdus(id, add);
					int rowCount = model.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						model.removeRow(i);
					}

				}
				// RestaurantSerializator serializator = new RestaurantSerializator();
				// serializator.writeData(AdminGUI.this.restaurant, "restaurant.ser");
				AdminGUI.this.repaint();
				AdminGUI.this.revalidate();

			}

		});

	}

	void tableFiller(JTable tabel, ArrayList<MenuItem> m, DefaultTableModel modelTabel) {
		modelTabel.setColumnCount(3);
		String[] coloane = new String[3];
		coloane[0] = "ID";
		coloane[1] = "NUME";
		coloane[2] = "PRET";
		modelTabel.setColumnIdentifiers(coloane);

		tabel.setModel(modelTabel);
	}

}
