package game;

public class GameWrapper
{
    public static PlayerController getPlayerController()
    {
        return GameMode.getGameModeFromForm().getPlayerController();
    }

    public static PlayerCharacter getPlayerCharacter()
    {
        return GameMode.getGameModeFromForm().getPlayerCharacter();
    }
}
