package game;

import ui.UIManager;
import ui.forms.FormBase;
import ui.forms.GameForm;

public class GameMode
{
    private final GameObjectManager gameObjectManager;

    private final PlayerController playerController;
    private final PlayerCharacter playerCharacter;

    public GameMode()
    {
        gameObjectManager = new GameObjectManager(this);
        playerController = new PlayerController();

        playerCharacter = (PlayerCharacter)gameObjectManager.createGameObject(PlayerCharacter.class);
        assert playerCharacter != null;

        playerCharacter.setPlayerController(playerController);

        // Spawn default game objects
        for(int i = 0; i < 4; ++i)
        {
            var location = gameObjectManager.getEmptyRandomLocation();
            spawnNewCarrot(location.row, location.column);
        }

        for(int i = 0; i < 4; ++i)
        {
            var location = gameObjectManager.getEmptyRandomLocation();
            spawnNewGrass(location.row, location.column);
        }
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

    public GameObject spawnNewCarrot(int row, int column)
    {
        GameObject carrot = gameObjectManager.createGameObject(GameCarrot.class, row, column);
        assert carrot != null;

        return carrot;
    }

    public GameObject spawnNewGrass(int row, int column)
    {
        GameObject grass = gameObjectManager.createGameObject(GameGrass.class, row, column);
        assert grass != null;

        return grass;
    }

    public void onCarrotCollected(GameObject carrotObject)
    {
        gameObjectManager.destroyGameObject(carrotObject);

        var location = gameObjectManager.getEmptyRandomLocation();
        spawnNewCarrot(location.row, location.column);
    }

    public void onGameObjectMoved(GameObject gameObject)
    {
        var carrots = gameObjectManager.getAllGameObjectsOfClass(GameCarrot.class);
        for(GameObject carrot : carrots)
        {
            if(gameObjectManager.areObjectsOverlapping(gameObject, carrot))
            {
                onCarrotCollected(carrot);
                break;
            }
        }
    }
}
