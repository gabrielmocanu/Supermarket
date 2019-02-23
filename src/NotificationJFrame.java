import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Comparator;


public class NotificationJFrame extends JFrame {

    private JButton backButton = new JButton("Back");
    private JTextArea textArea = new JTextArea(30,20);
    JScrollPane scrollPane = new JScrollPane(textArea);
    private final static String newline = "\n";


    public NotificationJFrame(LayoutManager layoutManager, Dimension dimension, Store store, Customer customer, ImageIcon icon, ArrayList bought)
    {
        super("WishList");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setLayout(layoutManager);
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setIconImage( new ImageIcon("storeSwingIcon.png").getImage());
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,0,10,0);

        backButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        textArea.setFont(new Font("Times New Roman",Font.BOLD,12));
        scrollPane.setFont(new Font("Times New Roman",Font.BOLD,12));

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(backButton,constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        textArea.setEditable(false);
        for(int i = 0; i < customer.getNotifications().size(); i++)
        {
            textArea.append(customer.getNotifications().get(i).toString());
            textArea.append(newline);
        }
        this.add(scrollPane);

        this.setVisible(true);
        pack();



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


    }
}
