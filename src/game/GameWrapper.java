package game;

import ui.UIManager;
import ui.forms.GameForm;

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

    public static GameForm getGameForm()
    {
        return (GameForm)UIManager.getInstance().getFormManager().getCurrentForm();
    }
}
