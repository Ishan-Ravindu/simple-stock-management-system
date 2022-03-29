package stock;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import javax.swing.table.*;

import stock.db.AddData;
import stock.db.DeleteData;
import stock.db.FetchData;

public class FurnitureTable {

	private JFrame frame;
	private JScrollPane pane;
	private JTable jTable1;
	private JButton btnAdd;
	private JButton btnBack;
	private JButton btnDelete;
	private JButton btnUpdate;

	private JTextField txtName;
	private JTextField txtDescription;
	private JTextField txtCount;
	private JTextField txtMaterial;

	private DefaultTableModel model;

	private JPanel panel;

	private int selectedRowId;

	public FurnitureTable() {
		frame = new JFrame("Furniture Items");

		txtName = new JTextField();
		txtName.setBounds(135, 33, 182, 20);
		txtName.setColumns(10);
		frame.getContentPane().add(txtName);
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(56, 36, 46, 14);
		frame.getContentPane().add(lblName);

		txtDescription = new JTextField();
		txtDescription.setBounds(135, 64, 182, 20);
		txtDescription.setColumns(10);
		frame.getContentPane().add(txtDescription);
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(56, 67, 69, 14);
		frame.getContentPane().add(lblDescription);

		txtCount = new JTextField();
		txtCount.setBounds(135, 95, 182, 20);
		txtCount.setColumns(10);
		frame.getContentPane().add(txtCount);
		JLabel lblCount = new JLabel("Count:");
		lblCount.setBounds(56, 98, 69, 14);
		frame.getContentPane().add(lblCount);

		txtMaterial = new JTextField();
		txtMaterial.setBounds(135, 126, 182, 20);
		txtMaterial.setColumns(10);
		frame.getContentPane().add(txtMaterial);
		JLabel lblMaterial = new JLabel("Material:");
		lblMaterial.setBounds(56, 129, 69, 14);
		frame.getContentPane().add(lblMaterial);

		btnAdd = new JButton("Add");
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(327, 33, 130, 31);
		frame.getContentPane().add(btnAdd);

		btnDelete = new JButton("Delete selected");
		btnDelete.setBackground(Color.ORANGE);
		btnDelete.setBounds(327, 74, 130, 31);
		frame.getContentPane().add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(327, 115, 130, 31);
		frame.getContentPane().add(btnUpdate);

		btnBack = new JButton("Back");
		// btnBack.setBackground(Color.GRAY);
		btnBack.setFont(new Font("Arial", Font.BOLD, 11)); // size
		btnBack.setBounds(420, 1, 70, 20);
		frame.getContentPane().add(btnBack);

		panel = new JPanel();

		String col[] = { "#ID", "Name", "Description", "Count", "Material" };

		model = new DefaultTableModel(col, 0);
		ResultSet rs = FetchData.getFurnitureDataForTable();
		try {
			while (rs.next()) {
				String id = rs.getString("furniture_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String count = rs.getString("count");
				String material = rs.getString("material");

				// create a single array of one row's worth of data
				String[] data = { id, name, description, count, material };

				// and add this row of data into the table model
				model.addRow(data);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		jTable1 = new JTable();

		jTable1.setModel(model);

		jTable1.getTableHeader();
		panel.setLayout(null);

		pane = new JScrollPane(jTable1);
		pane.setBounds(24, 271, 452, 444);
		jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panel.add(pane);
		frame.getContentPane().add(panel);

		frame.setSize(500, 750);
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// table row click
		jTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int update_row = jTable1.rowAtPoint(evt.getPoint());
				selectedRowId = Integer.parseInt(model.getValueAt(update_row, 0).toString());
				txtName.setText(model.getValueAt(update_row, 1).toString());
				txtDescription.setText(model.getValueAt(update_row, 2).toString());
				txtCount.setText(model.getValueAt(update_row, 3).toString());
				txtMaterial.setText(model.getValueAt(update_row, 4).toString());
			}
		});

		// update table data
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtName.getText().equals("") &&
						txtDescription.getText().equals("") &&
						txtCount.getText().equals("") &&
						txtMaterial.getText().equals("")) {

					JOptionPane.showMessageDialog(frame, "Click Table raw to Upadte!");

				} else {

					int update_row = jTable1.getSelectedRow();
					if (!txtName.getText().equals("") &&
							!txtDescription.getText().equals("") &&
							!txtCount.getText().equals("") &&
							!txtMaterial.getText().equals("")) {
						if (update_row != -1) {
							try {
								// update itemList state
								int quantity = Integer.parseInt(txtCount.getText());
								ItemList.getFurnitures().get(update_row).setCount(quantity);
								ItemList.getFurnitures().get(update_row).setName(txtName.getText());
								ItemList.getFurnitures().get(update_row)
										.setDescription(txtDescription.getText());
								ItemList.getFurnitures().get(update_row).setMaterial(txtMaterial.getText());

								// update table
								model.setValueAt(txtName.getText(), update_row, 0);
								model.setValueAt(txtDescription.getText(), update_row, 1);
								if (!txtCount.getText().matches("([0-9]*){1,20}")) {
									JOptionPane.showMessageDialog(frame, "count value must be numeric!");
								} else {
									model.setValueAt(txtCount.getText(), update_row, 2);
								}
								model.setValueAt(txtMaterial.getText(), update_row, 3);
								JOptionPane.showMessageDialog(frame, "Successfully added!");

								// clear txtFields
								txtName.setText("");
								txtDescription.setText("");
								txtCount.setText("");
								txtMaterial.setText("");
								update_row = -1;

							} catch (Exception er) {
								JOptionPane.showMessageDialog(frame, er.getMessage());
							}

						}
					} else {
						JOptionPane.showMessageDialog(frame, "All texts must be filled!");
					}

				}
			}
		});

		// add table data
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!txtCount.getText().matches("([0-9]*){1,20}")) {
					JOptionPane.showMessageDialog(frame, "count value must be numeric!");
				} else {
					if (!txtName.getText().equals("") &&
							!txtDescription.getText().equals("") &&
							!txtCount.getText().equals("") &&
							!txtMaterial.getText().equals("")) {

						AddData addData = new AddData();
						addData.addFurniture(txtName.getText(), txtDescription.getText(), txtCount.getText(),
								txtMaterial.getText());

						// update table
						model.addRow(new String[] { txtName.getText(), txtDescription.getText(),
								txtCount.getText(),
								txtMaterial.getText() });
						// clear input
						txtName.setText("");
						txtDescription.setText("");
						txtCount.setText("");
						txtMaterial.setText("");

						JOptionPane.showMessageDialog(frame, "Data added successfully!");

					} else {
						JOptionPane.showMessageDialog(frame, "All texts must be filled!");
					}
				}
			}
		});

		// delete data
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtName.getText().equals("") &&
						txtDescription.getText().equals("") &&
						txtCount.getText().equals("") &&
						txtMaterial.getText().equals("")) {

					JOptionPane.showMessageDialog(frame, "Click Table raw to Delete!");

				} else {
					int selectedRow = jTable1.getSelectedRow();

					int answer = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this instance?",
							"An Inane Question", JOptionPane.YES_NO_OPTION);

					if (selectedRow >= 0 && answer == 0) {
						// delete from database
						DeleteData deleteData = new DeleteData();
						deleteData.deleteFurniture(selectedRowId);
						// remove row from table
						model.removeRow(selectedRow);
						txtName.setText("");
						txtDescription.setText("");
						txtCount.setText("");
						txtMaterial.setText("");

					}
				}

			}
		});

		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new StockFrame().setVisible(true);

			}

		});
	}

}
