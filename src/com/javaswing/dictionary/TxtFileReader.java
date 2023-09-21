/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javaswing.dictionary;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Admin
 */    
public class TxtFileReader {
    
    public DefaultTableModel readTxtFile(String filePath) throws IOException {
        
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
        }
        return model;
    }
}
