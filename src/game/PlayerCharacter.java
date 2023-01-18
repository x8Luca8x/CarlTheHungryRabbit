package game;

import ui.GameImage;

import javax.swing.*;

public class PlayerCharacter extends GameObject
{
    private PlayerController playerController;

    // Render components
    private final GameImage characterImage;

    public PlayerCharacter()
    {
        super();
        characterImage = new GameImage(new ImageIcon("player.png"));
    }

    @Override
    public JComponent getRenderComponent()
    {
        return characterImage;
    }

    public PlayerController getPlayerController()
    {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController)
    {
        this.playerController = playerController;
        playerController.setControllingCharacter(this);
    }
}