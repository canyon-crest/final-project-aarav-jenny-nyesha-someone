package com.chutesandladders;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton rollButton;
    private JLabel infoLabel;
    private JLabel playerTurnLabel;
    private JLabel diceResultLabel;
    private ChutesAndLadders game;

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

    public void updateInfo(String message) {
        infoLabel.setText(message);
    }

    public void updateDiceResult(int value) {
        diceResultLabel.setText("Dice: " + value);
    }

    public void updatePlayerTurn(Player player) {
        playerTurnLabel.setText("Current: " + player.getName());
        rollButton.setEnabled(player.isHuman());
    }
}