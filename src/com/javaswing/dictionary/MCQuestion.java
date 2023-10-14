/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javaswing.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MCQuestion {
    private String question;
    private List<String> choices;
    private String answer;
    
    public MCQuestion(String filePath) {
        choices = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            
            if((question = reader.readLine()) != null) {
                for (int i = 0; i < 4; i++) {
                    String choice;
                    choice = reader.readLine();
                    choices.add(choice);
                }
                answer = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    public List<String> getChoices() {
        return choices;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public String getAnswer() {
        return answer;
    }
    
}
