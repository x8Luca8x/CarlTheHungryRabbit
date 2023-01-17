package game;

import java.util.UUID;

public class GameObject
{
    private int objectRow;
    private int objectColumn;

    private boolean bIsCollisionEnabled = true;
    private boolean bIsRenderStateDirty = false;

    private final UUID objectId;

    public GameObject()
    {
        objectId = UUID.randomUUID();
    }

    public UUID getObjectId()
    {
        return objectId;
    }

    public int getObjectRow()
    {
        return objectRow;
    }

    public int getObjectColumn()
    {
        return objectColumn;
    }

    public void setObjectRow(int objectRow)
    {
        this.objectRow = objectRow;
    }

    public void setObjectColumn(int objectColumn)
    {
        this.objectColumn = objectColumn;
    }

    public boolean isCollisionEnabled()
    {
        return bIsCollisionEnabled;
    }

    public void setIsCollisionEnabled(boolean bIsCollisionEnabled)
    {
        this.bIsCollisionEnabled = bIsCollisionEnabled;
    }

    public boolean isRenderStateDirty()
    {
        return bIsRenderStateDirty;
    }

    public void markRenderStateDirty()
    {
        bIsRenderStateDirty = true;
    }

    public void markRenderStateClean()
    {
        bIsRenderStateDirty = false;
    }

    public void moveUp()
    {
        getGameMode().getGameObjectManager().moveGameObject(this, objectRow - 1, objectColumn);
    }

    public void moveDown()
    {
        getGameMode().getGameObjectManager().moveGameObject(this, objectRow + 1, objectColumn);
    }

    public void moveLeft()
    {
        getGameMode().getGameObjectManager().moveGameObject(this, objectRow, objectColumn - 1);
    }

    public void moveRight()
    {
        getGameMode().getGameObjectManager().moveGameObject(this, objectRow, objectColumn + 1);
    }

    public GameMode getGameMode()
    {
        return GameMode.getGameModeFromForm();
    }
}