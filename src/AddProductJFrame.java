import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductJFrame extends JFrame {

    private JButton addButton = new JButton("Add");
    private JButton cancelButton = new JButton("Cancel");
    private JLabel labelItemName = new JLabel("Name:");
    private JLabel labelItemId = new JLabel("Id");
    private JLabel labelItemPrice = new JLabel("Price");
    private JLabel notAdd = new JLabel("This product already exists");
    private static final long serialVersionUID = 3;

    private JTextField textItemName = new JTextField(20);
    private JTextField textItemId = new JTextField(20);
    private JTextField textItemPrice = new JTextField(20);

    public AddProductJFrame(Store store, Department dep, int depId, TableModel tableModel) {
        super("Add product");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400,400));
        this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setLocationRelativeTo(null);

        addButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        cancelButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        labelItemName.setFont(new Font("Times New Roman",Font.BOLD,12));
        labelItemId.setFont(new Font("Times New Roman",Font.BOLD,12));
        labelItemPrice.setFont(new Font("Times New Roman",Font.BOLD,12));
        notAdd.setFont(new Font("Times New Roman",Font.BOLD,12));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

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
        this.add(addButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(cancelButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        this.add(notAdd, constraints);
        notAdd.setForeground(new Color(255, 0, 0));
        notAdd.setVisible(false);

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

        addButton.addActionListener(new ActionListener() { // Luam toate cazurile posibile de introducere a datelor
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(addButton.getText())) {
                    boolean empty = false;
                    if (textItemName.getText().length() == 0) {
                        notAdd.setText("Introduce name");
                        notAdd.setVisible(true);
                        empty = true;
                    }
                    if (textItemId.getText().length() == 0) {
                        notAdd.setText("Introduce ID");
                        notAdd.setVisible(true);
                        empty = true;
                    }
                    if (textItemPrice.getText().length() == 0) {
                        notAdd.setText("Introduce price");
                        notAdd.setVisible(true);
                        empty = true;
                    }
                    if (!empty) {
                        boolean ok = true;
                        for(int i = 0; i < 4; i++)
                        {
                            if(store.getDepartment(i).getListProducts().contains(Integer.parseInt(textItemId.getText())))
                                ok = false;
                        }
                        if (!ok) {
                            notAdd.setText("This product already exists");
                            notAdd.setVisible(true);
                        } else {
                            dep.getListProducts().add(new Item(textItemName.getText(), Integer.parseInt(textItemId.getText()), Float.parseFloat(textItemPrice.getText()), depId));
                            Notification notification = new Notification(depId, Integer.parseInt(textItemId.getText()), NotificationType.ADD);
                            dep.NotifyAdd(notification);
                            notAdd.setVisible(false);
                            tableModel.fireTableDataChanged();
                            dispose();
                        }
                    }

                }
            }
        });

    }

}
