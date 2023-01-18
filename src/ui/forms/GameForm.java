package ui.forms;

import game.GameMode;
import ui.GameLayout;
import ui.GamePanel;

import java.awt.*;

public class GameForm extends GamePanel implements FormBase
{
    private final GameMode gameMode = new GameMode();

    @Override
    public void initializeComponents()
    {
        setLayout(new GameLayout());
        setBackground(Color.decode("#1cca16"));
    }

    @Override
    public void onResize(int newWidth, int newHeight)
    {

    }

    @Override
    public void onShow()
    {
        gameMode.getGameObjectManager().renderGameObjects();
    }

    @Override
    public void onClose()
    {

    }

    public GameMode getGameMode()
    {
        return gameMode;
    }
}