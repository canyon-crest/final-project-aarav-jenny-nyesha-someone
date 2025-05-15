package com.chutesandladders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {
    private static final int BOARD_SIZE = 10;
    private static final int TILE_SIZE = 60;
    private JPanel[][] boardTiles;
    private Player[] players;

    public GamePanel(Player[] players) {
        this.players = players;
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        boardTiles = new JPanel[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = BOARD_SIZE - 1; row >= 0; row--) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel tile = new JPanel(new BorderLayout());
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                
                int position = calculatePosition(row, col);
                JLabel positionLabel = new JLabel(String.valueOf(position), SwingConstants.CENTER);
                tile.add(positionLabel, BorderLayout.NORTH);
                
                boardTiles[row][col] = tile;
                add(tile);
            }
        }
    }

    private int calculatePosition(int row, int col) {
        if (row % 2 == (BOARD_SIZE - 1) % 2) {
            return (BOARD_SIZE - row - 1) * BOARD_SIZE + col + 1;
        } else {
            return (BOARD_SIZE - row) * BOARD_SIZE - col;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayers(g);
    }

    private void drawPlayers(Graphics g) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                Point position = getTileCoordinates(players[i].getPosition());
                g.setColor(i == 0 ? Color.RED : Color.BLUE);
                g.fillOval(position.x + 10, position.y + 10, 20, 20);
            }
        }
    }

    public void updatePlayerPosition(Player player) {
        repaint();
    }

    private Point getTileCoordinates(int position) {
        if (position < 1 || position > BOARD_SIZE * BOARD_SIZE) {
            return new Point(0, 0);
        }
        
        position--;
        
        int row = BOARD_SIZE - 1 - (position / BOARD_SIZE);
        int col;
        
        if ((BOARD_SIZE - 1 - row) % 2 == 0) {
            col = position % BOARD_SIZE;
        } else {
            col = BOARD_SIZE - 1 - (position % BOARD_SIZE);
        }
        
        return new Point(col * TILE_SIZE, row * TILE_SIZE);
    }
}