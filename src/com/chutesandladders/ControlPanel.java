package com.chutesandladders;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton rollButton;
    private JLabel infoLabel;
    private JLabel playerTurnLabel;
    private JLabel diceResultLabel;
    private ChutesAndLadders game;
//UI components
    public ControlPanel(ChutesAndLadders game) {
        this.game = game;
        setLayout(new GridLayout(2, 2));
        
        rollButton = new JButton("Roll Dice");
        infoLabel = new JLabel("Game started. Player 1's turn.");
        playerTurnLabel = new JLabel("Current: Player 1");
        diceResultLabel = new JLabel("Dice: -");
        
        rollButton.addActionListener(e -> game.rollDice());
        
        add(rollButton);
        add(playerTurnLabel);
        add(infoLabel);
        add(diceResultLabel);
    }
    //@param message - the message to display
    public void updateInfo(String message) {
        infoLabel.setText(message);
    }
    //@param value - the value rolled on the dice (1 to 6)
    public void updateDiceResult(int value) {
        diceResultLabel.setText("Dice: " + value);
    }
    //@param player - the player whose turn it currently is
    public void updatePlayerTurn(Player player) {
        playerTurnLabel.setText("Current: " + player.getName());
        rollButton.setEnabled(player.isHuman());
    }
}