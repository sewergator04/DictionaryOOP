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
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author Admin
 */
public class DictionaryManagement {
    private final String filePath;
    private int indexes = 0;
    private final ArrayList<String> definitions;
    private final HashMap<String, Integer> definitionIndex;
    private int editIndex = -1;
    public DictionaryManagement(String filePath, ArrayList<String> definitions, HashMap<String, Integer> definitionIndex) {
        this.filePath = filePath;
        this.definitions = definitions;
        this.definitionIndex = definitionIndex;
    }
    
    public int getEditIndex() {
        return editIndex;
    }
    
    public void setEditIndex(int editIndex) {
        this.editIndex = editIndex;
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
                }
            }
            reader.close();
            System.out.println(keptlines.size());
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String keptline : keptlines) {
                writer.write(keptline);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, """
                                                Remove word successfully!
                                                Please click refresh to update the dictionary.""","Success!", JOptionPane.INFORMATION_MESSAGE);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void editWord(String changedLine) {
        if (editIndex == -1) {
            JOptionPane.showMessageDialog(null, "Please select a word!", "Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            long position = getLineIndex(file);
            file.seek(position);
            byte[] byteArray = changedLine.getBytes(StandardCharsets.UTF_8);
            file.write(byteArray);
            long endOfFile = file.length();
            file.setLength(file.getFilePointer());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, """
                                                Edit word successfully!
                                                Please click refresh to update the dictionary.""","Success!", JOptionPane.INFORMATION_MESSAGE);
        editIndex = -1;
    }
    
    private long getLineIndex(RandomAccessFile file) throws IOException {
        file.seek(0);
        long result = 0;
        int current = 0;
        while (current < editIndex && file.readLine() != null) {
            result = file.getFilePointer();
            current++;
        }
        return result;
    }
}
