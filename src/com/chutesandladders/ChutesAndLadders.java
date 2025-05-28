package com.chutesandladders;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ChutesAndLadders {
    private JFrame mainFrame;
    private GamePanel gamePanel;
    private ControlPanel controlPanel;
    private Player[] players;
    private Player currentPlayer;
    private Board gameBoard;
    private boolean gameActive;
    private Random random;

    public ChutesAndLadders() {
        initializeGame();
        setupUI();
    }

    private void initializeGame() {
        players = new Player[2];
        players[0] = new Player("Player 1", true);
        players[1] = new AIPlayer("Computer");
        currentPlayer = players[0];
        gameBoard = new Board();
        gameActive = true;
        random = new Random();
    }
//set game up
    private void setupUI() {
        mainFrame = new JFrame("Chutes and Ladders Showdown");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(800, 700);

        gamePanel = new GamePanel(players);
        controlPanel = new ControlPanel(this);

        mainFrame.add(gamePanel, BorderLayout.CENTER);
        mainFrame.add(controlPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }

    public void rollDice() {
        if (!gameActive) return;

        int roll = random.nextInt(6) + 1;
        controlPanel.updateDiceResult(roll);

        if (currentPlayer.isHuman()) {
            humanPlayerTurn(roll);
        } else {
            aiPlayerTurn(roll);
        }
    }

    private void humanPlayerTurn(int roll) {
        currentPlayer.move(roll);
        //int newPosition = gameBoard.getNewPosition(currentPlayer.getPosition());
        //currentPlayer.setPosition(newPosition);
        
        int resolvedPosition = resolvePosition(currentPlayer.getPosition());
        currentPlayer.setPosition(resolvedPosition);
        
        gamePanel.updatePlayerPosition(currentPlayer);
        controlPanel.updateInfo(currentPlayer.getName() + " rolled a " + roll + " and moved to " + resolvedPosition);
        
        if (checkWinCondition()) {
            gameActive = false;
            controlPanel.updateInfo(currentPlayer.getName() + " wins!");
            return;
        }
        
        switchPlayer();
    }

    private void aiPlayerTurn(int roll) {
        currentPlayer.move(roll);
        //int newPosition = gameBoard.getNewPosition(currentPlayer.getPosition());
        //currentPlayer.setPosition(newPosition);
        
        int resolvedPosition = resolvePosition(currentPlayer.getPosition());
        currentPlayer.setPosition(resolvedPosition);
        
        gamePanel.updatePlayerPosition(currentPlayer);
        controlPanel.updateInfo(currentPlayer.getName() + " rolled a " + roll + " and moved to " + resolvedPosition);
        
        if (checkWinCondition()) {
            gameActive = false;
            controlPanel.updateInfo(currentPlayer.getName() + " wins!");
            return;
        }
        
        switchPlayer();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == players[0]) ? players[1] : players[0];
        controlPanel.updatePlayerTurn(currentPlayer);
        
        if (!currentPlayer.isHuman()) {
            // AI takes turn automatically after short delay
            Timer timer = new Timer(1000, e -> rollDice());
            timer.setRepeats(false);
            timer.start();
        }
    }

    private boolean checkWinCondition() {
        return currentPlayer.getPosition() >= 100;
    }
    //gets valid positions for characters
    private int resolvePosition(int startPosition) {
        int newPos = startPosition;
        int nextPos;

        do {
            nextPos = gameBoard.getNewPosition(newPos);
            if (nextPos != newPos) {
                newPos = nextPos;
            } else {
                break;
            }
        } while (true);

        return newPos;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChutesAndLadders());
    }
}