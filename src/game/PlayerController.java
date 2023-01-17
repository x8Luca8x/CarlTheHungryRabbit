package game;

public class PlayerController
{
    private PlayerCharacter controllingCharacter;

    public void onMoveUp()
    {
        controllingCharacter.moveUp();
    }

    public void onMoveDown()
    {
        controllingCharacter.moveDown();
    }

    public void onMoveLeft()
    {
        controllingCharacter.moveLeft();
    }

    public void onMoveRight()
    {
        controllingCharacter.moveRight();
    }

    public PlayerCharacter getControllingCharacter()
    {
        return controllingCharacter;
    }

    public void setControllingCharacter(PlayerCharacter controllingCharacter)
    {
        this.controllingCharacter = controllingCharacter;
    }
}
