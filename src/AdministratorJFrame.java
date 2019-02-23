import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class AdministratorJFrame extends JFrame {

    private JLabel labelUsername = new JLabel("Enter username: ");
    private JLabel labelPassword = new JLabel("Enter password: ");
    private JLabel notAvailable = new JLabel("Wrong Username/Password");
    private JTextField textUsername = new JTextField(20);
    private JPasswordField fieldPassword = new JPasswordField(20);
    private JButton buttonLogin = new JButton("Login");
    private JButton buttonBack = new JButton("Back");
    private JPanel buttons = new JPanel(new GridBagLayout());
    private JLabel iconLabel = new JLabel();
    private Store store;


    public AdministratorJFrame(LayoutManager layoutManager, Dimension dimension, Store store, ImageIcon icon, ArrayList<Item> bought)
    {
        super("Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.store = store;
        this.setMinimumSize(dimension);
        this.setLayout(layoutManager);
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setIconImage( new ImageIcon("icons/storeSwingIcon.png").getImage());
        this.setLocationRelativeTo(null);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        labelUsername.setFont(new Font("Times New Roman",Font.BOLD,12));
        labelPassword.setFont(new Font("Times New Roman",Font.BOLD,12));
        buttonLogin.setFont(new Font("Times New Roman",Font.BOLD,12));
        buttonBack.setFont(new Font("Times New Roman",Font.BOLD,12));
        notAvailable.setFont(new Font("Times New Roman",Font.PLAIN,12));
        notAvailable.setForeground(new Color(255,0,0));
        buttons.setBackground(new Color(32, 156, 238));


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        iconLabel.setIcon(icon);
        this.add(iconLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        buttons.add(labelUsername, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        buttons.add(textUsername, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        buttons.add(labelPassword, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        buttons.add(fieldPassword, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        buttons.add(buttonLogin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        buttons.add(buttonBack, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        buttons.add(notAvailable, constraints);
        notAvailable.setVisible(false);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(buttons,constraints);

        this.setVisible(true);
        pack();


        buttonBack.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals(buttonBack.getText()))
                {
                    new AppStart(store,bought);
                    setVisible(false);
                    dispose();
                }
            }
        });

        buttonLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals((buttonLogin.getText())))
                {
                    loginAction(textUsername.getText(),fieldPassword.getText(), icon, bought);
                }
            }
        });

    }

    public void loginAction(String username, String password, ImageIcon icon, ArrayList<Item> bought)
    {
        File file = new File("administratori.txt");

        if (!file.exists())
            return ;

        try
        {
            Scanner scan = new Scanner(file);
            String line;
            while(scan.hasNextLine())
            {
                line = scan.nextLine();
                String [] split = line.split(";");
                if(username.equals(split[0]))
                {
                    if(password.equals(split[1]))
                    {
                        this.setVisible(false);
                        new ChooseDepJFrame(new GridBagLayout(), new Dimension(800,800),store, icon, bought);
                        setVisible(false);
                        dispose();
                        return;
                    }
                    else
                    {
                        notAvailable.setVisible(true);
                    }
                }
                else
                {
                    notAvailable.setVisible(true);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
