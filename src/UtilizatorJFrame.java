import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UtilizatorJFrame extends JFrame {

    private JButton buttonView = new JButton("View");
    private JButton buttonBack = new JButton("Back");
    private JLabel iconLabel = new JLabel();
    private JList<Customer> customerJList;
    private static final long serialVersionUID = 10;

    public UtilizatorJFrame(LayoutManager layoutManager, Dimension dimension, Store store, ImageIcon icon, ArrayList<Item> bought)
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

        buttonView.setFont(new Font("Times New Roman",Font.BOLD,12));
        buttonBack.setFont(new Font("Times New Roman",Font.BOLD,12));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        iconLabel.setIcon(icon);
        this.add(iconLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(buttonBack,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(buttonView,constraints);


        DefaultListModel<Customer> listModel = new DefaultListModel<Customer>();
        for(int i = 0;i < store.getCustomers().size(); i++)
        {
            listModel.addElement(store.getCustomers().get(i));
        }
        customerJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(customerJList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(scrollPane,constraints);

        this.setVisible(true);
        pack();


        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals(buttonBack.getText()))
                {
                    setVisible(false);
                    new AppStart(store,bought);
                }
            }
        });

        buttonView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals(buttonView.getText()))
                {
                    Customer customer = customerJList.getSelectedValue();
                    if(customer != null)
                    {
                        new CustomerJFrame(new GridBagLayout(), new Dimension(800,800), store, customer,icon, bought);
                        dispose();
                    }
                }
            }
        });


    }
}
