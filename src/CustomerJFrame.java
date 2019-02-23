import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerJFrame extends JFrame {


    private JButton cartButton = new JButton("Shopping Cart");
    private JButton wishButton = new JButton("Wish List");
    private JButton notificationButton = new JButton("Notifications");
    private JButton backButton = new JButton("Back");
    private JLabel iconLabel = new JLabel();
    private static final long serialVersionUID = 7;

    public CustomerJFrame(LayoutManager layoutManager, Dimension dimension, Store store, Customer customer, ImageIcon icon, ArrayList<Item> bought)
    {
        super("Customer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setLayout(layoutManager);
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setIconImage( new ImageIcon("icons/storeSwingIcon.png").getImage());
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        cartButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        wishButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        notificationButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        backButton.setFont(new Font("Times New Roman",Font.BOLD,12));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        iconLabel.setIcon(icon);
        this.add(iconLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(cartButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(wishButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(notificationButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(backButton, constraints);

        this.setVisible(true);
        pack();


        cartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                if(button.getText().equals(cartButton.getText()))
                {
                    new CartJFrame(new GridBagLayout(), new Dimension(800,800),store,customer,icon, bought);
                    dispose();
                }
            }
        });

        wishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                if(button.getText().equals(wishButton.getText()))
                {
                    new WishJFrame(new GridBagLayout(), new Dimension(800,800),store,customer,icon, bought);
                    dispose();
                }
            }
        });

        notificationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                if(button.getText().equals(notificationButton.getText()))
                {
                    new NotificationJFrame(new GridBagLayout(), new Dimension(800,800),store,customer,icon, bought);
                    dispose();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                if(button.getText().equals(backButton.getText()))
                {
                    dispose();
                    new UtilizatorJFrame(new GridBagLayout(), new Dimension(800,800), store,icon, bought);
                }
            }
        });
    }
}
