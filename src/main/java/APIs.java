
import java.io.IOException;
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
    private final Translator translator;
    private final SpeechSynthesis synthesizer;
    public APIs() {
        translator = new Translator();
        synthesizer = new SpeechSynthesis();
    }
    
    public String transVietToEn(String vietText) {
        String English = "";
        try {
            English = translator.EnToViet(vietText);
            English = translator.prettify(English);
            English = translator.JsonParsing(English);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return English;
    }
    
    public String transEnToViet(String engText) {
        String Vietnamese = "";
        try {
            Vietnamese = translator.VietToEn(engText);
            Vietnamese = translator.prettify(Vietnamese);
            Vietnamese = translator.JsonParsing(Vietnamese);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return Vietnamese;
    }
    
    public void pronounce(String englishText) {
        try {
            synthesizer.Synthesize(englishText);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
