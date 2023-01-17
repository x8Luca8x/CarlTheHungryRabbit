package ui;

import javax.swing.*;

public abstract class GamePanel extends JPanel
{
    public abstract void initializeComponents();
    public abstract void onResize(int newWidth, int newHeight);
}
