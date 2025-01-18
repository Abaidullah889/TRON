/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package tron;

import java.awt.*;
import java.util.ArrayList;

/**
 * The Motorcycle class represents a player's motorcycle in the TRON game.
 * It keeps track of the motorcycle's position, direction, trail, and color.
 * The motorcycle moves on the game board and leaves a trail behind it.
 * 
 * This class provides methods for moving the motorcycle, adding to its trail,
 * and checking if it has gone out of bounds.
 * 
 * @author 9
 */
public class Motorcycle {

    /**
     * The current x-coordinate of the motorcycle.
     */
    private int x;

    /**
     * The current y-coordinate of the motorcycle.
     */
    private int y;

    /**
     * The current direction of the motorcycle (UP, DOWN, LEFT, or RIGHT).
     */
    private String direction;

    /**
     * The color of the motorcycle, used for drawing it and its trail.
     */
    private final Color color;

    /**
     * The name of the player controlling the motorcycle.
     */
    private final String name;

    /**
     * The trail of the motorcycle, represented as a list of points.
     */
    private final ArrayList<Point> trail;

    /**
     * Creates a new motorcycle with the given starting position, direction,
     * color, and player name.
     * 
     * @param startX the starting x-coordinate of the motorcycle
     * @param startY the starting y-coordinate of the motorcycle
     * @param initialDirection the initial direction of the motorcycle
     * @param color the color of the motorcycle
     * @param name the name of the player controlling the motorcycle
     */
    public Motorcycle(int startX, int startY, String initialDirection, Color color, String name) {
        this.x = startX;
        this.y = startY;
        this.direction = initialDirection;
        this.color = color;
        this.trail = new ArrayList<>();
        this.name = name;
    }

    /**
     * Gets the current x-coordinate of the motorcycle.
     * 
     * @return the x-coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the current y-coordinate of the motorcycle.
     * 
     * @return the y-coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Gets the name of the player controlling the motorcycle.
     * 
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the trail of the motorcycle.
     * 
     * @return a list of points representing the motorcycle's trail
     */
    public ArrayList<Point> getTrail() {
        return this.trail;
    }

    /**
     * Draws the motorcycle and its trail on the game board.
     * 
     * @param g2 the graphics context used to draw the motorcycle
     */
    public void draw(Graphics2D g2) {
        // Draw trail
        g2.setColor(color);
        for (Point point : trail) {
            g2.fillRect(point.x, point.y, 5, 5);
        }
        // Draw head
        g2.fillOval((x - (10 - 5) / 2), (y - (10 - 5) / 2), 10, 10);
    }

    /**
     * Moves the motorcycle in its current direction by a given step size.
     * 
     * @param stepSize the number of units to move
     */
    public void move(int stepSize) {
        switch (direction) {
            case "UP":
                y -= stepSize;
                break;
            case "DOWN":
                y += stepSize;
                break;
            case "LEFT":
                x -= stepSize;
                break;
            case "RIGHT":
                x += stepSize;
                break;
        }
    }

    /**
     * Adds the motorcycle's current position to its trail.
     */
    public void addTrail() {
        trail.add(new Point(x, y));
    }

    /**
     * Sets the direction of the motorcycle, ensuring it doesn't turn directly
     * into the opposite direction.
     * 
     * @param newDirection the new direction to set
     */
    public void setDirection(String newDirection) {
        if ((direction.equals("UP") && newDirection.equals("DOWN")) ||
            (direction.equals("DOWN") && newDirection.equals("UP")) ||
            (direction.equals("LEFT") && newDirection.equals("RIGHT")) ||
            (direction.equals("RIGHT") && newDirection.equals("LEFT"))) {
            // Prevent direct reversal
        } else {
            direction = newDirection;
        }
    }

    /**
     * Checks if the motorcycle is out of bounds on the game board.
     * 
     * @param width the width of the game board
     * @param height the height of the game board
     * @return true if the motorcycle is out of bounds, false otherwise
     */
    public boolean isOutOfBounds(int width, int height) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }
}
