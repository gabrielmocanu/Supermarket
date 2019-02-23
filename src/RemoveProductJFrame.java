import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveProductJFrame extends JFrame {

    private JButton removeButton = new JButton("Remove");
    private JButton cancelButton = new JButton("Cancel");
    private JLabel labelItemName = new JLabel("Name:");
    private JLabel labelItemId = new JLabel("Id");
    private JLabel noRemove = new JLabel("Product doesn't exists");
    private JLabel noCorespondence = new JLabel("ID doesn't corespond with name");


    private JTextField textItemName = new JTextField(20);
    private JTextField textItemId = new JTextField(20);

    public RemoveProductJFrame(Department dep, int depId, TableModel tableModel)
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
        noRemove.setFont(new Font("Times New Roman", Font.BOLD, 12));
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
        constraints.gridy = 3;
        this.add(removeButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(cancelButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        noRemove.setForeground(new Color(255,0,0));
        this.add(noRemove, constraints);
        noRemove.setVisible(false);

        constraints.gridx = 1;
        constraints.gridy = 5;
        noCorespondence.setForeground(new Color(255,0,0));
        this.add(noCorespondence, constraints);
        noCorespondence.setVisible(false);

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
                    if(textItemName.getText().length() == 0)
                    {
                        Item item = dep.getListProducts().getItemId(Integer.parseInt(textItemId.getText()));
                        if(item == null)
                        {
                            noRemove.setVisible(true);
                            noCorespondence.setVisible(false);
                        }
                        else
                        {
                            dep.getListProducts().remove(item);
                            Notification notification = new Notification(depId,Integer.parseInt(textItemId.getText()),NotificationType.REMOVE);
                            dep.NotifyRemove(notification);
                            noRemove.setVisible(false);
                            tableModel.fireTableDataChanged();
                            dispose();
                        }
                    }
                    if(textItemId.getText().length() == 0)
                    {
                        Item item = dep.getListProducts().getItemName(textItemName.getText());
                        if(item == null)
                        {
                            noRemove.setVisible(true);
                            noCorespondence.setVisible(false);
                        }
                        else
                        {
                            dep.getListProducts().remove(item);
                            Notification notification = new Notification(depId,item.getId(),NotificationType.REMOVE);
                            dep.NotifyRemove(notification);
                            noRemove.setVisible(false);
                            tableModel.fireTableDataChanged();
                            dispose();
                        }
                    }
                    if(textItemId.getText().length() != 0 && textItemId.getText().length() != 0)
                    {
                        Item item = dep.getListProducts().getItemId(Integer.parseInt(textItemId.getText()));
                        if(item == null)
                        {
                            noRemove.setVisible(true);
                            noCorespondence.setVisible(false);
                        }
                        else
                        {
                            if(item.getName().equals(textItemName.getText()))
                            {
                                dep.getListProducts().remove(item);
                                Notification notification = new Notification(depId,Integer.parseInt(textItemId.getText()),NotificationType.MODIFY);
                                dep.NotifyRemove(notification);
                                noCorespondence.setVisible(false);
                                tableModel.fireTableDataChanged();
                                dispose();
                            }
                            else
                            {
                                noCorespondence.setVisible(true);
                            }
                        }
                    }

                }
            }
        });

    }

}
