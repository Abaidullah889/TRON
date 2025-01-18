/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tron;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The WelcomeScreen class shows the initial screen of the TRON game.
 * It displays a welcome message and a "Start" button. When the button
 * is clicked, the game moves to the main menu.
 * 
 * This screen is part of the game's graphical user interface.
 * 
 * @author 9
 */
public class WelcomeScreen extends JFrame {

    /**
     * The controller that manages screen transitions.
     */
    private final GameController controller;

    /**
     * Creates a WelcomeScreen with a reference to the game controller.
     * 
     * @param controller the game controller that manages transitions between screens
     */
    public WelcomeScreen(GameController controller) {
        this.controller = controller;
        initUI();
    }

    /**
     * Sets up the welcome screen layout.
     * Includes a message and a button to start the game.
     */
    private void initUI() {
        
        JPanel jPanel1 = new javax.swing.JPanel();
        JPanel jPanel3 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JButton jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jLabel1.setText("Welcome to TRON Light-Cycle Battle.");

        jButton1.setText("Start");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new StartButtonActionListener());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(34, 34, 34))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();


    }

    /**
     * Handles the "Start" button click event.
     * Closes the welcome screen and opens the main menu.
     */
    private class StartButtonActionListener implements ActionListener {

        /**
         * Performs the action when the "Start" button is clicked.
         * 
         * @param e the event that triggered this action
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            controller.showMenuScreen();
        }
    }
}
