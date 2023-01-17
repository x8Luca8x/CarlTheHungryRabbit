package ui.forms;

import ui.GamePanel;
import ui.UIManager;

import javax.swing.*;
import java.awt.*;

public class MainMenuForm extends GamePanel implements FormBase
{
    @Override
    public void initializeComponents()
    {
        setBackground(Color.DARK_GRAY);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.DARK_GRAY);

        JButton newGameButton = new JButton("Spiel starten");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(e -> {
            UIManager.getInstance().getFormManager().changeForm(GameForm.class);
        });

        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton exitButton = new JButton("Beenden");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> {
            UIManager.getInstance().getWindow().getFrame().dispose();
        });

        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void onResize(int newWidth, int newHeight)
    {
        setBounds(0, 0, newWidth, newHeight);
    }

    @Override
    public void onShow()
    {

    }

    @Override
    public void onClose()
    {

    }
}
