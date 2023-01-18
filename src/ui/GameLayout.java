package ui;

import game.GameConstants;

import java.awt.*;

public class GameLayout extends GridLayout
{
    public GameLayout()
    {
        super(GameConstants.FIELD_ROWS, GameConstants.FIELD_COLUMNS);
    }
}
