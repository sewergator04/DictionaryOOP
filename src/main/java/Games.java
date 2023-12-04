/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import HangmanbyNTT.RunGame;
/**
 *
 * @author Admin
 */
public class Games {
    private final String MCGamePath = "D:\\dictionary_Gradle\\MCQuestions";
    private final MultipleChoicesGame MCGame;
    
    public Games() {
        MCGame = new MultipleChoicesGame(MCGamePath);
    }
    
    public void openMCGame() {
        MCGame.setVisible(true);
    }
    
    public void openHangman() {
        new RunGame();
    }
}
