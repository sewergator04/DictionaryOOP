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
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Admin
 */
public class DictionaryManagement {
    private final String filePath;
    private int indexes = 0;
    private final ArrayList<String> definitions;
    private final HashMap<String, Integer> definitionIndex;
    public DictionaryManagement(String filePath, ArrayList<String> definitions, HashMap<String, Integer> definitionIndex) {
        this.filePath = filePath;
        this.definitions = definitions;
        this.definitionIndex = definitionIndex;
    }
    
    public DefaultTableModel readTxtFile() {
        
        DefaultTableModel model = new DefaultTableModel();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            
            String line;
            
            model.addColumn("Words");
            
            while ((line = reader.readLine()) != null) {
                
                String[] data = line.split("\t");
                if (data.length < 2) {
                    System.out.println("Exception at line " + indexes);
                }
                definitionIndex.put(data[0], indexes);
                if (data.length > 0) {
                    model.addRow(new Object[]{data[0]});
                }
                data[1] = data[1].replace("\\n", "\n");
                definitions.add(data[1]);
                indexes++;
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
                        definitions.add((String) model.getValueAt(row, 1));
                        definitionIndex.put((String) model.getValueAt(row,0), indexes);
                        indexes++;
                    }
                }
            }
            JOptionPane.showMessageDialog(null,"Add words successfully!","Success!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
}
