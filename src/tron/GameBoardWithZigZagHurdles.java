/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


package tron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The GameBoardWithZigZagHurdles class represents a game board with a zigzag pattern of hurdles.
 * This board includes obstacles that players must avoid while playing.
 * 
 * The class manages player movement, collision detection, and rendering the game board,
 * hurdles, and motorcycles.
 * 
 * @author 9
 */
public class GameBoardWithZigZagHurdles extends JPanel {

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
     * The game timer that updates the game state periodically.
     */
    private Timer gameTimer;

    /**
     * The elapsed game time.
     */
    private int gameTime;

    /**
     * Label to display the game time.
     */
    private JLabel timeLabel;

    /**
     * The controller to manage game flow and transitions.
     */
    private GameController controller;

    // Define hurdles for the zigzag pattern
    private final Rectangle hurdle1;
    private final Rectangle hurdle2;
    private final Rectangle hurdle3;
    private final Rectangle hurdle4;
    private final Rectangle hurdle5;

    /**
     * Creates a new game board with zigzag hurdles.
     * 
     * @param player1Name the name of player 1
     * @param player2Name the name of player 2
     * @param player1Color the color of player 1's motorcycle
     * @param player2Color the color of player 2's motorcycle
     * @param timeLabel the label to display the game time
     * @param controller the controller to manage game transitions
     */
    public GameBoardWithZigZagHurdles(String player1Name, String player2Name, Color player1Color, Color player2Color, JLabel timeLabel, GameController controller) {
        this.timeLabel = timeLabel;
        this.controller = controller;
        motorcycles = new ArrayList<>();
        motorcycles.add(new Motorcycle(100, 200, "RIGHT", player1Color, player1Name));
        motorcycles.add(new Motorcycle(600, 300, "LEFT", player2Color, player2Name));

        
        hurdle1 = new Rectangle(150, 100, 40, 40);
        hurdle2 = new Rectangle(250, 200, 40, 40);
        hurdle3 = new Rectangle(350, 300, 40, 40);
        hurdle4 = new Rectangle(450, 200, 40, 40);
        hurdle5 = new Rectangle(550, 100, 40, 40);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyHandler());

        initGame();
    }

    /**
     * Initializes the game, starting the timer and setting initial values.
     */
    private void initGame() {
        gameTime = 0;
        gameTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameTime++;
                timeLabel.setText("Game Time: " + (gameTime / 10.0) + " seconds");

                for (Motorcycle motorcycle : motorcycles) {
                    motorcycle.move(DOT_SIZE);
                    motorcycle.addTrail();
                }
                repaint();
                checkCollisions();
            }
        });
        gameTimer.start();
    }

    /**
     * Checks for collisions between motorcycles, hurdles, trails, and the game boundaries.
     */
    private void checkCollisions() {
        Motorcycle p1 = motorcycles.get(0);
        Motorcycle p2 = motorcycles.get(1);

        // Head-to-head collision
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            gameTimer.stop();
            showGameOverDialog("Game is Draw!");
            return;
        }

        // Boundary collision
        for (Motorcycle motorcycle : motorcycles) {
            if (motorcycle.isOutOfBounds(getWidth(), getHeight())) {
                gameTimer.stop();
                Motorcycle winner = (motorcycle == p1) ? p2 : p1;
                showGameOverDialog(motorcycle.getName() + " lost (Out of Bounds)!");
                controller.updateHighScores(winner.getName());
                return;
            }
        }

        // Hurdle collision
        for (Motorcycle motorcycle : motorcycles) {
            if (hurdle1.contains(motorcycle.getX(), motorcycle.getY()) ||
                hurdle2.contains(motorcycle.getX(), motorcycle.getY()) ||
                hurdle3.contains(motorcycle.getX(), motorcycle.getY()) ||
                hurdle4.contains(motorcycle.getX(), motorcycle.getY()) ||
                hurdle5.contains(motorcycle.getX(), motorcycle.getY())) {
                gameTimer.stop();
                Motorcycle winner = (motorcycle == p1) ? p2 : p1;
                showGameOverDialog(motorcycle.getName() + " lost (Hit the Hurdle)!");
                controller.updateHighScores(winner.getName());
                return;
            }
        }

        // Trail collision
        for (Motorcycle motorcycle : motorcycles) {
            Motorcycle otherMotorcycle = (motorcycle == p1) ? p2 : p1;
            for (Point trailPoint : otherMotorcycle.getTrail()) {
                if (trailPoint.x == motorcycle.getX() && trailPoint.y == motorcycle.getY()) {
                    gameTimer.stop();
                    showGameOverDialog(motorcycle.getName() + " lost (Trail Collision)!");
                    controller.updateHighScores(otherMotorcycle.getName());
                    return;
                }
            }
        }
    }

    /**
     * Displays the game-over dialog with a message and options to restart or exit.
     * 
     * @param message the game-over message to display
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
     * Draws the game board, including hurdles, motorcycles, and their trails.
     * 
     * @param g the Graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        g.setColor(Color.GRAY);
        g.fillRect(hurdle1.x, hurdle1.y, hurdle1.width, hurdle1.height);
        g.fillRect(hurdle2.x, hurdle2.y, hurdle2.width, hurdle2.height);
        g.fillRect(hurdle3.x, hurdle3.y, hurdle3.width, hurdle3.height);
        g.fillRect(hurdle4.x, hurdle4.y, hurdle4.width, hurdle4.height);
        g.fillRect(hurdle5.x, hurdle5.y, hurdle5.width, hurdle5.height);

        
        for (Motorcycle motorcycle : motorcycles) {
            motorcycle.draw((Graphics2D) g);
        }
    }

    /**
     * Handles key presses to change the direction of motorcycles.
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
