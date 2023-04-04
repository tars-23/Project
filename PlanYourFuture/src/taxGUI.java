import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class taxGUI extends JFrame {
    public taxGUI() throws IOException {
        JFrame frame = new JFrame("Tax calculator");
        frame.setLayout(new FlowLayout());
        JLabel incomeLabel = new JLabel("Enter income per month");
        JLabel expensesLabel = new JLabel("Enter your expense per month ");
        JLabel taxDeductLabel = new JLabel("Enter your total tax deduction");
        JLabel taxLabel = new JLabel("");


        InputStream inputStream = getClass().getResourceAsStream("Background.png");
        ImageIcon imageIcon = new ImageIcon(ImageIO.read(inputStream));
        Image scaledImage = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel welcome = new JLabel(scaledIcon);


        JTextField incomeField = new JTextField();
        JTextField expensesField = new JTextField();
        JTextField taxDeductField = new JTextField();
        Object[] options = {taxLabel};


        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> {
            try {
                double income = Double.parseDouble(incomeField.getText());
                double expenses = Double.parseDouble(expensesField.getText());
                double deduct = Double.parseDouble(taxDeductField.getText());
                double tax = AllCalculate.calculateIncomeTax(income * 12 - expenses * 12);
                double finalTax = tax - deduct;
                if (finalTax <= 0) {
                    taxLabel.setText("your tax is 0 Baht");
                } else {
                    taxLabel.setText(String.format("Total tax %.2f Baht", finalTax));
                }
                JOptionPane.showOptionDialog(null, options, "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            } catch (NumberFormatException ex) {
                JLabel message = new JLabel(ex.getMessage());
                JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        expensesField.addActionListener(e -> calculateButton.doClick());
        incomeField.addActionListener(e -> calculateButton.doClick());
        taxDeductField.addActionListener(e -> calculateButton.doClick());


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(incomeLabel);
        panel.add(incomeField);
        panel.add(taxDeductLabel);
        panel.add(taxDeductField);
        panel.add(expensesLabel);
        panel.add(expensesField);
        panel.add(calculateButton);


        frame.add(welcome);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}