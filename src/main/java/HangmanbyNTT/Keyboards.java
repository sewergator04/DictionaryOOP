package HangmanbyNTT;

import javax.swing.*;
import java.awt.*;

public class Keyboards extends JButton {
    private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};
    private JButton[] Buttons = new JButton[26];

    public Keyboards(JPanel keyboardPanel) {
        for (int i = 0; i < this.alphabet.length; i++) {
            this.Buttons[i] = new JButton(String.valueOf(this.alphabet[i]).toUpperCase());
            keyboardPanel.add(this.Buttons[i]);
        }
    }

    public void resetKeyboard() {
        for (JButton key : this.Buttons) {
            key.setEnabled(true);
            key.setBackground(null);
        }
    }

    public void lockKeyboards() {
        for (JButton key : this.Buttons) {
            key.setEnabled(false);
        }
    }

    public void lockKey(char ch) {
        for (JButton key : this.Buttons) {
            if (key.getText().equals(String.valueOf(ch))) {
                key.setEnabled(false);
            }
        }
    }

    public void colorGreenKey(char ch) {
        for (JButton key : this.Buttons) {
            if (key.getText().equals(String.valueOf(ch))) {
                key.setBackground(Color.GREEN);
            }
        }
    }

    public void colorRedKey(char ch) {
        for (JButton key : this.Buttons) {
            if (key.getText().equals(String.valueOf(ch))) {
                key.setBackground(Color.RED);
            }
        }
    }

    public JButton[] getButtons() {
        return Buttons;
    }
}
