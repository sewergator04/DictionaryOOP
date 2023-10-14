/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javaswing.dictionary;

/**
 *
 * @author Admin
 */
public class Games {
    private final String MCGamePath = "C:\\Users\\Admin\\Documents\\NetBeansProjects\\dictionary_release\\MCQuestions";
    private final MultipleChoicesGame MCGame;
    
    public Games() {
        MCGame = new MultipleChoicesGame(MCGamePath);
    }
    
    public void openMCGame() {
        MCGame.setVisible(true);
    }
}
