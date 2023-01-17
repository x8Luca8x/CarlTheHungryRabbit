package game;

import ui.UIManager;
import ui.forms.FormBase;
import ui.forms.GameForm;

public class GameMode
{
    private final GameObjectManager gameObjectManager = new GameObjectManager();

    private final PlayerController playerController = new PlayerController();
    private final PlayerCharacter playerCharacter;

    public GameMode()
    {
        playerCharacter = (PlayerCharacter)gameObjectManager.createGameObject(PlayerCharacter.class);
        assert playerCharacter != null;

        playerCharacter.setPlayerController(playerController);
    }

    public GameObjectManager getGameObjectManager()
    {
        return gameObjectManager;
    }

    public PlayerController getPlayerController()
    {
        return playerController;
    }

    public PlayerCharacter getPlayerCharacter()
    {
        return playerCharacter;
    }

    public static GameMode getGameModeFromForm()
    {
        FormBase form = UIManager.getInstance().getFormManager().getCurrentForm();
        if(!(form instanceof GameForm))
            return null;

        return ((GameForm)form).getGameMode();
    }
}
