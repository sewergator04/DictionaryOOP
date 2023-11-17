package HangmanbyNTT;

import javax.swing.*;
import java.awt.*;

public class RunGame{
    private final int Width = 800;
    private final int Height = 600;
    private final String Title = "HangMan";

    public RunGame() {
        JFrame screen = new JFrame();
        CardLayout cardLayout = new CardLayout();
        screen.setSize(Width, Height);
        screen.setTitle(Title);
        screen.setLocationRelativeTo(null);
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel container = new JPanel();
        container.setLayout(cardLayout);
        screen.add(container);

        StartScreen startScreen = new StartScreen(Width, Height, container, cardLayout, screen);
        MainGameScreen mainGameScreen = new MainGameScreen(Width, Height, container, cardLayout);

        container.add(startScreen, "1");
        container.add(mainGameScreen, "2");

        screen.setVisible(true);
    }

}
