package com.chutesandladders;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int BOARD_SIZE = 10;
    private static final int TILE_SIZE = 60;
    private JPanel[][] boardTiles;
    private Player[] players;

    public GamePanel(Player[] players) {
        this.players = players;
        setPreferredSize(new Dimension(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE));
        //setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        setLayout(null);
        boardTiles = new JPanel[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = BOARD_SIZE - 1; row >= 0; row--) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel tile = new JPanel(new BorderLayout());
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                //tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                tile.setOpaque(false);
                tile.setBounds(col*TILE_SIZE,(BOARD_SIZE-1-row)* TILE_SIZE, TILE_SIZE, TILE_SIZE);
                int position = calculatePosition(row, col);
                JLabel label = new JLabel(String.valueOf(position), SwingConstants.CENTER);
                tile.add(label, BorderLayout.NORTH);

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
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        drawLadders(g2);
        drawChutes(g2);
        drawPlayers(g);
    }
    private void drawLadders(Graphics2D g) {
    	drawLineBetweenTiles(g, 4, 14);
    	drawLineBetweenTiles(g,9,31);
    	drawLineBetweenTiles(g, 28, 84);
    	drawLineBetweenTiles(g, 40, 59);
    	
    }
    private void drawChutes(Graphics2D g) {
    	g.setColor(new Color(178,34,34));
    	drawLineBetweenTiles(g,98,78);
    	drawLineBetweenTiles(g,95,75);
    	drawLineBetweenTiles(g,62,19);
    	drawLineBetweenTiles(g,48,26);
    }
    private void drawLineBetweenTiles(Graphics2D g, int start, int end) {
    	Point startPt = getTileCenter(start);
    	Point endPt = getTileCenter(end);
    	g.drawLine(startPt.x, startPt.y, endPt.x, endPt.y);
    }
    private Point getTileCenter(int position) {
    	Point coords=getTileCoordinates(position);
    	return new Point(coords.x + TILE_SIZE/2, coords.y + TILE_SIZE/2);
    }
    private void drawPlayers(Graphics g) {
        for (int i = 0; i < players.length; i++) {
            Player p = players[i];
            Point coords = getTileCoordinates(p.getPosition());

            g.setColor(i == 0 ? Color.RED : Color.BLUE);
            g.fillOval(coords.x + 10 + (i * 25), coords.y + 30, 20, 20);
        }
    }

    private Point getTileCoordinates(int position) {
        if (position < 1 || position > 100) return new Point(0, 0);
        position--;

        int row = BOARD_SIZE - 1 - (position / BOARD_SIZE);
        int col = ((BOARD_SIZE - 1 - row) % 2 == 0) ? position % BOARD_SIZE : (BOARD_SIZE - 1 - (position % BOARD_SIZE));

        return new Point(col * TILE_SIZE, row * TILE_SIZE);
    }

    public void updatePlayerPosition(Player player) {
        repaint();
    }
}
