
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package tron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The {@code GameBoard} class represents the main game board for the TRON Light-Cycle Battle game.
 * It manages the game logic, including player movement, collision detection, and game state updates.
 * The class uses a {@code JPanel} for rendering the game area and motorcycles.
 * 
 * 
 * This is the default board with no internal hurdles, focusing purely on player
 * trail collisions and boundary avoidance.
 * 
 * 
 * @author 9
 */
public class GameBoard extends JPanel {

    /**
     * The width of the game board.
     */
    private final int BOARD_WIDTH = 800;

    /**
     * The height of the game board.
     */
    private final int BOARD_HEIGHT = 600;

    /**
     * The size of each step for motorcycle movement.
     */
    private final int DOT_SIZE = 5;

    /**
     * The list of motorcycles participating in the game.
     */
    private ArrayList<Motorcycle> motorcycles;

    /**
     * Timer to manage game updates.
     */
    private Timer gameTimer;

    /**
     * Tracks the elapsed game time in milliseconds.
     */
    private int gameTime;

    /**
     * A label to display the game time.
     */
    private JLabel timeLabel;

    /**
     * The game controller responsible for managing game flow and transitions.
     */
    private GameController controller;

    /**
     * Constructs a {@code GameBoard} instance.
     * 
     * @param player1Name the name of player 1
     * @param player2Name the name of player 2
     * @param player1Color the color of player 1's motorcycle
     * @param player2Color the color of player 2's motorcycle
     * @param timeLabel a label to display the game time
     * @param controller the controller to manage game transitions
     */
    public GameBoard(String player1Name, String player2Name, Color player1Color, Color player2Color, JLabel timeLabel, GameController controller) {
        this.timeLabel = timeLabel;
        this.controller = controller;
        motorcycles = new ArrayList<>();
        motorcycles.add(new Motorcycle(200, 300, "RIGHT", player1Color, player1Name));
        motorcycles.add(new Motorcycle(600, 300, "LEFT", player2Color, player2Name));

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyHandler());

        initGame();
    }

    /**
     * Initializes the game, starting the timer and resetting the game state.
     */
    private void initGame() {
        gameTime = 0;
        gameTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameTime++;
                timeLabel.setText("Game Time: " + gameTime / 10.0 + " seconds");

                for (Motorcycle motorcycle : motorcycles) {
                    motorcycle.move(DOT_SIZE);
                    motorcycle.addTrail(); // Record trail positions
                }
                repaint();
                checkCollisions();
            }
        });
        gameTimer.start();
    }

    /**
     * Checks for collisions between players, boundaries, and trails.
     */
    private void checkCollisions() {
        Motorcycle p1 = motorcycles.get(0);
        Motorcycle p2 = motorcycles.get(1);

        // Check head-to-head collision
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            gameTimer.stop();
            showGameOverDialog("Game is Draw!");
            return;
        }

        // Check boundary collisions
        for (Motorcycle motorcycle : motorcycles) {
            if (motorcycle.isOutOfBounds(getWidth(), getHeight())) {
                gameTimer.stop();
                Motorcycle winner = (motorcycle == p1) ? p2 : p1;
                showGameOverDialog(motorcycle.getName() + " lost (Out of Bounds)!");
                controller.updateHighScores(winner.getName());
                return;
            }
        }

        // Check trail collisions
        for (Motorcycle motorcycle : motorcycles) {
            Motorcycle otherMotorcycle = (motorcycle == p1) ? p2 : p1;
            for (Point trailPoint : otherMotorcycle.getTrail()) {
                if (trailPoint.x == motorcycle.getX() && trailPoint.y == motorcycle.getY()) {
                    gameTimer.stop();
                    Motorcycle winner = (motorcycle == p1) ? p2 : p1;
                    showGameOverDialog(motorcycle.getName() + " lost (Trail Collision)!");
                    controller.updateHighScores(winner.getName());
                    return;
                }
            }
        }
    }

    /**
     * Displays a game-over dialog with options to restart or exit the game.
     * 
     * @param message the game-over message
     */
    private void showGameOverDialog(String message) {
        JFrame gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setSize(300, 150);
        gameOverFrame.setLayout(new BorderLayout());
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setLocationRelativeTo(null);

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gameOverFrame.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton playAgainButton = new JButton("Play Again");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(playAgainButton);
        buttonPanel.add(exitButton);
        gameOverFrame.add(buttonPanel, BorderLayout.SOUTH);

        playAgainButton.addActionListener(e -> {
            gameOverFrame.dispose();
            controller.restartGame();
        });

        exitButton.addActionListener(e -> System.exit(0));

        gameOverFrame.setVisible(true);
    }

    /**
     * Renders the game board, including motorcycles and their trails.
     * 
     * @param g the Graphics object used for rendering
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Motorcycle motorcycle : motorcycles) {
            motorcycle.draw((Graphics2D) g);
        }
    }

    /**
     * Handles keyboard input to control motorcycle direction.
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_W) motorcycles.get(0).setDirection("UP");
            if (key == KeyEvent.VK_S) motorcycles.get(0).setDirection("DOWN");
            if (key == KeyEvent.VK_A) motorcycles.get(0).setDirection("LEFT");
            if (key == KeyEvent.VK_D) motorcycles.get(0).setDirection("RIGHT");

            if (key == KeyEvent.VK_UP) motorcycles.get(1).setDirection("UP");
            if (key == KeyEvent.VK_DOWN) motorcycles.get(1).setDirection("DOWN");
            if (key == KeyEvent.VK_LEFT) motorcycles.get(1).setDirection("LEFT");
            if (key == KeyEvent.VK_RIGHT) motorcycles.get(1).setDirection("RIGHT");
        }
    }
}
