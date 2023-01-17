package game;

import java.util.ArrayList;
import java.util.UUID;

public class GameObjectManager
{
    private final ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public GameObject createGameObject(Class gameObjectClass)
    {
        try
        {
            GameObject gameObject = (GameObject)gameObjectClass.getConstructor().newInstance();
            gameObjects.add(gameObject);

            return gameObject;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void destroyGameObject(GameObject gameObject)
    {
        gameObjects.remove(gameObject);
    }

    public GameObject findGameObject(UUID objectId)
    {
        for (GameObject gameObject : gameObjects)
        {
            if (gameObject.getObjectId().equals(objectId))
                return gameObject;
        }

        return null;
    }

    public GameObject getGameObjectOnField(int row, int column)
    {
        for (GameObject gameObject : gameObjects)
        {
            if (gameObject.getObjectRow() == row && gameObject.getObjectColumn() == column)
                return gameObject;
        }

        return null;
    }

    public boolean areObjectsColliding(GameObject gameObject1, GameObject gameObject2)
    {
        if(gameObject1.isCollisionEnabled() && gameObject2.isCollisionEnabled())
            return gameObject1.getObjectRow() == gameObject2.getObjectRow() && gameObject1.getObjectColumn() == gameObject2.getObjectColumn();

        return false;
    }

    public boolean isObjectColliding(GameObject gameObject)
    {
        GameObject other = getGameObjectOnField(gameObject.getObjectRow(), gameObject.getObjectColumn());
        return other != null && areObjectsColliding(gameObject, other);
    }

    public boolean canSetGameObjectOnField(int row, int column)
    {
        if(row >= GameConstants.FIELD_ROWS || column >= GameConstants.FIELD_COLUMNS)
            return false;

        for(int i = 0; i < GameConstants.MOVE_BTN_FIELDS.length; ++i)
        {
            if(GameConstants.MOVE_BTN_FIELDS[i][0] == row && GameConstants.MOVE_BTN_FIELDS[i][1] == column)
                return false;
        }

        GameObject other = getGameObjectOnField(row, column);
        return other == null || !other.isCollisionEnabled();
    }

    public boolean moveGameObject(GameObject gameObject, int row, int column)
    {
        if (canSetGameObjectOnField(row, column))
        {
            gameObject.setObjectRow(row);
            gameObject.setObjectColumn(column);

            gameObject.markRenderStateDirty();
            return true;
        }

        return false;
    }

    public ArrayList<GameObject> getDirtyGameObjets()
    {
        ArrayList<GameObject> dirtyGameObjects = new ArrayList<GameObject>();
        for (GameObject gameObject : gameObjects)
        {
            if (gameObject.isRenderStateDirty())
                dirtyGameObjects.add(gameObject);
        }

        return dirtyGameObjects;
    }
}