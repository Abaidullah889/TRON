
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package tron;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;

/**
 * The HighScores class manages the high score records for the TRON game.
 * It connects to a MySQL database to store, update, retrieve, and delete scores.
 * 
 * This class ensures high scores are stored persistently and provides methods
 * to add new scores, update existing ones, and retrieve the top scores.
 * 
 * @author 9
 */
public class HighScores {

    /**
     * Prepared statement for inserting a new high score.
     */
    private final PreparedStatement insertStatement;

    /**
     * Prepared statement for updating an existing high score.
     */
    private final PreparedStatement updateStatement;

    /**
     * Prepared statement for deleting high scores with a specific value.
     */
    private final PreparedStatement deleteStatement;

    /**
     * Prepared statement for selecting a specific high score by name.
     */
    private final PreparedStatement selectStatement;

    /**
     * Connection to the database.
     */
    private final Connection connection;

    /**
     * Creates a new HighScores manager with a specified maximum number of scores.
     * Sets up the database connection and prepares SQL statements.
     * 
     * @param maxScores the maximum number of high scores to manage (not yet used)
     * @throws SQLException if there is an issue connecting to the database
     */
    public HighScores(int maxScores) throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "@Jad28152");
        connectionProps.put("serverTimezone", "UTC");

        String dbURL = "jdbc:mysql://localhost:3306/highscores";
        connection = DriverManager.getConnection(dbURL, connectionProps);

        String insertQuery = "INSERT INTO HIGHSCORES (NAME, SCORE) VALUES (?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);

        String updateQuery = "UPDATE HIGHSCORES SET SCORE = SCORE + ? WHERE NAME = ?";
        updateStatement = connection.prepareStatement(updateQuery);

        String deleteQuery = "DELETE FROM HIGHSCORES WHERE SCORE = ?";
        deleteStatement = connection.prepareStatement(deleteQuery);

        String selectQuery = "SELECT SCORE FROM HIGHSCORES WHERE NAME = ?";
        selectStatement = connection.prepareStatement(selectQuery);
    }

    /**
     * Retrieves the list of high scores, sorted in descending order.
     * 
     * @return an ArrayList of HighScore objects representing the high scores
     * @throws SQLException if there is an issue with the database query
     */
    public ArrayList<HighScore> getHighScores() throws SQLException {
        String query = "SELECT * FROM HIGHSCORES ORDER BY SCORE DESC";
        ArrayList<HighScore> highScores = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            int score = results.getInt("SCORE");
            highScores.add(new HighScore(name, score));
        }
        sortHighScores(highScores);
        return highScores;
    }

    /**
     * Adds a new high score or updates an existing one.
     * 
     * @param name the name of the player
     * @param score the score to add
     * @throws SQLException if there is an issue with the database query
     */
    public void putHighScore(String name, int score) throws SQLException {
        // Check if the player already exists
        selectStatement.setString(1, name);
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            updateScore(name, score);
        } else {
            insertScore(name, score);
        }
    }

    /**
     * Updates an existing player's score by adding an increment.
     * 
     * @param name the name of the player
     * @param increment the amount to add to the existing score
     * @throws SQLException if there is an issue with the database query
     */
    private void updateScore(String name, int increment) throws SQLException {
        updateStatement.setInt(1, increment);
        updateStatement.setString(2, name);
        updateStatement.executeUpdate();
    }

    /**
     * Inserts a new player's score into the database.
     * 
     * @param name the name of the player
     * @param score the player's score
     * @throws SQLException if there is an issue with the database query
     */
    private void insertScore(String name, int score) throws SQLException {
        insertStatement.setString(1, name);
        insertStatement.setInt(2, score);
        insertStatement.executeUpdate();
    }

    /**
     * Sorts the high scores in descending order.
     * 
     * @param highScores the list of high scores to sort
     */
    private void sortHighScores(ArrayList<HighScore> highScores) {
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore t, HighScore t1) {
                return t1.getScore() - t.getScore();
            }
        });
    }

    /**
     * Deletes all high scores with a specific value.
     * 
     * @param score the score value to delete
     * @throws SQLException if there is an issue with the database query
     */
    private void deleteScores(int score) throws SQLException {
        deleteStatement.setInt(1, score);
        deleteStatement.executeUpdate();
    }
}
