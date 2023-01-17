import ui.UIManager;
import ui.forms.MainMenuForm;

public class Main
{
    public static void main(String[] args)
    {
        UIManager.getInstance().getFormManager().changeForm(MainMenuForm.class);
    }
}
