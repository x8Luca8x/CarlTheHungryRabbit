package ui;

import ui.forms.FormManager;

public class UIManager
{
    private static final UIManager INSTANCE = new UIManager();

    private final GameWindow window = new GameWindow();
    private final FormManager formManager = new FormManager();

    private UIManager()
    {

    }

    public static UIManager getInstance()
    {
        return INSTANCE;
    }

    public FormManager getFormManager()
    {
        return formManager;
    }

    public GameWindow getWindow()
    {
        return window;
    }
}
