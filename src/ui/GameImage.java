package ui;

import javax.swing.*;

public class GameImage extends JLabel
{
    private final ImageIcon imageIcon;

    public GameImage(ImageIcon imageIcon)
    {
        super(imageIcon);
        this.imageIcon = imageIcon;
    }

    public ImageIcon getImageIcon()
    {
        return imageIcon;
    }
}