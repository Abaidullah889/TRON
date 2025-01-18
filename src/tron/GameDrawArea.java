/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The GameDrawArea class handles the drawing of the game elements,
 * including the motorcycles and their trails.
 * It extends {@code JPanel} and is used as the main area for rendering
 * the game's visual components.
 * 
 * This class works with a list of motorcycles and draws each one on the game board.
 * 
 * @author 9
 */
public class GameDrawArea extends JPanel {

    /**
     * The list of motorcycles to be drawn on the game board.
     */
    private final ArrayList<Motorcycle> motorcycles;

    /**
     * Creates a new GameDrawArea with the specified list of motorcycles.
     * 
     * @param motorcycles the list of motorcycles to be drawn
     */
    public GameDrawArea(ArrayList<Motorcycle> motorcycles) {
        this.motorcycles = motorcycles;
    }

    /**
     * Paints the game components, including the motorcycles and their trails.
     * 
     * @param g the {@code Graphics} object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Motorcycle motorcycle : motorcycles) {
            motorcycle.draw(g2);
        }
    }
}
