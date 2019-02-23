import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.JFrame;

class AppStart extends JFrame {
    public Store store;
    private JPanel start = new JPanel(new GridBagLayout());
    private JButton admin = new JButton("Administrator");
    private JButton client = new JButton("Client");
    private JButton uploadStoc = new JButton("Upload");
    private JLabel iconLabel = new JLabel();
    private JLabel notUpload = new JLabel("You didn't upload the store");
    ;
    private ImageIcon icon = new ImageIcon("icons/storeSwing.png");
    private InitStore init;
    private ArrayList<Item> bought = new ArrayList<Item>();
    private static final long serialVersionUID = 8;


    public AppStart() { // Constructor pentru cazul in care datele nu sunt initializate
        super("SwingStore");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 800));
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setIconImage(new ImageIcon("icons/storeSwingIcon.png").getImage());
        this.store = store;
        this.setLocationRelativeTo(null);

        start.setPreferredSize(this.getSize());
        start.setBackground(new Color(32, 156, 238));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        admin.setFont(new Font("Times New Roman", Font.BOLD, 12));
        client.setFont(new Font("Times New Roman", Font.BOLD, 12));
        uploadStoc.setFont(new Font("Times New Roman", Font.BOLD, 12));
        notUpload.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        notUpload.setForeground(new Color(255, 0, 0));


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        iconLabel.setIcon(icon);
        start.add(iconLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        start.add(client, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        start.add(admin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        start.add(uploadStoc, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        start.add(notUpload, constraints);
        notUpload.setVisible(false);

        add(start);
        setVisible(true);
        pack();

        client.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(client.getText())) {
                    if (init == null) {
                        notUpload.setVisible(true);
                    } else {
                        notUpload.setVisible(false);
                        start.setVisible(false);
                        new UtilizatorJFrame(new GridBagLayout(), new Dimension(800, 800), store, icon, bought);
                        dispose();
                    }
                }
            }
        });

        admin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(admin.getText())) {
                    if (init == null) {
                        notUpload.setVisible(true);
                    } else {
                        notUpload.setVisible(false);
                        start.setVisible(false);
                        new AdministratorJFrame(new GridBagLayout(), new Dimension(800, 800), store, icon, bought);
                        dispose();
                    }
                }
            }
        });

        uploadStoc.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(uploadStoc.getText())) {
                    init = new InitStore();
                    store = init.getStore("store.txt");
                    store.ListCustomer = init.getCustomers("customers.txt");
                    //init.getEvents("events.txt");
                    notUpload.setVisible(false);
                }
            }
        });


    }

    public AppStart(Store store, ArrayList<Item> bought) { // Constructor pentru cazul cand datele sunt initializate
        super("SwingStore");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 800));
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setIconImage(new ImageIcon("icons/storeSwingIcon.png").getImage());
        this.store = store;
        this.setLocationRelativeTo(null);

        start.setPreferredSize(this.getSize());
        start.setBackground(new Color(32, 156, 238));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        admin.setFont(new Font("Times New Roman", Font.BOLD, 12));
        client.setFont(new Font("Times New Roman", Font.BOLD, 12));
        uploadStoc.setFont(new Font("Times New Roman", Font.BOLD, 12));
        notUpload.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        notUpload.setForeground(new Color(255, 0, 0));


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        iconLabel.setIcon(icon);
        start.add(iconLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        start.add(client, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        start.add(admin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        start.add(uploadStoc, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        start.add(notUpload, constraints);
        notUpload.setVisible(false);

        add(start);
        setVisible(true);
        pack();

        client.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(client.getText())) {
                    if (store == null) {
                        notUpload.setVisible(true);
                    } else {
                        notUpload.setVisible(false);
                        start.setVisible(false);
                        new UtilizatorJFrame(new GridBagLayout(), new Dimension(800, 800), store, icon, bought);
                        dispose();
                    }
                }
            }
        });

        admin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals(admin.getText())) {
                    if (store == null) {
                        notUpload.setVisible(true);
                    } else {
                        notUpload.setVisible(false);
                        start.setVisible(false);
                        new AdministratorJFrame(new GridBagLayout(), new Dimension(800, 800), store, icon, bought);
                        dispose();
                    }
                }
            }
        });

    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Doesn't Work");
        }
        new AppStart();

    }
}

