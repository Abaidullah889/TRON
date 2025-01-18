/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package tron;

import javax.swing.*;
import java.sql.SQLException;
import java.awt.*;
import java.util.ArrayList;

/**
 * The GameController class manages the overall flow of the TRON game.
 * It handles navigation between screens, starting the game, and updating high scores.
 * 
 * This class serves as the central hub for coordinating different parts of the game,
 * such as the welcome screen, menu, player setup, and game boards.
 * 
 * @author 9
 */
public class GameController {

    /**
     * The main game window that displays the game board.
     */
    private JFrame gameFrame;

    /**
     * Creates a new GameController and starts by displaying the welcome screen.
     */
    public GameController() {
        showWelcomeScreen();
    }

    /**
     * Displays the welcome screen.
     */
    public void showWelcomeScreen() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(this);
        welcomeScreen.setVisible(true);
    }

    /**
     * Displays the main menu screen.
     */
    public void showMenuScreen() {
        MenuScreen menuScreen = new MenuScreen(this);
        menuScreen.setVisible(true);
    }

    /**
     * Displays the player setup screen.
     */
    public void showPlayerSetupScreen() {
        PlayerSetupScreen playerSetupScreen = new PlayerSetupScreen(this);
        playerSetupScreen.setVisible(true);
    }

    /**
     * Starts the game with the given player details and selected board.
     * 
     * @param player1Name the name of player 1
     * @param player2Name the name of player 2
     * @param player1Color the color chosen by player 1
     * @param player2Color the color chosen by player 2
     * @param selectedBoard the selected game board
     */
    public void startGame(String player1Name, String player2Name, String player1Color, String player2Color, String selectedBoard) {
        if (gameFrame != null) {
            gameFrame.dispose();
        }

        gameFrame = new JFrame("TRON Light Cycle Battle");
        JPanel gameBoard;
        JLabel timeLabel = new JLabel("Game Time: 0 seconds");
        timeLabel.setForeground(Color.WHITE);

        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(Color.GRAY);

        JLabel player1Label = new JLabel(player1Name + " (Color: " + player1Color + ")");
        player1Label.setForeground(parseColor(player1Color));

        JLabel player2Label = new JLabel(player2Name + " (Color: " + player2Color + ")");
        player2Label.setForeground(parseColor(player2Color));

        infoPanel.add(player1Label, BorderLayout.WEST);
        infoPanel.add(player2Label, BorderLayout.EAST);
        infoPanel.add(timeLabel, BorderLayout.SOUTH);

        
        switch (selectedBoard) {
            case "NormalBoard":
                gameBoard = new GameBoard(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            case "OneHurdleBoard":
                gameBoard = new GameBoardWithHurdle(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            case "TwoHurdlesBoard":
                gameBoard = new GameBoardWithTwoHurdles(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            case "FourCornersBoard":
                gameBoard = new GameBoardWithFourCorners(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            case "CentralCrossHurdleBoard":
                gameBoard = new GameBoardWithCentralCrossHurdle(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            case "ThreeVerticalHurdlesBoard":
                gameBoard = new GameBoardWithThreeVerticalHurdles(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            case "TwoParallelHurdlesBoard":
                gameBoard = new GameBoardWithTwoParallelHurdles(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            case "VisibleBoundaryBoard":
                gameBoard = new GameBoardWithVisibleBoundary(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            case "ZigZagHurdlesBoard":
                gameBoard = new GameBoardWithZigZagHurdles(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            case "ThreeSmallCircleHurdlesBoard":
                gameBoard = new GameBoardWithThreeSmallCircleHurdles(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid board selection. Defaulting to GameBoard.");
                gameBoard = new GameBoard(player1Name, player2Name, parseColor(player1Color), parseColor(player2Color), timeLabel, this);
        }

        gameFrame.add(gameBoard, BorderLayout.CENTER);
        gameFrame.add(infoPanel, BorderLayout.SOUTH);

        gameFrame.setSize(800, 600);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    /**
     * Restarts the game by returning to the player setup screen.
     */
    public void restartGame() {
        showPlayerSetupScreen();
    }

    /**
     * Converts a color name to a {@code Color} object.
     * 
     * @param colorName the name of the color
     * @return the corresponding {@code Color} object, or black if not recognized
     */
    private Color parseColor(String colorName) {
        switch (colorName.toLowerCase()) {
            case "red": return Color.RED;
            case "green": return Color.GREEN;
            case "blue": return Color.BLUE;
            case "yellow": return Color.YELLOW;
            case "cyan": return Color.CYAN;
            case "magenta": return Color.MAGENTA;
            case "orange": return Color.ORANGE;
            case "pink": return Color.PINK;
            case "purple": return new Color(128, 0, 128);
            case "gray": return Color.GRAY;
            case "black": return Color.BLACK;
            case "white": return Color.WHITE;
            case "brown": return new Color(139, 69, 19);
            case "lime": return new Color(50, 205, 50);
            case "teal": return new Color(0, 128, 128);
            case "navy": return new Color(0, 0, 128);
            case "maroon": return new Color(128, 0, 0);
            case "olive": return new Color(128, 128, 0);
            default: return Color.BLACK;
        }
    }

    /**
     * Updates the high score of the specified player.
     * 
     * @param playerName the name of the player whose score is being updated
     */
    public void updateHighScores(String playerName) {
        try {
            HighScores highScores = new HighScores(10); // Top 10 scores
            highScores.putHighScore(playerName, 1); // Increment winner's score by 1
        } catch (SQLException e) {
            System.out.print("Connection failed");
        }
    }

    /**
     * Displays the high scores in a separate window.
     */
    public void showHighScores() {
        try {
            
            HighScores highScores = new HighScores(10);
            ArrayList<HighScore> scores = highScores.getHighScores();

            
            JFrame highScoresFrame = new JFrame("High Scores");
            highScoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            highScoresFrame.setSize(400, 400);
            highScoresFrame.setLayout(new BorderLayout());
            highScoresFrame.setLocationRelativeTo(null);

            
            JPanel highScoresPanel = new JPanel();
            highScoresPanel.setLayout(new BoxLayout(highScoresPanel, BoxLayout.Y_AXIS));
            highScoresPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            
            JLabel titleLabel = new JLabel("High Scores");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            highScoresPanel.add(titleLabel);

            
            for (int i = 0; i < scores.size(); i++) {
                HighScore hs = scores.get(i);
                JLabel scoreLabel = new JLabel((i + 1) + ". " + hs.getName() + " - " + hs.getScore());
                scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                highScoresPanel.add(scoreLabel);
            }

            
            highScoresFrame.add(highScoresPanel, BorderLayout.CENTER);

           
            highScoresFrame.setVisible(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading high scores.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
