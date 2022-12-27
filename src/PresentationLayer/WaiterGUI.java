package PresentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;
import dataLayer.RestaurantSerializator;

public class WaiterGUI extends JFrame {
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
	ArrayList<Order> comenzi = new ArrayList<Order>();

	public WaiterGUI(Restaurant restaurant) {

		this.restaurant = restaurant;
		Vector<MenuItem> vector = new Vector<MenuItem>();

		this.setTitle("Waiter");
		administratorPanel = new JPanel();
		administratorPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		administratorPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(508, 82, 437, 282);
		administratorPanel.add(scrollPane);

		itemsJList = new JList<>();
		JScrollPane itemsScrollPane = new JScrollPane(itemsJList);
		itemsScrollPane.setBounds(231, 82, 165, 116);
		administratorPanel.add(itemsScrollPane);
		setBounds(600, 100, 1012, 431);

		JButton btnNewButton = new JButton("Vizualizare");
		btnNewButton.setBounds(60, 50, 100, 23);
		administratorPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Adaugare Comanda");
		btnNewButton_1.setBounds(41, 96, 150, 23);
		administratorPanel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Chitanta");
		btnNewButton_2.setBounds(41, 144, 126, 23);
		administratorPanel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Meniu");
		btnNewButton_3.setBounds(41, 180, 123, 23);
		administratorPanel.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel("ID COMANDA");
		lblNewLabel.setBounds(25, 282, 620, 22);
		administratorPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(100, 279, 73, 20);
		administratorPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("NUMAR MASA");
		lblNewLabel_1.setBounds(193, 282, 225, 14);
		administratorPanel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(275, 279, 86, 20);
		administratorPanel.add(textField_1);
		textField_1.setColumns(10);

		setContentPane(this.administratorPanel);
		this.invalidate();
		this.validate();
		this.repaint();
		for (MenuItem m1 : restaurant.getProduse()) {
			vector.add(m1);
			itemsJList.setListData(vector);
		}
		setOrderJTable(table, model);
		table.setModel(model);

		this.setVisible(true);

		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Vector<MenuItem> vec = new Vector<MenuItem>();

				for (MenuItem m : restaurant.getProduse()) {
					vec.add(m);

				}

				WaiterGUI.this.itemsJList.setListData(vec);
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer orderId = Integer.parseInt(textField.getText());
					restaurant.Bill(orderId);
					JOptionPane.showMessageDialog(null, "Bill generated successfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 1;

				for (Entry<Order, ArrayList<MenuItem>> o : restaurant.getComenzi().entrySet()) {
					for (int i = 0; i < WaiterGUI.this.table.getRowCount(); i++) {
						if (WaiterGUI.this.table.getValueAt(i, 0).equals(o.getKey().getId_comanda())) {
							WaiterGUI.this.model.removeRow(i);
						}
					}
				}

				for (Entry<Order, ArrayList<MenuItem>> o : WaiterGUI.this.restaurant.getComenzi().entrySet()) {
					id++;
					o.getKey().setId_comanda(id);
					Object[] obj = new Object[] { o.getKey().getId_comanda(), o.getKey().getData(),
							o.getKey().getNumar_tabel(), restaurant.computePrice(o.getKey().getId_comanda()) };

					WaiterGUI.this.model.addRow(obj);
					WaiterGUI.this.table.setModel(model);

				}

			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					ArrayList<MenuItem> menuItemList = (ArrayList<MenuItem>) itemsJList.getSelectedValuesList();
					if (menuItemList.size() == 0) {
						throw new Exception("No item is selected");
					}
					Integer tableId = Integer.parseInt(textField_1.getText());

					comenzi.add(restaurant.creazaComanda(menuItemList, tableId));

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	private void setOrderJTable(JTable tabel, DefaultTableModel modelTabel) {
		modelTabel.setColumnCount(4);
		String[] coloane = new String[4];
		coloane[0] = "ID COMANDA";
		coloane[1] = "DATA";
		coloane[2] = "NUMAR MASA";
		coloane[3] = "PRET";
		modelTabel.setColumnIdentifiers(coloane);

		tabel.setModel(modelTabel);

	}
}
