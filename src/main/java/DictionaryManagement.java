/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class DictionaryManagement {
    private final String filePath;
    
    public DictionaryManagement(String filePath) {
        this.filePath = filePath;
    }
    
    public DefaultTableModel readTxtFile() {
        
        DefaultTableModel model = new DefaultTableModel();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            
            String line;
            if ((line = reader.readLine()) != null) {
                String[] columnNames = line.split("\t"); 
                model.setColumnIdentifiers(columnNames);
            }

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t"); 
                model.addRow(data);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return model;
    }
    
    public void AddWords(DefaultTableModel model) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            // Iterate through the rows of the JTable and write data to the file
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                // Iterate through the rows of the JTable and write data to the file
                for (int row = 0; row < model.getRowCount(); row++) {
                    boolean isEmptyRow = true;
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        String cellValue = (String) model.getValueAt(row, col);
                        if (cellValue != null && !cellValue.trim().isEmpty()) {
                            isEmptyRow = false;
                            if (cellValue != null) {
                                bufferedWriter.write(cellValue);
                            }
                            bufferedWriter.write("\t"); // Separate values with a tab or other delimiter
                        }
                    }
                    if (!isEmptyRow) {
                bufferedWriter.newLine(); // Add a newline after each non-empty row
            }
                }
            }
            JOptionPane.showMessageDialog(null,"Add words successfully!","Success!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
}
