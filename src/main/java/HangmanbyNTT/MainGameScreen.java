package HangmanbyNTT;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MainGameScreen extends JPanel implements ActionListener {
    private final int Width;
    private final int Height;
    private final JPanel container;
    private final CardLayout cardLayout;
    private final Image backgroundImg = new ImageIcon("img/background2.jpg").getImage();
    private Image[] hangman = new Image[7];
    private Keyboards keyboards;
    private String word;
    private final Words words = new Words();
    private char[] guessesWord;
    private int numGuessesMiss;
    private boolean gameOver;


    public MainGameScreen(int Width, int Height, JPanel container, CardLayout cardLayout) {
        this.Width = Width;
        this.Height = Height;
        this.container = container;
        this.cardLayout = cardLayout;

        this.setLayout(null);

        this.word = words.chooseWord();
        this.setGuessesWord();

        this.numGuessesMiss = 0;

        for (int i = numGuessesMiss; i < 7; i++) {
            this.hangman[i] = new ImageIcon("img/Hangman-" + i + ".png").getImage();
        }

        this.gameOver = false;

        this.setBackButtonAndNewButton();
        this.setKeyboards();

    }

    private void setBackButtonAndNewButton() {
        JButton newButton = new JButton("CHƠI LẠI");
        JButton backButton = new JButton("QUAY LẠI");

        int buttonWidth = 90;
        int buttonHeight = 30;
        int buttonX = 10;

        int newButtonY = 10;
        newButton.setBounds(buttonX, newButtonY, buttonWidth, buttonHeight);
        newButton.addActionListener(this);

        int backButtonY = newButtonY + buttonHeight + 10;
        backButton.setBounds(buttonX, backButtonY, buttonWidth, buttonHeight);
        backButton.addActionListener(this);

        this.add(newButton);
        this.add(backButton);
    }

    private void setKeyboards() {
        JPanel keyboard = new JPanel(new GridLayout(3, 9));
        int keyboardWidth = this.Width - 20;
        int keyboardHeight = this.Height / 2 - 150;
        int keyboardX = 2;
        int keyboardY = this.Height / 2 + 108;
        keyboard.setBounds(keyboardX, keyboardY, keyboardWidth, keyboardHeight);

        this.keyboards = new Keyboards(keyboard);
        this.addActionListenerToKeyboard();

        this.add(keyboard);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();
        super.paintComponent(g2D);

        int space = 10;
        int UnderlineLength = this.calculateUnderlineLength(this.word, space);
        int x1 = (this.Width / 2) - ((UnderlineLength * word.length() + space * (word.length() + 1)) / 2);
        int y1 = this.Height / 2 + 90;
        int x2 = x1 + UnderlineLength;
        int y2 = y1;
        int fontSize = UnderlineLength;
        Font font = new Font("Arial", Font.BOLD, fontSize);

        g2D.drawImage(this.backgroundImg, 0, 0, null);

        int hangmanX = (this.Width / 2) - this.hangman[0].getWidth(null) / 2;
        int hangmanY = 20;
        g2D.drawImage(this.hangman[numGuessesMiss], hangmanX, hangmanY, null);

        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(5));
        g2D.setFont(font);

        this.drawLetterUnderline(g2D, x1, y1, x2, y2, space, UnderlineLength);

        this.drawGuessedWord(g2D, x1, y1, x2, space, UnderlineLength);

        if (this.gameOver && !this.guessesWord.equals(this.word.toCharArray())) {
            this.drawMissingLetters(g2D, x1, y1, x2, space, UnderlineLength);
        }

        if (this.gameOver) {
            this.drawGameOverText(g2D);
        }
    }

    private void drawLetterUnderline(Graphics2D g2D, int x1, int y1, int x2, int y2, int space, int UnderlineLength) {
        char[] wordToChar = this.word.toCharArray();
        for (char ch : wordToChar) {
            if (String.valueOf(ch).matches("[a-zA-Z0-9]")) {
                g2D.drawLine(x1, y1, x2, y2);
            } else {
                int charX = x1 + (UnderlineLength / 2) - (g2D.getFontMetrics().stringWidth(String.valueOf(ch).toUpperCase()) / 2);
                int charY = y1 - 10;
                g2D.drawString(String.valueOf(ch), charX, charY);
            }
            x1 = x2 + space;
            x2 = x1 + UnderlineLength;
        }
    }

    private void drawGuessedWord(Graphics2D g2D, int x1, int y1, int x2, int space, int UnderlineLength) {
        int y = y1 - 10;
        for (char ch : this.guessesWord) {
            int x = x1 + (UnderlineLength / 2) - (g2D.getFontMetrics().stringWidth(String.valueOf(ch).toUpperCase()) / 2);

            if (String.valueOf(ch).matches("[a-zA-Z0-9]")) {
                g2D.drawString(String.valueOf(ch).toUpperCase(), x, y);
            }
            x1 = x2 + space;
            x2 = x1 + UnderlineLength;
        }
    }

    private void drawMissingLetters(Graphics2D g2D, int x1, int y1, int x2, int space, int UnderlineLength) {
        g2D.setColor(Color.RED);
        for (int i = 0; i < this.word.toCharArray().length; i++) {
            char ch = this.word.toCharArray()[i];
            int x = x1 + (UnderlineLength / 2) - (g2D.getFontMetrics().stringWidth(String.valueOf(ch).toUpperCase()) / 2);
            int y = y1 - 10;

            if (String.valueOf(ch).matches("[a-zA-Z0-9]") && this.guessesWord[i] != this.word.toCharArray()[i]) {
                g2D.drawString(String.valueOf(ch), x, y);
            }
            x1 = x2 + space;
            x2 = x1 + UnderlineLength;
        }
    }

    private void drawGameOverText(Graphics2D g2D) {

        String text;
        if ((Arrays.equals(this.guessesWord, this.word.toCharArray()))) {
            text = "Bạn đã thắng!";
        } else {
            text = "Bạn đã thua:((";
        }
        int fontSize = 100;
        Font font = new Font("Arial", Font.BOLD, fontSize);
        g2D.setFont(font);

        int textX = (this.Width / 2) - (g2D.getFontMetrics().stringWidth(text) / 2);
        int textY = (this.Height / 2) - (g2D.getFontMetrics().getHeight() / 2);

        if (text.equals("Bạn đã thắng!")) {
            g2D.setColor(Color.GREEN);
        }
        else {
            g2D.setColor(Color.RED);
        }
        g2D.drawString(text, textX, textY);
    }


    private int calculateUnderlineLength(String word, int space) {
        int UnderlineLength;
        if (word.length() <= 10) {
            UnderlineLength = ((this.Width - 40) / 10) - space;
        }
        else {
            UnderlineLength = ((this.Width - 20) / word.length()) - space;
        }
        return UnderlineLength;
    }

    private boolean checkWord(char ch) {
        for (int i = 0; i < this.guessesWord.length; i++) {
            if (ch == this.word.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private void updateScreen(char ch) {
        if (checkWord(ch)) {
            for (int i = 0; i < this.guessesWord.length; i++) {
                if (ch == this.word.charAt(i)) {
                    this.guessesWord[i] = this.word.charAt(i);
                }
            }
            keyboards.colorGreenKey(ch);
        } else {
            this.numGuessesMiss++;
            keyboards.colorRedKey(ch);
        }
        keyboards.lockKey(ch);
        repaint();
    }

    private void checkGame() {
        if (this.numGuessesMiss == 6) {
            this.keyboards.lockKeyboards();
            this.gameOver = true;
        }

        if (Arrays.equals(this.guessesWord, this.word.toCharArray())) {
            this.keyboards.lockKeyboards();
            this.gameOver = true;
        }
    }

    private void setGuessesWord() {
        char[] wordToChar = word.toCharArray();
        this.guessesWord = new char[wordToChar.length];

        for (int i = 0; i < wordToChar.length; i++) {
            if (wordToChar[i] < 'A' || wordToChar[i] > 'Z') {
                this.guessesWord[i] = wordToChar[i];
            }
        }
    }

    private void resetGame(ActionEvent e) {
        this.word = words.chooseWord();
        this.setGuessesWord();
        this.keyboards.resetKeyboard();
        this.numGuessesMiss = 0;
        this.gameOver = false;
    }

    private void addActionListenerToKeyboard() {
        for (JButton key : this.keyboards.getButtons()) {
            key.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("QUAY LẠI")) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn quay lại không?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (result == 0)  {
                this.cardLayout.show(container, "1");
                this.resetGame(e);
            }
        } else if (e.getActionCommand().equals("CHƠI LẠI")) {
            int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn bắt đầu game mới?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (result == 0) {
                this.resetGame(e);
                repaint();
            }
        } else {
            for (JButton key : this.keyboards.getButtons()) {
                if (e.getActionCommand().equals(key.getText())) {
                    updateScreen(key.getText().charAt(0));
                    checkGame();
                }
            }
        }
    }
}
