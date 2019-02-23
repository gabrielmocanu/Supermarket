import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemJFrame extends JFrame {

    private JButton addButton = new JButton("Add");
    private JButton cancelButton = new JButton("Cancel");
    private JLabel labelItemName = new JLabel("Name:");
    private JLabel labelItemId = new JLabel("Id:");
    private JLabel noCorespondence = new JLabel("ID doesn't corespond with name");
    private static final long serialVersionUID = 1;

    private JTextField textItemName = new JTextField(20);
    private JTextField textItemId = new JTextField(20);

    public AddItemJFrame(Store store, Customer customer, TableModelCustomer tableModel, JLabel bugetDisponibil, JLabel bugetCos) { // Constructor in cazul in care adaugam in ShoppingCart
        super("Add product");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400,400));
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        addButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        labelItemId.setFont(new Font("Times New Roman", Font.BOLD, 12));
        labelItemName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        noCorespondence.setFont(new Font("Times New Roman", Font.BOLD, 12));

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(labelItemName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(labelItemId, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(textItemName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(textItemId, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(addButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        this.add(cancelButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(noCorespondence, constraints);
        noCorespondence.setVisible(false);
        noCorespondence.setForeground(new Color(255, 0, 0));

        setVisible(true);
        pack();

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(cancelButton.getText())) {
                    dispose();
                }
            }
        });

        addButton.addActionListener(new ActionListener() { // Luam toate cazurile de introducere a datelor
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(addButton.getText())) {
                    if (textItemId.getText().length() == 0 && textItemName.getText().length() == 0) {
                        noCorespondence.setVisible(true);
                        noCorespondence.setText("This item doesn't exists.");
                    }
                    if (textItemId.getText().length() == 0 && textItemName.getText().length() != 0) {
                        Item itemName = store.findItemByName(textItemName.getText());
                        if (itemName != null) {
                            Department dep = store.findDeparmentByItem(itemName.getId());
                            customer.getCart().add(new Item(itemName.getName(), itemName.getId(), itemName.getPret(), dep.getId()));
                            tableModel.fireTableDataChanged();
                            dispose();
                            bugetDisponibil.setText("Available amount: " + (float)Math.round(customer.getCart().getBuget() * 100.0)/100.0);
                            bugetCos.setText("Cost products: " + (float)Math.round(customer.getCart().getPretProduse() * 100.0)/100.0);
                            noCorespondence.setVisible(false);
                        } else {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }
                    }
                    if (textItemId.getText().length() != 0 && textItemName.getText().length() == 0) {
                        Item item = store.findItem(Integer.parseInt(textItemId.getText()));
                        if (item != null) {
                            Department dep = store.findDeparmentByItem(Integer.parseInt(textItemId.getText()));
                            customer.getCart().add(new Item(item.getName(), item.getId(), item.getPret(), dep.getId()));
                            tableModel.fireTableDataChanged();
                            dispose();
                            bugetDisponibil.setText("Available amount: " + (float)Math.round(customer.getCart().getBuget() * 100.0)/100.0);
                            bugetCos.setText("Cost products: " + (float)Math.round(customer.getCart().getPretProduse() * 100.0)/100.0);
                            noCorespondence.setVisible(false);

                        } else {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }
                    }
                    if (textItemId.getText().length() != 0 && textItemName.getText().length() != 0) {
                        Item item = store.findItem(Integer.parseInt(textItemId.getText()));
                        if (item != null) {
                            Department dep = store.findDeparmentByItem(Integer.parseInt(textItemId.getText()));
                            if (item.getName().equals(textItemName.getText())) {
                                customer.getCart().add(new Item(item.getName(), item.getId(), item.getPret(), dep.getId()));
                                tableModel.fireTableDataChanged();
                                dispose();
                                bugetDisponibil.setText("Available amount: " + (float)Math.round(customer.getCart().getBuget() * 100.0)/100.0);
                                bugetCos.setText("Cost products: " + (float)Math.round(customer.getCart().getPretProduse() * 100.0)/100.0);
                                noCorespondence.setVisible(false);
                            } else {
                                noCorespondence.setVisible(true);
                                noCorespondence.setText("ID doesn't corespond with name.");
                            }
                        } else {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }

                    }
                }
            }
        });

    }

    public AddItemJFrame(Store store, Customer customer, TableModelCustomer tableModel) { // constructor in cazul in care adaugam in WishList
        super("Add product");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400,400));
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        addButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        labelItemId.setFont(new Font("Times New Roman", Font.BOLD, 12));
        labelItemName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        noCorespondence.setFont(new Font("Times New Roman", Font.BOLD, 12));

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(labelItemName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(labelItemId, constraints);


        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(textItemName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(textItemId, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(addButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        this.add(cancelButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(noCorespondence, constraints);
        noCorespondence.setVisible(false);
        noCorespondence.setForeground(new Color(255, 0, 0));

        setVisible(true);
        pack();

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(cancelButton.getText())) {
                    dispose();
                }
            }
        });

        addButton.addActionListener(new ActionListener() { // Luam toate cazurile de introducere a datelor
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(addButton.getText())) {
                    if (textItemId.getText().length() == 0 && textItemName.getText().length() == 0) {
                        noCorespondence.setVisible(true);
                        noCorespondence.setText("This item doesn't exists.");
                    }
                    if (textItemId.getText().length() == 0 && textItemName.getText().length() != 0) {
                        Item itemName = store.findItemByName(textItemName.getText());
                        if (itemName != null) {
                            Department dep = store.findDeparmentByItem(itemName.getId());
                            customer.getWishList().add(new Item(itemName.getName(), itemName.getId(), itemName.getPret(), dep.getId()));
                            dep.addObserver(customer);
                            tableModel.fireTableDataChanged();
                            dispose();
                            noCorespondence.setVisible(false);
                        } else {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }
                    }
                    if (textItemId.getText().length() != 0 && textItemName.getText().length() == 0) {
                        Item item = store.findItem(Integer.parseInt(textItemId.getText()));
                        if (item != null) {
                            Department dep = store.findDeparmentByItem(Integer.parseInt(textItemId.getText()));
                            customer.getWishList().add(new Item(item.getName(), item.getId(), item.getPret(), dep.getId()));
                            dep.addObserver(customer);
                            tableModel.fireTableDataChanged();
                            dispose();
                            noCorespondence.setVisible(false);
                        } else {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }
                    }
                    if (textItemId.getText().length() != 0 && textItemName.getText().length() != 0) {
                        Item item = store.findItem(Integer.parseInt(textItemId.getText()));
                        if (item != null) {
                            Department dep = store.findDeparmentByItem(Integer.parseInt(textItemId.getText()));
                            if (item.getName().equals(textItemName.getText())) {
                                customer.getWishList().add(new Item(item.getName(), item.getId(), item.getPret(), dep.getId()));
                                dep.addObserver(customer);
                                tableModel.fireTableDataChanged();
                                dispose();
                                noCorespondence.setVisible(false);
                            } else {
                                noCorespondence.setVisible(true);
                                noCorespondence.setText("ID doesn't corespond with name.");
                            }
                        } else {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }

                    }
                }
            }
        });

    }

}
