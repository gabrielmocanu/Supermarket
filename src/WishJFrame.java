import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Comparator;


public class WishJFrame extends JFrame {

    private JButton addProduct = new JButton("Add product");
    private JButton removeProduct = new JButton("Remove product");
    private JButton backButton = new JButton("Back");
    private JPanel buttons = new JPanel(new GridBagLayout());
    private JTable table;
    private static final long serialVersionUID = 9;

    public WishJFrame(LayoutManager layoutManager, Dimension dimension, Store store, Customer customer, ImageIcon icon, ArrayList<Item> bought)
    {
        super("WishList");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setLayout(layoutManager);
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setIconImage( new ImageIcon("icons/storeSwingIcon.png").getImage());
        this.setLocationRelativeTo(null);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,0,10,0);

        addProduct.setFont(new Font("Times New Roman",Font.BOLD,12));
        removeProduct.setFont(new Font("Times New Roman",Font.BOLD,12));
        backButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        buttons.setFont(new Font("Times New Roman",Font.BOLD,12));

        constraints.gridx = 9;
        constraints.gridy = 15;
        buttons.add(addProduct,constraints);

        constraints.gridx = 9;
        constraints.gridy = 17;
        buttons.add(removeProduct,constraints);

        constraints.gridx = 9;
        constraints.gridy = 18;
        buttons.add(backButton,constraints);

        constraints.gridx = 9;
        constraints.gridy = 10;

        buttons.setBackground(new Color(32, 156, 238));
        this.add(buttons, constraints);
        this.setVisible(true);
        pack();



        TableModelCustomer tableModel = new TableModelCustomer(customer.getWishList());
        table = new JTable(tableModel);
        TableRowSorter<TableModel> sortTable = new TableRowSorter(table.getModel());
        table.setRowSorter(sortTable);

        sortTable.setComparator(0, new Comparator<String>() {
            public int compare(String name1, String name2) {
                return name1.compareTo(name2);
            }
        });

        sortTable.setComparator(1, new Comparator<Integer>() {
            public int compare(Integer id1, Integer id2) {
                if(id2 > id1)
                    return 1;
                else
                    return -1;
            }
        });

        sortTable.setComparator(2, new Comparator<Float>() {
            public int compare(Float price1, Float price2) {
                if(price2 > price1)
                    return 1;
                else
                    return -1;
            }
        });

        sortTable.sort();

        constraints.gridx = 10;
        constraints.gridy = 10;
        add(new JScrollPane(table),constraints);


        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals((addProduct.getText())))
                {
                    new AddItemJFrame(store, customer, tableModel);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals(backButton.getText()))
                {
                    setVisible(false);
                    new CustomerJFrame(layoutManager, dimension, store, customer, icon, bought);
                    dispose();
                }
            }
        });

        removeProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals(removeProduct.getText()))
                {
                    new RemoveItemJFrame(store, customer, tableModel);
                }
            }
        });

    }
}
