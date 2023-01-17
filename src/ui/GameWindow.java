package ui;

import javax.swing.*;
import java.lang.reflect.Constructor;

public class GameWindow
{
    private JFrame frameInstance;

    public GameWindow()
    {
        frameInstance = new JFrame("Carl the hungry rabbit");
        frameInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameInstance.setSize(1280, 720);
        frameInstance.setResizable(false);
        frameInstance.setVisible(true);
    }

    public static GamePanel initNewPanel(Class<GamePanel> gamePanelClass)
    {
        try
        {
            GamePanel gamePanel = gamePanelClass.getConstructor().newInstance();
            gamePanel.initializeComponents();

            return gamePanel;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public JFrame getFrame()
    {
        return frameInstance;
    }

    public boolean isInitialized()
    {
        return frameInstance.getContentPane() instanceof GamePanel;
    }

    public GamePanel getGamePanelInstance()
    {
        if(!isInitialized())
            return null;

        return (GamePanel)frameInstance.getContentPane();
    }

    public GamePanel changeContentPane(GamePanel newInstance)
    {
        GamePanel oldGamePanel = getGamePanelInstance();

        newInstance.onResize(frameInstance.getWidth(), frameInstance.getHeight());
        frameInstance.setContentPane(newInstance);

        return oldGamePanel;
    }
}
