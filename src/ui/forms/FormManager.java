package ui.forms;

import ui.GamePanel;
import ui.GameWindow;
import ui.UIManager;

public class FormManager
{
    private FormBase currentForm = null;

    public void changeForm(FormBase newForm)
    {
        if (currentForm != null)
            currentForm.onClose();

        GameWindow window = UIManager.getInstance().getWindow();

        currentForm = newForm;

        window.changeContentPane((GamePanel)currentForm);

        window.getGamePanelInstance().revalidate();
        window.getGamePanelInstance().repaint();

        currentForm.onShow();
    }

    public void changeForm(Class newFormClass)
    {
        try
        {
            FormBase newForm = (FormBase)GameWindow.initNewPanel(newFormClass);
            changeForm(newForm);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public FormBase getCurrentForm()
    {
        return currentForm;
    }
}
