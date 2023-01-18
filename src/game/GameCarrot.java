package game;

import ui.GameImage;

import javax.swing.*;

public class GameCarrot extends GameObject
{
    // Render components
    private final GameImage carrotImage;

    public GameCarrot()
    {
        super();
        carrotImage = new GameImage(new ImageIcon("carrot.png"));

        setIsCollisionEnabled(false);
    }

    @Override
    public JComponent getRenderComponent()
    {
        return carrotImage;
    }
}
