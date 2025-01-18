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
 * The PlayerSetupScreen class allows players to set up their details before
 * starting the game. Players can enter their names, choose their colors, and
 * select the game board.
 * 
 * This screen validates player input and ensures unique names and colors
 * before proceeding to the game.
 * 
 * @author 9
 */
public class PlayerSetupScreen extends JFrame {

    /**
     * The controller that manages screen transitions and starts the game.
     */
    private final GameController controller;

    /**
     * Text fields for entering player names.
     */
    private JTextField player1NameField;
    private JTextField player2NameField;

    /**
     * Combo boxes for selecting player colors.
     */
    private JComboBox<String> player1ColorCombo;
    private JComboBox<String> player2ColorCombo;

    /**
     * Combo box for selecting the game board type.
     */
    private JComboBox<String> difficultyCombo;

    /**
     * Creates the player setup screen.
     * 
     * @param controller the game controller to manage transitions and start the game
     */
    public PlayerSetupScreen(GameController controller) {
        this.controller = controller;
        initUI();
    }

    /**
     * Initializes the user interface for player setup.
     * <p>
     * This includes input fields for player names, dropdowns for selecting colors
     * and game boards, and a button to start the game.
     * </p>
     */
    private void initUI() {
        setTitle("Player Setup");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        add(panel);

        panel.add(new JLabel("Player 1 Name:"));
        player1NameField = new JTextField();
        panel.add(player1NameField);

        panel.add(new JLabel("Player 1 Color:"));
        player1ColorCombo = new JComboBox<>(getAllColors());
        panel.add(player1ColorCombo);

        panel.add(new JLabel("Player 2 Name:"));
        player2NameField = new JTextField();
        panel.add(player2NameField);

        panel.add(new JLabel("Player 2 Color:"));
        player2ColorCombo = new JComboBox<>(getAllColors());
        panel.add(player2ColorCombo);

        panel.add(new JLabel("Select Game Board:"));
        difficultyCombo = new JComboBox<>(getAllBoards());
        panel.add(difficultyCombo);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new StartGameButtonActionListener());
        panel.add(startGameButton);
    }

    /**
     * Provides a list of available colors for players to choose from.
     * 
     * @return an array of color names
     */
    private String[] getAllColors() {
        return new String[] {
            "Red", "Green", "Blue", "Yellow", "Cyan", "Magenta",
            "Orange", "Pink", "Purple", "Gray", "Black", "White",
            "Brown", "Lime", "Teal", "Navy", "Maroon", "Olive"
        };
    }

    /**
     * Provides a list of available game boards to choose from.
     * 
     * @return an array of game board names
     */
    private String[] getAllBoards() {
        return new String[] {
            "NormalBoard",
            "OneHurdleBoard",
            "TwoHurdlesBoard",
            "FourCornersBoard",
            "CentralCrossHurdleBoard",
            "ThreeVerticalHurdlesBoard",
            "TwoParallelHurdlesBoard",
            "VisibleBoundaryBoard",
            "ZigZagHurdlesBoard",
            "ThreeSmallCircleHurdlesBoard",
        };
    }

    /**
     * Handles the "Start Game" button click event.
     * This inner class validates player input and starts the game if all conditions are met.
     */
    private class StartGameButtonActionListener implements ActionListener {

        /**
         * Validates player input and starts the game.
         * 
         * @param e the event triggered by clicking the "Start Game" button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String player1Name = player1NameField.getText().trim();
            String player2Name = player2NameField.getText().trim();
            String player1Color = (String) player1ColorCombo.getSelectedItem();
            String player2Color = (String) player2ColorCombo.getSelectedItem();
            String selectedBoard = (String) difficultyCombo.getSelectedItem();

            if (player1Name.isEmpty() || player2Name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the names of the players.");
                return;
            }

            if (player1Name.equalsIgnoreCase(player2Name)) {
                JOptionPane.showMessageDialog(null, "Players' names cannot be the same.");
                return;
            }

            if (player1Color.equals(player2Color)) {
                JOptionPane.showMessageDialog(null, "Players must choose different colors.");
                return;
            }

            controller.startGame(player1Name, player2Name, player1Color, player2Color, selectedBoard);
            dispose();
        }
    }
}
