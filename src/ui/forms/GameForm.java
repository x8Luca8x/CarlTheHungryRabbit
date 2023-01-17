package ui.forms;

import game.GameConstants;
import game.GameMode;
import ui.GameLayout;
import ui.GamePanel;

public class GameForm extends GamePanel implements FormBase
{
    private final GameMode gameMode = new GameMode();

    @Override
    public void initializeComponents()
    {
        GameLayout layout = new GameLayout();

        layout.setRows(GameConstants.FIELD_ROWS);
        layout.setColumns(GameConstants.FIELD_COLUMNS);

        setLayout(layout);
    }

    @Override
    public void onResize(int newWidth, int newHeight)
    {

    }

    @Override
    public void onShow()
    {

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