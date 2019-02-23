import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveItemJFrame extends JFrame {

    private JButton removeButton = new JButton("Remove");
    private JButton cancelButton = new JButton("Cancel");
    private JLabel labelItemName = new JLabel("Name:");
    private JLabel labelItemId = new JLabel("Id");
    private JLabel noCorespondence = new JLabel("ID doesn't corespond with name");

    private JTextField textItemName = new JTextField(20);
    private JTextField textItemId = new JTextField(20);

    public RemoveItemJFrame(Store store, Customer customer, TableModelCustomer tableModel, JLabel bugetDisponibil, JLabel bugetCos)
    {
        super("Remove product");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400,400));
        this.setMinimumSize(new Dimension(400,400));
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,0,10,0);

        removeButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
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
        this.add(removeButton, constraints);

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
                if(button.getText().equals(cancelButton.getText()))
                {
                    dispose();
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals(removeButton.getText()))
                {
                    if (textItemId.getText().length() == 0 && textItemName.getText().length() == 0) {
                        noCorespondence.setVisible(true);
                        noCorespondence.setText("This item doesn't exists.");
                    }

                    if(textItemName.getText().length() == 0 && textItemId.getText().length() != 0)
                    {
                        Item item = store.findItem(Integer.parseInt(textItemId.getText()));
                        if(item != null)
                        {
                            customer.getCart().remove(item);
                            tableModel.fireTableDataChanged();
                            dispose();
                            bugetDisponibil.setText("Available amount: " + (float)Math.round(customer.getCart().getBuget() * 100.0)/100.0);
                            bugetCos.setText("Cost products: " + (float)Math.round(customer.getCart().getPretProduse() * 100.0)/100.0);
                            noCorespondence.setVisible(false);
                        }
                        else
                        {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }
                    }
                    if(textItemId.getText().length() == 0 && textItemName.getText().length() != 0)
                    {
                        Item item = store.findItemByName(textItemName.getText());
                        if(item != null)
                        {
                            customer.getCart().remove(item);
                            tableModel.fireTableDataChanged();
                            dispose();
                            bugetDisponibil.setText("Available amount: " + (float)Math.round(customer.getCart().getBuget() * 100.0)/100.0);
                            bugetCos.setText("Cost products: " + (float)Math.round(customer.getCart().getPretProduse() * 100.0)/100.0);
                        }
                        else
                        {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }
                    }

                    if(textItemId.getText().length() != 0 && textItemName.getText().length() != 0)
                    {
                        Item item = store.findItem(Integer.parseInt(textItemId.getText()));
                        if(item != null)
                        {
                            if(item.getName().equals(textItemName.getText()))
                            {
                                customer.getCart().remove(item);
                                tableModel.fireTableDataChanged();
                                dispose();
                                bugetDisponibil.setText("Available amount: " + (float)Math.round(customer.getCart().getBuget() * 100.0)/100.0);
                                bugetCos.setText("Cost products: " + (float)Math.round(customer.getCart().getPretProduse() * 100.0)/100.0);
                            }
                            else
                            {
                                noCorespondence.setVisible(true);
                                noCorespondence.setText("ID doesn't corespond with name.");
                            }
                        }
                        else
                        {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }
                    }

                }
            }
        });

    }
    public RemoveItemJFrame(Store store, Customer customer, TableModelCustomer tableModel)
    {
        super("Remove product");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400,400));
        this.setMinimumSize(new Dimension(400,400));
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setLocationRelativeTo(null);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,0,10,0);

        removeButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
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
        this.add(removeButton, constraints);

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
                if(button.getText().equals(cancelButton.getText()))
                {
                    dispose();
                }
            }
        });

        removeButton.addActionListener(new ActionListener() { // Toate cazurile de introducere a datelor
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals(removeButton.getText()))
                {
                    if (textItemId.getText().length() == 0 && textItemName.getText().length() == 0) {
                        noCorespondence.setVisible(true);
                        noCorespondence.setText("This item doesn't exists.");
                    }

                    if(textItemName.getText().length() == 0 && textItemId.getText().length() != 0)
                    {
                        Item item = store.findItem(Integer.parseInt(textItemId.getText()));
                        if(item != null)
                        {
                            Department dep = store.findDeparmentByItem(item.getId());
                            customer.getWishList().remove(item);
                            if (!customer.getWishList().findItemByDepartment(dep))
                                dep.removeObserver(customer);
                            tableModel.fireTableDataChanged();
                            dispose();
                        }
                        else
                        {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }

                    }
                    if(textItemId.getText().length() == 0 && textItemName.getText().length() != 0)
                    {
                        Item item = store.findItemByName(textItemName.getText());
                        if(item != null)
                        {
                            Department dep = store.findDeparmentByItem(item.getId());
                            customer.getWishList().remove(item);
                            if (!customer.getWishList().findItemByDepartment(dep))
                                dep.removeObserver(customer);
                            tableModel.fireTableDataChanged();
                            dispose();
                        }
                        else
                        {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }
                    }

                    if(textItemId.getText().length() != 0 && textItemName.getText().length() != 0)
                    {
                        Item item = store.findItem(Integer.parseInt(textItemId.getText()));
                        if(item != null)
                        {
                            if(item.getName().equals(textItemName.getText()))
                            {
                                Department dep = store.findDeparmentByItem(item.getId());
                                customer.getWishList().remove(item);
                                if (!customer.getWishList().findItemByDepartment(dep))
                                    dep.removeObserver(customer);
                                tableModel.fireTableDataChanged();
                                dispose();
                            }
                            else
                            {
                                noCorespondence.setVisible(true);
                                noCorespondence.setText("ID doesn't corespond with name.");
                            }
                        }
                        else
                        {
                            noCorespondence.setVisible(true);
                            noCorespondence.setText("This item doesn't exists.");
                        }
                    }



                }
            }
        });

    }

}
