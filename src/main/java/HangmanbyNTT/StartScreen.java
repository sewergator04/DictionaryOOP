package HangmanbyNTT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JPanel implements ActionListener {
    private final int Width;
    private final int Height;
    private JPanel container;
    private CardLayout cardLayout;
    private final ImageIcon backgroundImg = new ImageIcon("img/background.jpg");
    private JFrame screen;

    public StartScreen(int Width, int Height, JPanel container, CardLayout cardLayout, JFrame screen) {
        this.Width = Width;
        this.Height = Height;
        this.container = container;
        this.cardLayout = cardLayout;
        this.screen = screen;
        
        JLabel BackgroundIMG = new JLabel(backgroundImg);
        BackgroundIMG.setLayout(null);

        this.setLayout(new BorderLayout());
        this.add(BackgroundIMG);

        this.setExitButtonAndPlayButton(BackgroundIMG);
    }

    private void setExitButtonAndPlayButton(JLabel BackgroundIMG) {
        JButton playButton = new JButton("CHƠI");
        JButton exitButton = new JButton("THOÁT");

        int buttonWidth = 100;
        int buttonHeight = 50;
        int buttonY = Height - (buttonHeight * 3);

        int playButtonX = (Width / 2) - (buttonWidth + 20);
        playButton.setBounds(playButtonX, buttonY, buttonWidth, buttonHeight);
        playButton.addActionListener(this);

        int exitButtonX = (Width / 2) + 20;
        exitButton.setBounds(exitButtonX, buttonY, buttonWidth, buttonHeight);
        exitButton.addActionListener(this);

        BackgroundIMG.add(playButton);
        BackgroundIMG.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("CHƠI")) {
            this.cardLayout.show(container, "2");
        } else if (e.getActionCommand().equals("THOÁT")) {
            screen.dispose();
        }
    }
}
