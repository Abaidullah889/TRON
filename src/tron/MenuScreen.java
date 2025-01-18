/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


package tron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MenuScreen class shows the main menu of the TRON game.
 * It includes buttons to start the game, view high scores, or exit the game.
 * This screen is displayed after the welcome screen and serves as the game's hub.
 * 
 * @author 9
 */
public class MenuScreen extends JFrame {

    /**
     * The game controller that handles navigation between screens.
     */
    private final GameController controller;

    /**
     * Creates the menu screen for the TRON game.
     * 
     * @param controller the GameController used to navigate between screens
     */
    public MenuScreen(GameController controller) {
        this.controller = controller;
        initUI();
    }

    /**
     * Sets up the user interface for the menu.
     * <p>
     * The menu includes buttons for starting the game, viewing high scores,
     * and exiting the application.
     * </p>
     */
    private void initUI() {
        setTitle("TRON Menu");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);

        panel.add(Box.createVerticalGlue()); 

        JButton startGameButton = createMenuButton("Start Game");
        startGameButton.addActionListener(new StartGameButtonActionListener());
        panel.add(startGameButton);

        panel.add(Box.createRigidArea(new Dimension(0, 20))); 

        JButton highScoresButton = createMenuButton("High Scores");
        highScoresButton.addActionListener(new HighScoresButtonActionListener());
        panel.add(highScoresButton);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton exitButton = createMenuButton("Exit");
        exitButton.addActionListener(new ExitButtonActionListener());
        panel.add(exitButton);

        panel.add(Box.createVerticalGlue());
        

    }

    /**
     * Creates a button for the menu.
     * 
     * @param text the text displayed on the button
     * @return the configured JButton
     */
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setPreferredSize(new Dimension(200, 40));
        return button;
    }

    /**
     * Handles the "Start Game" button action.
     * This class navigates to the player setup screen.
     */
    class StartGameButtonActionListener implements ActionListener {

        /**
         * Invoked when the "Start Game" button is clicked.
         * 
         * @param e the event triggered by the button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the menu screen
            controller.showPlayerSetupScreen();
        }
    }

    /**
     * Handles the "High Scores" button action.
     * This class displays the high scores screen.
     */
    class HighScoresButtonActionListener implements ActionListener {

        /**
         * Invoked when the "High Scores" button is clicked.
         * 
         * @param e the event triggered by the button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.showHighScores();
        }
    }

    /**
     * Handles the "Exit" button action.
     * This class closes the application.
     */
    class ExitButtonActionListener implements ActionListener {

        /**
         * Invoked when the "Exit" button is clicked.
         * 
         * @param e the event triggered by the button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0); 
        }
    }
}
