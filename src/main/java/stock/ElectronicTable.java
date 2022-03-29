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
import stock.db.UpdateData;

public class ElectronicTable {

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
	private JTextField txtPowerType;

	private DefaultTableModel model;

	private JPanel panel;

	private int selectedRowId;


	public ElectronicTable() {
		frame = new JFrame("Electronic Items");

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

		txtPowerType = new JTextField();
		txtPowerType.setBounds(135, 126, 182, 20);
		txtPowerType.setColumns(10);
		frame.getContentPane().add(txtPowerType);
		JLabel lblPowerType = new JLabel("Power Type:");
		lblPowerType.setBounds(56, 129, 89, 14);
		frame.getContentPane().add(lblPowerType);

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
		btnBack.setFont(new Font("Arial", Font.BOLD, 11)); // size
		btnBack.setBounds(420, 1, 70, 20);
		frame.getContentPane().add(btnBack);

		panel = new JPanel();

		String col[] = { "#ID","Name", "Description", "Count", "Power Type" };

		model = new DefaultTableModel(col, 0);
		ResultSet rs = FetchData.getElectronicDataForTable();
		try {
			while (rs.next()) {
				String id = rs.getString("electronic_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String count = rs.getString("count");
				String powerType = rs.getString("power_type");

				// create a single array of one row's worth of data
				String[] data = { id,name, description, count, powerType };

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
				selectedRowId =Integer.parseInt(model.getValueAt(update_row, 0).toString());
				txtName.setText(model.getValueAt(update_row, 1).toString());
				txtDescription.setText(model.getValueAt(update_row, 2).toString());
				txtCount.setText(model.getValueAt(update_row, 3).toString());
				txtPowerType.setText(model.getValueAt(update_row, 4).toString());
			}
		});

		// update table data
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtName.getText().equals("") &&
						txtDescription.getText().equals("") &&
						txtCount.getText().equals("") &&
						txtPowerType.getText().equals("")) {

					JOptionPane.showMessageDialog(frame, "Click Table raw to Update!");

				} else {

					int update_row = jTable1.getSelectedRow();
					if (!txtName.getText().equals("") &&
							!txtDescription.getText().equals("") &&
							!txtCount.getText().equals("") &&
							!txtPowerType.getText().equals("")) {
						if (update_row != -1) {
								//update database
								UpdateData updateData = new UpdateData();
								updateData.updateElectronic(selectedRowId, txtName.getText(), txtDescription.getText(), txtCount.getText(), txtPowerType.getText());
  								// update table
								model.setValueAt(txtName.getText(), update_row, 1);
								model.setValueAt(txtDescription.getText(), update_row, 2);
								model.setValueAt(txtCount.getText(), update_row, 3);
								model.setValueAt(txtPowerType.getText(), update_row, 4);
								JOptionPane.showMessageDialog(frame, "Successfully updated!");

								// clear txtFields
								txtName.setText("");
								txtDescription.setText("");
								txtCount.setText("");
								txtPowerType.setText("");
								update_row = -1;

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
							!txtPowerType.getText().equals("")) {

						AddData addData = new AddData();
						addData.addElectronic(txtName.getText(), txtDescription.getText(), txtCount.getText(),
								txtPowerType.getText());

						//update table
						model.addRow(new String[] { txtName.getText(), txtDescription.getText(),
								txtCount.getText(),
								txtPowerType.getText() });
						//clear input
						txtName.setText("");
						txtDescription.setText("");
						txtCount.setText("");
						txtPowerType.setText("");

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
						txtPowerType.getText().equals("")) {

					JOptionPane.showMessageDialog(frame, "Click Table raw to Delete!");

				} else {
					int selectedRow = jTable1.getSelectedRow();
					int answer = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this instance?",
							"An Inane Question", JOptionPane.YES_NO_OPTION);

					if (selectedRow >= 0 && answer == 0) {
						//delete from database
						DeleteData deleteData = new DeleteData();
						deleteData.deleteElectronic(selectedRowId);
						//remove row from table
						model.removeRow(selectedRow);
						txtName.setText("");
						txtDescription.setText("");
						txtCount.setText("");
						txtPowerType.setText("");
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
