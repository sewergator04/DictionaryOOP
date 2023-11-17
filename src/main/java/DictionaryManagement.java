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
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
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
    
    public void Addword(String word, String meaning) {
        try {
            String result = word + "\t" + meaning.replace("\n","\\n");
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(result);
            writer.newLine();
            definitions.add(meaning);
            definitionIndex.put(word, indexes);
            indexes++;
            JOptionPane.showMessageDialog(null,"Add words successfully!","Success!", JOptionPane.INFORMATION_MESSAGE);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void removeWord(String key) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            ArrayList<String> keptlines = new ArrayList<>();
            String line;
            
            while ((line = reader.readLine()) != null) {
                
                String[] data = line.split("\t");
                if (!data[0].equals(key)) {
                    keptlines.add(line);
                    //System.out.println(line);
                }
            }
            reader.close();
            System.out.println(keptlines.size());
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String keptline : keptlines) {
                writer.write(keptline);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null,"Remove word successfully!","Success!", JOptionPane.INFORMATION_MESSAGE);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
