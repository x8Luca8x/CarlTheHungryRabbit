package game;

import ui.UIManager;
import ui.forms.GameForm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class GameObjectManager
{
    public record RandomLocationResult(int row, int column) { }

    private final ArrayList<GameObject> gameObjects = new ArrayList<>();

    private final GameMode owningGameMode;

    public GameObjectManager(GameMode owningGameMode)
    {
        this.owningGameMode = owningGameMode;
    }

    public ArrayList<GameObject> getGameObjects()
    {
        return gameObjects;
    }

    public ArrayList<GameObject> getAllGameObjectsOfClass(Class gameObjectClass)
    {
        ArrayList<GameObject> result = new ArrayList<>();
        for(GameObject gameObject : gameObjects)
        {
            if(gameObject.getClass() == gameObjectClass || gameObject.getClass().isAssignableFrom(gameObjectClass))
                result.add(gameObject);
        }

        return result;
    }

    public GameObject createGameObject(Class gameObjectClass, int row, int column)
    {
        try
        {
            if(!GameObject.class.isAssignableFrom(gameObjectClass) || !canSetGameObjectOnField(row, column))
                return null;

            GameObject gameObject = (GameObject)gameObjectClass.getConstructor().newInstance();

            gameObject.setObjectRow(row);
            gameObject.setObjectColumn(column);

            gameObjects.add(gameObject);
            renderGameObjects();

            return gameObject;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public GameObject createGameObject(Class gameObjectClass)
    {
        return createGameObject(gameObjectClass, 0, 0);
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

    public boolean areObjectsOverlapping(GameObject object1, GameObject object2)
    {
        return object1.getObjectRow() == object2.getObjectRow() && object1.getObjectColumn() == object2.getObjectColumn();
    }

    public boolean isObjectOverlappingAny(GameObject object)
    {
        for(GameObject gameObject : gameObjects)
        {
            if(gameObject != object && areObjectsOverlapping(object, gameObject))
                return true;
        }

        return false;
    }

    public boolean areObjectsColliding(GameObject gameObject1, GameObject gameObject2)
    {
        if(gameObject1.isCollisionEnabled() && gameObject2.isCollisionEnabled())
            return areObjectsOverlapping(gameObject1, gameObject2);

        return false;
    }

    public boolean isObjectColliding(GameObject gameObject)
    {
        GameObject other = getGameObjectOnField(gameObject.getObjectRow(), gameObject.getObjectColumn());
        return other != null && areObjectsColliding(gameObject, other);
    }

    public boolean canSetGameObjectOnField(int row, int column)
    {
        if (!canSetGameObjectOnFieldHelper(row, column))
            return false;

        GameObject other = getGameObjectOnField(row, column);
        return other == null || !other.isCollisionEnabled();
    }

    private boolean canSetGameObjectOnFieldHelper(int row, int column)
    {
        if(row < 0 || column < 0 || row >= GameConstants.FIELD_ROWS || column >= GameConstants.FIELD_COLUMNS)
            return false;

        for(int i = 0; i < GameConstants.MOVE_BTN_FIELDS.length; ++i)
        {
            if(GameConstants.MOVE_BTN_FIELDS[i][0] == row && GameConstants.MOVE_BTN_FIELDS[i][1] == column)
                return false;
        }

        return true;
    }

    public boolean canSetGameObjectOnField(GameObject gameObject, int row, int column)
    {
        if (!canSetGameObjectOnFieldHelper(row, column))
            return false;
        if(!gameObject.isCollisionEnabled())
            return true;

        GameObject other = getGameObjectOnField(row, column);
        return other == null || !other.isCollisionEnabled();
    }

    public boolean moveGameObject(GameObject gameObject, int row, int column)
    {
        if (canSetGameObjectOnField(gameObject, row, column))
        {
            gameObject.setObjectRow(row);
            gameObject.setObjectColumn(column);

            gameObject.markRenderStateDirty();
            owningGameMode.onGameObjectMoved(gameObject);

            renderGameObjects();
            return true;
        }

        return false;
    }

    public ArrayList<GameObject> getDirtyGameObjets()
    {
        ArrayList<GameObject> dirtyGameObjects = new ArrayList<>();
        for (GameObject gameObject : gameObjects)
        {
            if (gameObject.isRenderStateDirty())
                dirtyGameObjects.add(gameObject);
        }

        return dirtyGameObjects;
    }

    public RandomLocationResult getRandomLocation()
    {
        int row;
        int column;

        do
        {
            row = (int)(Math.random() * (GameConstants.FIELD_ROWS - 1));
            column = (int)(Math.random() * (GameConstants.FIELD_COLUMNS - 1));
        } while(!canSetGameObjectOnField(row, column));

        return new RandomLocationResult(row, column);
    }

    public RandomLocationResult getEmptyRandomLocation()
    {
        int row;
        int column;

        do
        {
            row = (int)(Math.random() * (GameConstants.FIELD_ROWS - 1));
            column = (int)(Math.random() * (GameConstants.FIELD_COLUMNS - 1));
        } while(!canSetGameObjectOnField(row, column) || getGameObjectOnField(row, column) != null);

        return new RandomLocationResult(row, column);
    }

    private JComponent getRenderComponentForField(int row, int column)
    {
        GameObject gameObject = getGameObjectOnField(row, column);
        if (gameObject != null)
            return gameObject.getRenderComponent();

        if(GameConstants.isMoveBtnField(row, column))
        {
            var moveBtnType = GameConstants.getMoveBtnType(row, column);
            if(moveBtnType != null)
            {
                JButton btn = GameConstants.getMoveBtn(moveBtnType);
                switch (moveBtnType)
                {
                    case UP -> btn.addActionListener(e -> owningGameMode.getPlayerCharacter().moveUp());
                    case DOWN -> btn.addActionListener(e -> owningGameMode.getPlayerCharacter().moveDown());
                    case LEFT -> btn.addActionListener(e -> owningGameMode.getPlayerCharacter().moveLeft());
                    case RIGHT -> btn.addActionListener(e -> owningGameMode.getPlayerCharacter().moveRight());
                }

                return btn;
            }
        }

        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#1cca16"));

        return panel;
    }

    private ArrayList<JComponent> getRenderComponents()
    {
        ArrayList<JComponent> renderComponents = new ArrayList<>();
        for (int row = 0; row < GameConstants.FIELD_ROWS; ++row)
        {
            for (int column = 0; column < GameConstants.FIELD_COLUMNS; ++column)
                renderComponents.add(getRenderComponentForField(row, column));
        }

        return renderComponents;
    }

    public boolean renderGameObjects()
    {
        if(!canRenderObjects())
            return false;

        GameForm gameForm = GameWrapper.getGameForm();
        gameForm.removeAll();

        ArrayList<JComponent> renderComponents = getRenderComponents();
        for (JComponent renderComponent : renderComponents)
            gameForm.add(renderComponent);

        gameForm.revalidate();
        gameForm.repaint();

        // TODO: Remove dirty state
        ArrayList<GameObject> dirtyGameObjects = getDirtyGameObjets();
        for (GameObject dirtyGameObject : dirtyGameObjects)
            dirtyGameObject.markRenderStateClean();

        return true;
    }

    private boolean canRenderObjects()
    {
        return UIManager.getInstance().getFormManager().getCurrentForm() instanceof GameForm;
    }
}