import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseDepJFrame extends JFrame {

    private JButton musicButton = new JButton("Music Department");
    private JButton bookButton = new JButton("Book Department");
    private JButton videoButton = new JButton("Video Department");
    private JButton softButton = new JButton("Software Department");
    private JButton backButton = new JButton("Back");
    private JLabel labelIcon = new JLabel();

    public ChooseDepJFrame(LayoutManager layoutManager, Dimension dimension, Store store, ImageIcon icon, ArrayList<Item> bought) {

        super("Departments");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(dimension);
        this.setLayout(layoutManager);
        this.getContentPane().setBackground(new Color(32, 156, 238));
        this.setIconImage( new ImageIcon("icons/storeSwingIcon.png").getImage());
        this.setLocationRelativeTo(null);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 0, 10, 0);

        musicButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        bookButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        videoButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        softButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        backButton.setFont(new Font("Times New Roman",Font.BOLD,12));
        labelIcon.setIcon(icon);

        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(labelIcon,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(musicButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(bookButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(videoButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(softButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(backButton, constraints);

        this.setVisible(true);
        pack();

        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if(button.getText().equals(backButton.getText()))
                {
                    setVisible(false);
                    new AdministratorJFrame(new GridBagLayout(), new Dimension(800,800),store, icon, bought);
                    dispose();
                }
            }
        });


        musicButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals((musicButton.getText()))) {
                    setVisible(false);
                    new MusicJFrame(new GridBagLayout(), new Dimension(800,800),store, icon, bought);
                    dispose();
                }
            }
        });


        bookButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals((bookButton.getText()))) {
                    setVisible(false);
                    new BookJFrame(new GridBagLayout(), new Dimension(800,800),store, icon, bought);
                    dispose();
                }
            }
        });

        videoButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals((videoButton.getText()))) {
                    setVisible(false);
                    new VideoJFrame(new GridBagLayout(), new Dimension(800,800),store, icon, bought);
                    dispose();
                }
            }
        });

        softButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals((softButton.getText()))) {
                    setVisible(false);
                    new SoftJFrame(new GridBagLayout(), new Dimension(800,800),store, icon, bought);
                    dispose();
                }
            }
        });


    }
}

