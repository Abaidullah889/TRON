
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package tron;

/**
 * The HighScore class represents a player's high score in the TRON game.
 * It stores the player's name and score and provides methods to retrieve these details.
 * 
 * This class is used by the HighScores manager to store and display high scores.
 * 
 * @author 9
 */
public class HighScore {

    /**
     * The name of the player.
     */
    private final String name;

    /**
     * The score achieved by the player.
     */
    private final int score;

    /**
     * Creates a new HighScore with the specified player's name and score.
     * 
     * @param name the name of the player
     * @param score the score achieved by the player
     */
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gets the name of the player.
     * 
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the score of the player.
     * 
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns a string representation of the HighScore.
     * 
     * @return a string in the format "HighScore{name=..., score=...}"
     */
    @Override
    public String toString() {
        return "HighScore{" + "name=" + name + ", score=" + score + '}';
    }
}
