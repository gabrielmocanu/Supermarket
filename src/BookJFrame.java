import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Comparator;

public class BookJFrame extends JFrame {

    private JButton addProduct = new JButton("Add product");
    private JButton modifyProduct = new JButton("Modify product");
    private JButton removeProduct = new JButton("Remove product");
    private JButton statisticsButton = new JButton("Statistics");
    private JButton backButton = new JButton("Back");
    private JPanel buttons = new JPanel(new GridBagLayout());
    private JTable table;


    public BookJFrame(LayoutManager layoutManager, Dimension dimension, Store store, ImageIcon icon, ArrayList<Item> bought) {
        super("Book department");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setLayout(layoutManager);
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setIconImage(new ImageIcon("icons/storeSwingIcon.png").getImage());
        buttons.setBackground(new Color(32, 156, 238));
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        addProduct.setFont(new Font("Times New Roman", Font.BOLD, 12));
        modifyProduct.setFont(new Font("Times New Roman", Font.BOLD, 12));
        removeProduct.setFont(new Font("Times New Roman", Font.BOLD, 12));
        statisticsButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 12));

        constraints.gridx = 9;
        constraints.gridy = 15;
        buttons.add(addProduct, constraints);

        constraints.gridx = 9;
        constraints.gridy = 16;
        buttons.add(modifyProduct, constraints);

        constraints.gridx = 9;
        constraints.gridy = 17;
        buttons.add(removeProduct, constraints);

        constraints.gridx = 9;
        constraints.gridy = 18;
        buttons.add(statisticsButton, constraints);

        constraints.gridx = 9;
        constraints.gridy = 19;
        buttons.add(backButton, constraints);

        constraints.gridx = 9;
        constraints.gridy = 10;
        buttons.setBackground(new Color(32, 156, 238));
        this.add(buttons, constraints);
        this.setVisible(true);
        pack();


        TableModel tableModel = new TableModel(store.getDepartment(1).getListProducts());
        table = new JTable(tableModel);
        TableRowSorter sortTable = new TableRowSorter(table.getModel());
        table.setRowSorter(sortTable);

        sortTable.setComparator(0, new Comparator<String>() {
            public int compare(String name1, String name2) {
                return name1.compareTo(name2);
            }
        });

        sortTable.setComparator(1, new Comparator<Integer>() {
            public int compare(Integer id1, Integer id2) {
                if (id2 > id1)
                    return 1;
                else
                    return -1;
            }
        });

        sortTable.setComparator(2, new Comparator<Float>() {
            public int compare(Float price1, Float price2) {
                if (price2 > price1)
                    return 1;
                else
                    return -1;
            }
        });

        sortTable.sort();

        constraints.gridx = 10;
        constraints.gridy = 10;
        add(new JScrollPane(table), constraints);


        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals((addProduct.getText()))) {
                    new AddProductJFrame(store,store.getDepartment(1), 1, tableModel);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(backButton.getText())) {
                    setVisible(false);
                    new ChooseDepJFrame(new GridBagLayout(), new Dimension(800, 800), store, icon, bought);
                    dispose();
                }
            }
        });

        removeProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(removeProduct.getText())) {
                    new RemoveProductJFrame(store.getDepartment(1), 1, tableModel);
                }
            }
        });

        statisticsButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(statisticsButton.getText())) {
                    new StatisticJFrame(new GridBagLayout(), new Dimension(800, 800), store, bought, icon, 1);
                    dispose();
                }
            }
        });

        modifyProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(modifyProduct.getText())) {
                    new ModifyProductJFrame(store.getDepartment(1), 1, tableModel);
                }
            }
        });
    }
}
