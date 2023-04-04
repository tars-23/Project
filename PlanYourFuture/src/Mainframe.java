import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Mainframe extends JFrame {
    public Mainframe() throws IOException {
        JFrame frame = new JFrame("Future Planner");

        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Create Deposit, Tax, and Saving panels
        JPanel depositPanel = new JPanel();
        JPanel taxPanel = new JPanel();
        JPanel savingPanel = new JPanel();

        // Add panels to cardPanel with unique names
        cardPanel.add(depositPanel, "Deposit");
        cardPanel.add(taxPanel, "Tax");
        cardPanel.add(savingPanel, "Saving");

        // Create buttons to switch between panels
        JButton depositButton = new JButton("Deposit");
        JButton taxButton = new JButton("Tax");
        JButton savingButton = new JButton("Saving");

        // Set the preferred size of buttons to match image width
        InputStream inputStream = getClass().getResourceAsStream("Background.png");
        ImageIcon imageIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(inputStream)));
        depositButton.setPreferredSize(new Dimension(imageIcon.getIconWidth() / 3, depositButton.getPreferredSize().height));
        taxButton.setPreferredSize(new Dimension(imageIcon.getIconWidth() / 3, taxButton.getPreferredSize().height));
        savingButton.setPreferredSize(new Dimension(imageIcon.getIconWidth() / 3, savingButton.getPreferredSize().height));

        // Add action listeners to buttons to switch between panels
        depositButton.addActionListener(e -> cardLayout.show(cardPanel, "Deposit"));
        taxButton.addActionListener(e -> cardLayout.show(cardPanel, "Tax"));
        savingButton.addActionListener(e -> cardLayout.show(cardPanel, "Saving"));

        // Add buttons to inputPanel
        JPanel inputPanel = new JPanel();
        inputPanel.add(depositButton);
        inputPanel.add(taxButton);
        inputPanel.add(savingButton);


        depositButton.addActionListener(e -> {
            try {
                JFrame depositFrame = new Deposit();
                depositFrame.setVisible(true);
                depositFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        taxButton.addActionListener(e -> {
            try {
                JFrame taxFrame = new taxGUI();
                taxFrame.setVisible(true);


                taxFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        taxFrame.dispose();
                    }
                });
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        savingButton.addActionListener(e -> {
            try {
                SavingsTracker savingsTracker = new SavingsTracker();
                savingsTracker.frame.setVisible(true);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        JLabel welcome = new JLabel(imageIcon);
        frame.add(welcome, BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException {
        new Mainframe();
    }
}