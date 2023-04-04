import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SavingsTracker {

    JFrame frame;
    private JLabel dateLabel;
    private JLabel savingsLabel;

    private JTextField dateField;
    private JTextField savingsField;
    private JTable table;
    private DefaultTableModel model;
    private File currentFile = null;


    private void calculateSum() {
        double sum = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double savings = Double.parseDouble(model.getValueAt(i, 1).toString());
            sum += savings;
        }
        JOptionPane.showMessageDialog(frame, String.format("Total savings: %.2f Baht", sum));
    }

    public SavingsTracker() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Saving table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        dateLabel = new JLabel("Date:");
        panel.add(dateLabel);

        dateField = new JTextField();
        dateField.setColumns(10);
        panel.add(dateField);

        savingsLabel = new JLabel("Savings:");
        panel.add(savingsLabel);

        savingsField = new JTextField();
        savingsField.setColumns(10);
        panel.add(savingsField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> addRow());
        panel.add(saveButton);

        JButton exportButton = new JButton("Export to Text");
        exportButton.addActionListener(e -> exportToText());
        panel.add(exportButton);

        JButton sumButton = new JButton("Calculate Sum");
        sumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateSum();
            }
        });


        panel.add(sumButton);


        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        model = new DefaultTableModel(new Object[][]{}, new String[]{"Date", "Savings"});
        table.setModel(model);
        scrollPane.setViewportView(table);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    private void addRow() {
        String date = dateField.getText();
        String savings = savingsField.getText();

        if (date.isEmpty() || savings.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a date and savings amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double.parseDouble(savings);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Savings amount must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] row = { date, savings };
        model.addRow(row);
        dateField.setText("");
        savingsField.setText("");
    }

    private void exportToText() {
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame, "There is no data to export.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String fileName = file.getName();

            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < model.getColumnCount(); i++) {
                    sb.append(model.getColumnName(i)).append("\t");
                }
                sb.append("\n");

                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        sb.append(model.getValueAt(i, j)).append("\t");
                    }
                    sb.append("\n");
                }

                outputStream.write(sb.toString().getBytes());
                outputStream.close();

                JOptionPane.showMessageDialog(frame, "Data exported successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error exporting data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
}