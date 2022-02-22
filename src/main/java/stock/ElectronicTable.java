package stock;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import javax.swing.table.*;

public class ElectronicTable {

	private JFrame frame;
	private JScrollPane pane;
	private JTable jTable1;
	private JTableHeader header;

	private JButton btnAdd;
        private JButton btnBack;
	// private JButton btnDelete;
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
		lblPowerType.setBounds(56, 129, 69, 14);
		frame.getContentPane().add(lblPowerType);

		btnAdd = new JButton("Add");
		btnAdd.setBackground(Color.GREEN);
		btnAdd.setBounds(327, 33, 130, 31);
		frame.getContentPane().add(btnAdd);

		// btnDelete = new JButton("Delete selected");
		// btnDelete.setBackground(Color.ORANGE);
		// btnDelete.setBounds(327, 74, 130, 31);
		// frame.getContentPane().add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(327, 115, 130, 31);
		frame.getContentPane().add(btnUpdate);
                
                btnBack = new JButton("Back");
//		btnBack.setBackground(Color.GRAY);
                btnBack.setFont(new Font("Arial", Font.BOLD, 20)); // size 
		btnBack.setBounds(450,1, 40, 20);
		frame.getContentPane().add(btnBack);

		panel = new JPanel();

		String col[] = { "Id", "Name", "Description", "Count", "Power Type" };

		model = new DefaultTableModel(col, 0);
		for (String[] row : ItemList.getElectronicDataForTable()) {
			model.addRow(row);
		}

		jTable1 = new JTable();

		jTable1.setModel(model);

		header = jTable1.getTableHeader();
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
				try {
					selectedRowId = Integer.parseInt((String) model.getValueAt(update_row, 0));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, "something went wrong!");
				}
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
						txtPowerType.getText().equals("")){
                            
                                JOptionPane.showMessageDialog(frame, "Click Table raw to Upadte!");
                            
                            }else{
                            
                            
				int update_row = jTable1.getSelectedRow();
				if (!txtName.getText().equals("") &&
						!txtDescription.getText().equals("") &&
						!txtCount.getText().equals("") &&
						!txtPowerType.getText().equals("")) {
					if (update_row != -1) {
						try {
							//update itemList state
							int quantity = Integer.parseInt(txtCount.getText());
							ItemList.getElectronics().get(selectedRowId-1).setCount(quantity);
							ItemList.getElectronics().get(selectedRowId-1).setName(txtName.getText());
							ItemList.getElectronics().get(selectedRowId-1).setDescription(txtDescription.getText());
							ItemList.getElectronics().get(selectedRowId-1).setPowerType(txtPowerType.getText());
						
							//update table
							model.setValueAt(txtName.getText(), update_row, 1);
							model.setValueAt(txtDescription.getText(), update_row, 2);
							if (!txtCount.getText().matches("([0-9]*){1,20}")) {
								JOptionPane.showMessageDialog(frame, "count value must be numeric!");
							} else {
								model.setValueAt(txtCount.getText(), update_row, 3);
							}
							model.setValueAt(txtPowerType.getText(), update_row, 4);
							JOptionPane.showMessageDialog(frame, "Successfully added!");

							// clear txtFields
							txtName.setText("");
							txtDescription.setText("");
							txtCount.setText("");
							txtPowerType.setText("");
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

				int lengthOfItemList = ItemList.getElectronics().size();
				String strIndex = Integer.toString(lengthOfItemList + 1);

				if (!txtCount.getText().matches("([0-9]*){1,20}")) {
					JOptionPane.showMessageDialog(frame, "count value must be numeric!");
				} else {
					if (!txtName.getText().equals("") &&
							!txtDescription.getText().equals("") &&
							!txtCount.getText().equals("") &&
							!txtPowerType.getText().equals("")) {

						try {
							// update ItemList state
							int quantity = Integer.parseInt(txtCount.getText());
							Electronic electronic = new Electronic(quantity, txtName.getText(), txtDescription.getText(),
									txtPowerType.getText());
							ItemList.getElectronics().add(electronic);

							// update Table
							model.addRow(new String[] { strIndex, txtName.getText(), txtDescription.getText(),
									txtCount.getText(),
									txtPowerType.getText() });

							JOptionPane.showMessageDialog(frame, "Successfully added!");

							// clear txtFields
							txtName.setText("");
							txtDescription.setText("");
							txtCount.setText("");
							txtPowerType.setText("");

						} catch (Exception e) {
							JOptionPane.showMessageDialog(frame, e.getMessage());
						}

					} else {
						JOptionPane.showMessageDialog(frame, "All texts must be filled!");
					}
				}
			}
		});
                
                

		// delete data
	// 	btnDelete.addActionListener(new ActionListener() {
	// 		public void actionPerformed(ActionEvent arg0) {

	// 			int selectedRow = jTable1.getSelectedRow();

	// 			int answer = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this instance?",
	// 					"An Inane Question", JOptionPane.YES_NO_OPTION);

	// 			if (selectedRow >= 0 && answer == 0) {
	// 				model.removeRow(selectedRow);
	// 				txtName.setText("");
	// 				txtName.setText("");
	// 				txtCount.setText("");
	// 				txtPowerType.setText("");
	// 				txtMath.setText("");
	// 				txtMarks.setText("");
	// 				txtGrade.setText("");
	// 			}
	// 		}
	// 	});
        
        btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        new StockFrame().setVisible(true);
                        
                        }
        
        });


	}
}
