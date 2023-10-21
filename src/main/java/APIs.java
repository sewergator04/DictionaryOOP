
import java.util.concurrent.ExecutionException;

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
    private final SpeechSynthesis synthesizer;
    public APIs() {
        translator = new TranslatorWindow();
        synthesizer = new SpeechSynthesis();
    }
    
    public void openTranslator() {
        translator.setVisible(true);
    }
    
    public void pronounce(String englishText) {
        try {
            synthesizer.Synthesize(englishText);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
