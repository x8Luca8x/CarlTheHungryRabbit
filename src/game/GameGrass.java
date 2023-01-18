package game;

import ui.GameImage;

import javax.swing.*;

public class GameGrass extends GameObject
{
    // Render components
    private final GameImage grassImage;

    public GameGrass()
    {
        super();
        grassImage = new GameImage(new ImageIcon("grass.png"));
    }

    @Override
    public JComponent getRenderComponent()
    {
        return grassImage;
    }
}
