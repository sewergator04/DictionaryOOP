/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Admin
 */
public class APIs {
    private final TranslatorWindow translator;
    public APIs() {
        translator = new TranslatorWindow();
    }
    
    public void openTranslator() {
        translator.setVisible(true);
    }
}
