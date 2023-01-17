package game;

public class PlayerCharacter extends GameObject
{
    private PlayerController playerController;

    public PlayerCharacter()
    {
        super();
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