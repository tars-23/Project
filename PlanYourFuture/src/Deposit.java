import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Deposit extends JFrame {
    public Deposit() throws IOException {
        JFrame frame = new JFrame("Deposit calculate");


        InputStream inputStream = getClass().getResourceAsStream("Background.png");
        ImageIcon imageIcon = new ImageIcon(ImageIO.read(Objects.requireNonNull(inputStream)));
        Image scaledImage = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel welcome = new JLabel(scaledIcon);


        JTextField amountField = new JTextField("");
        JTextField interestRateField = new JTextField("");
        JTextField yearsField = new JTextField("");
        JTextField annualContributionField = new JTextField("");
        JTextField inflationField = new JTextField("");


        JLabel inflationLabel = new JLabel("Enter inflation rate (Percentage)  ");
        JLabel amountLabel = new JLabel("Enter amount of deposit money per month  ");
        JLabel interestRateLabel = new JLabel("Enter interest rate (Percentage)  ");
        JLabel yearsLabel = new JLabel("Enter years of deposition  ");
        JLabel interestLabel = new JLabel();
        JLabel futureValueLabel = new JLabel();
        JLabel compoundInterestLabel = new JLabel();
        JLabel annualContributionLabel = new JLabel("Enter annual contribute");
        JLabel sumOfDepositLabel = new JLabel("");

        JCheckBox compoundInterestCheckBox = new JCheckBox("Compound Interest");

        JButton calculateButton = new JButton("Calculate");
        Object[] options = {sumOfDepositLabel, interestLabel, futureValueLabel, compoundInterestLabel, inflationLabel};


        calculateButton.addActionListener(e -> {
            try {

                double amount = Double.parseDouble(amountField.getText());
                double annualContribution = Double.parseDouble(annualContributionField.getText());
                int years = Integer.parseInt(yearsField.getText());
                double interestRate = Double.parseDouble(interestRateField.getText());
                double inflationRate = Double.parseDouble(inflationField.getText());
                double sum = amount * 12;
                double interest = AllCalculate.calculateSimpleInterest(amount * 12, interestRate / 100, years);
                interestLabel.setText(String.format("Total interest earned :  %.2f", interest));

                double futureValue = AllCalculate.calculateFutureValue(amount * 12, annualContribution, years, interestRate / 100);
                futureValueLabel.setText(String.format("Total your money in future : %.2f ", futureValue));

                double inflationValue = AllCalculate.impactOfInflation(amount, years, inflationRate /100);
                inflationLabel.setText(String.format("With %.0f percent inflation impact your %.2f will be %.2f in %d years", inflationRate, amount, inflationValue, years));

                sumOfDepositLabel.setText(String.format("You have deposit : %.2f per year", sum));
                if (compoundInterestCheckBox.isSelected()) {
                    compoundInterestLabel.setText(String.format("Compound interest value : %.2f", AllCalculate.calculateCompoundInterest(amount * 12, interestRate / 100, years)));
                }
                JOptionPane.showOptionDialog(null, options, "Result", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            } catch (NumberFormatException ex) {
                JLabel message = new JLabel(ex.getMessage());
                JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        amountField.addActionListener(e -> calculateButton.doClick());
        interestRateField.addActionListener(e -> calculateButton.doClick());
        yearsField.addActionListener(e -> calculateButton.doClick());
        annualContributionField.addActionListener(e -> calculateButton.doClick());
        inflationField.addActionListener(e -> calculateButton.doClick());
        compoundInterestCheckBox.addActionListener(e -> calculateButton.doClick());


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        inputPanel.add(interestRateLabel);
        inputPanel.add(interestRateField);
        inputPanel.add(yearsLabel);
        inputPanel.add(yearsField);
        inputPanel.add(annualContributionLabel);
        inputPanel.add(annualContributionField);
        inputPanel.add(inflationLabel);
        inputPanel.add(inflationField);
        inputPanel.add(compoundInterestCheckBox);
        inputPanel.add(Box.createVerticalStrut(20));
        inputPanel.add(calculateButton);


        frame.add(welcome, BorderLayout.WEST);
        frame.add(inputPanel, BorderLayout.EAST);
        frame.add(interestLabel, BorderLayout.SOUTH);
        frame.add(futureValueLabel, BorderLayout.SOUTH);
        frame.add(sumOfDepositLabel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

}