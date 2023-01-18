package game;

import javax.swing.*;

public class GameConstants
{
    public enum EMoveBtnType
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static final int FIELD_ROWS = 5;
    public static final int FIELD_COLUMNS = 9;

    public static final int[][] MOVE_BTN_FIELDS = new int[][]
    {
        { 3, 4 },
        { 4, 3 },
        { 4, 4 },
        { 4, 5 },
    };

    public static boolean isMoveBtnField(int row, int column)
    {
        for (int[] moveBtnField : MOVE_BTN_FIELDS)
        {
            if (moveBtnField[0] == row && moveBtnField[1] == column)
                return true;
        }

        return false;
    }

    public static EMoveBtnType getMoveBtnType(int row, int column)
    {
        if (MOVE_BTN_FIELDS[0][0] == row && MOVE_BTN_FIELDS[0][1] == column)
            return EMoveBtnType.UP;
        else if (MOVE_BTN_FIELDS[1][0] == row && MOVE_BTN_FIELDS[1][1] == column)
            return EMoveBtnType.LEFT;
        else if (MOVE_BTN_FIELDS[2][0] == row && MOVE_BTN_FIELDS[2][1] == column)
            return EMoveBtnType.DOWN;
        else if (MOVE_BTN_FIELDS[3][0] == row && MOVE_BTN_FIELDS[3][1] == column)
            return EMoveBtnType.RIGHT;

        return null;
    }

    public static JButton getMoveBtn(EMoveBtnType moveBtnType)
    {
        return switch (moveBtnType)
                {
                    case UP -> new JButton("UP");
                    case DOWN -> new JButton("DOWN");
                    case LEFT -> new JButton("LEFT");
                    case RIGHT -> new JButton("RIGHT");
                };

    }
}
