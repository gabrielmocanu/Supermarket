import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatisticJFrame extends JFrame {

    private JButton backButton = new JButton("Back");
    private JLabel clients = new JLabel("Clients: ");
    private JLabel observers = new JLabel("Observers: ");
    private JLabel expensiveLabel = new JLabel("The most expensive: ");
    private JLabel expensiveProduct = new JLabel();
    private JLabel wantedProduct = new JLabel();
    private JLabel boughtProduct = new JLabel();
    private JLabel wantedLabel = new JLabel("The most wanted: ");
    private JLabel boughtLabel = new JLabel("The most bought: ");
    private JPanel typesProduct = new JPanel(new GridBagLayout());

    private JTextArea textAreaClients = new JTextArea(15, 10);
    JScrollPane scrollPaneClients = new JScrollPane(textAreaClients);
    private final static String newline = "\n";

    private JTextArea textAreaObservers = new JTextArea(15, 10);
    JScrollPane scrollPaneObservers = new JScrollPane(textAreaObservers);

    private HashMap<Integer, Integer> vectorBoughtProducts = new HashMap<Integer, Integer>();
    private HashMap<Integer, Integer> vectorWantedProducts = new HashMap<Integer, Integer>();


    public StatisticJFrame(LayoutManager layoutManager, Dimension dimension, Store store, ArrayList<Item> bought, ImageIcon icon, int depId) {
        super("Statistic    ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setLayout(layoutManager);
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setIconImage(new ImageIcon("storeSwingIcon.png").getImage());
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        backButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        clients.setFont(new Font("Times New Roman", Font.BOLD, 12));
        observers.setFont(new Font("Times New Roman", Font.BOLD, 12));
        expensiveLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        expensiveProduct.setFont(new Font("Times New Roman", Font.BOLD, 12));
        wantedProduct.setFont(new Font("Times New Roman", Font.BOLD, 12));
        boughtProduct.setFont(new Font("Times New Roman", Font.BOLD, 12));
        wantedLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        boughtLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        typesProduct.setFont(new Font("Times New Roman", Font.BOLD, 12));
        scrollPaneClients.setFont(new Font("Times New Roman", Font.BOLD, 12));
        scrollPaneObservers.setFont(new Font("Times New Roman", Font.BOLD, 12));

        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(backButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(clients, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        textAreaClients.setEditable(false);
        ArrayList<Customer> customersAux = store.getDepartment(depId).getCustomers();
        if (customersAux != null) {
            for (int i = 0; i < customersAux.size(); i++) {
                textAreaClients.append(customersAux.get(i).toString());
                textAreaClients.append(newline);
            }
        }
        this.add(scrollPaneClients, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        this.add(observers, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        textAreaObservers.setEditable(false);
        ArrayList<Customer> customersAux2 = store.getDepartment(depId).getObservers();
        for (int i = 0; i < customersAux2.size(); i++) {
            textAreaObservers.append(customersAux2.get(i).toString());
            textAreaObservers.append(newline);
        }
        this.add(scrollPaneObservers, constraints);

        for (int i = 0; i < bought.size(); i++) {
            if (vectorBoughtProducts.containsKey(bought.get(i).getId()))
                vectorBoughtProducts.put(bought.get(i).getId(), vectorBoughtProducts.get(bought.get(i).getId()) + 1);
            else
                vectorBoughtProducts.put(bought.get(i).getId(), 1);
        }

        constraints.gridx = 0;
        constraints.gridy = 0;
        typesProduct.add(boughtLabel, constraints);

        Map.Entry<Integer, Integer> max = null;
        Map.Entry<Integer, Integer> act = null;

        for (Map.Entry<Integer, Integer> entry : vectorBoughtProducts.entrySet()) {
            if ((max==null) || (max != null) && ( entry.getValue() > max.getValue())) {
                max = entry;
                if(store.findDeparmentByItem(max.getKey()).getId() != depId)
                {
                    max = null;
                }
                if(max != null)
                    act = max;
            }
        }

        constraints.gridx = 0;
        constraints.gridy = 1;

        if (act != null) {
            boughtProduct.setText(store.findItem(act.getKey()).toString());
            typesProduct.add(boughtProduct, constraints);
        }


        for (int i = 0; i < customersAux2.size(); i++) {
            for (int j = 0; j < customersAux2.get(i).getWishList().getNrElements(); j++) {
                if (vectorWantedProducts.containsKey(customersAux2.get(i).getWishList().getItem(j).getId()))
                {
                    vectorWantedProducts.put(customersAux2.get(i).getWishList().getItem(j).getId(), vectorWantedProducts.get(customersAux2.get(i).getWishList().getItem(j).getId()) + 1);
                }
                else
                {
                    vectorWantedProducts.put(customersAux2.get(i).getWishList().getItem(j).getId(), 1);
                }
            }
        }
        constraints.gridx = 0;
        constraints.gridy = 2;
        typesProduct.add(wantedLabel, constraints);

        Map.Entry<Integer, Integer> max2 = null;

        for (Map.Entry<Integer, Integer> entry : vectorWantedProducts.entrySet()) {
            if (((max2 == null) || ( entry.getValue() > max2.getValue()))) {
                max2 = entry;
            }
        }

        constraints.gridx = 0;
        constraints.gridy = 3;

        if (max2 != null) {
            wantedProduct.setText(store.findItem(max2.getKey()).toString());
            typesProduct.add(wantedProduct, constraints);
        }

        constraints.gridx = 0;
        constraints.gridy = 4;
        typesProduct.add(expensiveLabel, constraints);

        Item maxItem = store.getDepartment(depId).getListProducts().getItem(0);

        for (int i = 1; i < store.getDepartment(depId).getListProducts().getNrElements(); i++) {
            if (store.getDepartment(depId).getListProducts().getItem(i).getPret() > maxItem.getPret())
                maxItem = store.getDepartment(depId).getListProducts().getItem(i);
        }

        constraints.gridx = 0;
        constraints.gridy = 5;
        expensiveProduct.setText(maxItem.toString());
        typesProduct.add(expensiveProduct, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        typesProduct.setBackground(new Color(32, 156, 238));
        this.add(typesProduct, constraints);

        this.setVisible(true);
        pack();

        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(backButton.getText())) {
                    setVisible(false);
                    if (depId == 0)
                        new MusicJFrame(layoutManager, dimension, store, icon, bought);
                    if (depId == 1)
                        new BookJFrame(layoutManager, dimension, store, icon, bought);
                    if (depId == 2)
                        new VideoJFrame(layoutManager, dimension, store, icon, bought);
                    if (depId == 3)
                        new SoftJFrame(layoutManager, dimension, store, icon, bought);
                    dispose();
                }
            }
        });


    }
}
