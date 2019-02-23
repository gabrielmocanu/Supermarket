import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductJFrame extends JFrame {

    private JButton modifyButton = new JButton("Modify");
    private JButton cancelButton = new JButton("Cancel");
    private JLabel labelItemName = new JLabel("Name:");
    private JLabel labelItemId = new JLabel("Id");
    private JLabel labelItemPrice = new JLabel("Price");
    private JLabel noModify = new JLabel("Product doesn't exists");
    private JLabel noCorespondence = new JLabel("ID doesn't corespond with name");

    private JTextField textItemName = new JTextField(20);
    private JTextField textItemId = new JTextField(20);
    private JTextField textItemPrice = new JTextField(20);

    public ModifyProductJFrame(Department dep, int depId, TableModel tableModel) {
        super("Modify product");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400,400));
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        modifyButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        labelItemId.setFont(new Font("Times New Roman", Font.BOLD, 12));
        labelItemName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        labelItemPrice.setFont(new Font("Times New Roman", Font.BOLD, 12));
        noModify.setFont(new Font("Times New Roman", Font.BOLD, 12));
        noCorespondence.setFont(new Font("Times New Roman", Font.BOLD, 12));

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(labelItemName, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(labelItemId, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(labelItemPrice, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(textItemName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(textItemId, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(textItemPrice, constraints);


        constraints.gridx = 1;
        constraints.gridy = 3;
        this.add(modifyButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(cancelButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        noModify.setForeground(new Color(255, 0, 0));
        this.add(noModify, constraints);
        noModify.setVisible(false);

        constraints.gridx = 1;
        constraints.gridy = 5;
        noCorespondence.setForeground(new Color(255, 0, 0));
        this.add(noCorespondence, constraints);
        noCorespondence.setVisible(false);

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

        modifyButton.addActionListener(new ActionListener() { // Luam toate cazurile de introducere a datelor
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(modifyButton.getText())) {
                    if (textItemPrice.getText().length() == 0) {
                        noCorespondence.setText("Introduce price");
                        noCorespondence.setVisible(true);
                    } else {
                        if (textItemName.getText().length() == 0) {
                            Item item = dep.getListProducts().getItemId(Integer.parseInt(textItemId.getText()));
                            if (item == null) {
                                noModify.setVisible(true);
                            } else {
                                item.setPret(Float.parseFloat(textItemPrice.getText()));
                                Notification notification = new Notification(depId, Integer.parseInt(textItemId.getText()), NotificationType.MODIFY);
                                dep.NotifyPrice(notification, Float.parseFloat(textItemPrice.getText()));
                                noModify.setVisible(false);
                                tableModel.fireTableDataChanged();
                                dispose();
                            }
                        }
                        if (textItemId.getText().length() == 0) {
                            Item item = dep.getListProducts().getItemName(textItemName.getText());
                            if (item == null) {
                                noModify.setVisible(true);
                            } else {
                                item.setPret(Float.parseFloat(textItemPrice.getText()));
                                Notification notification = new Notification(depId, item.getId(), NotificationType.MODIFY);
                                dep.NotifyPrice(notification, Float.parseFloat(textItemPrice.getText()));
                                noModify.setVisible(false);
                                tableModel.fireTableDataChanged();
                                dispose();
                            }
                        }
                        if (textItemId.getText().length() != 0 && textItemName.getText().length() != 0) {
                            Item item = dep.getListProducts().getItemId(Integer.parseInt(textItemId.getText()));
                            if (item == null) {
                                noModify.setVisible(true);
                            } else {
                                if (item.getName().equals(textItemName.getText())) {
                                    item.setPret(Float.parseFloat(textItemPrice.getText()));
                                    Notification notification = new Notification(depId, Integer.parseInt(textItemId.getText()), NotificationType.MODIFY);
                                    dep.NotifyPrice(notification, Float.parseFloat(textItemPrice.getText()));
                                    noCorespondence.setVisible(false);
                                    tableModel.fireTableDataChanged();
                                    dispose();
                                } else {
                                    noCorespondence.setText("ID doesn't corespond with name");
                                    noCorespondence.setVisible(true);
                                }
                            }
                        }

                    }
                }
            }
        });

    }

}
