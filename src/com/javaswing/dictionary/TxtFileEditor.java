/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javaswing.dictionary;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
/*
 *
 * @author Admin
 */
public class TxtFileEditor {
    private final String fileName;
    public TxtFileEditor(String fileName) {
        this.fileName = fileName;
}
    public void AddWords(DefaultTableModel model) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
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
