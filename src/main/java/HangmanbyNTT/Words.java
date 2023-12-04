package HangmanbyNTT;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Words {
    private final String filePath = "D:\\dictionary_Gradle\\dictionary.txt";
    private ArrayList<String> listWords;

    private String checkword(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '"') {
                word = word.substring(0, i) + word.substring(i + 1);
            }
        }
        return word;
    }

    public String chooseWord() {
        listWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String [] data = line.split("\t");
                data[0] = checkword(data[0]);
                listWords.add(data[0]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String word = listWords.get(new Random().nextInt(listWords.size())).toUpperCase();
        return word;
    }
}
