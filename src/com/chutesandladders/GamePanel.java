package com.chutesandladders;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int BOARD_SIZE = 10;
    private static final int TILE_SIZE = 60;
    private JPanel[][] boardTiles;
    private Player[] players;
    private Image ladderImage;
    private Image chuteImage;
//sets up game with board, images, and players
    //@param players - an array of players in the game
    public GamePanel(Player[] players) {
        this.players = players;
        setPreferredSize(new Dimension(TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE));
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        setLayout(null);
        boardTiles = new JPanel[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
        loadImages();
    }
    //images for the snakes and the ladders
    private void loadImages() {
    	ladderImage = new ImageIcon(getClass().getResource("image/ladders.png")).getImage();
        chuteImage = new ImageIcon(getClass().getResource("image/snakesnake.png")).getImage();
    }
    //creates numbered tiles and board for game
    private void initializeBoard() {
        for (int row = BOARD_SIZE - 1; row >= 0; row--) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JPanel tile = new JPanel(new BorderLayout());
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               
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
    //@param row - the row index on the board
    //@param col - the column index on the board
    //@return the position number
    private int calculatePosition(int row, int col) {
        if (row % 2 == (BOARD_SIZE - 1) % 2) {
            return (BOARD_SIZE - row - 1) * BOARD_SIZE + col + 1;
        } else {
            return (BOARD_SIZE - row) * BOARD_SIZE - col;
        }
    }

    @Override
    //@param g - the Graphics object used to draw the component
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        drawLadders(g2);
        drawChutes(g2);
        drawPlayers(g);
    }
    //@param g - the Graphics2D context to draw with
    private void drawLadders(Graphics2D g) {
    	//sets up the graphics for the ladders
    	drawLineBetweenTiles(g, ladderImage, 1, 38);
        drawLineBetweenTiles(g, ladderImage, 4, 14);
        drawLineBetweenTiles(g, ladderImage, 9, 31);
        drawLineBetweenTiles(g, ladderImage, 21, 42);
        drawLineBetweenTiles(g, ladderImage, 28, 84);
        drawLineBetweenTiles(g, ladderImage, 36, 44);
        drawLineBetweenTiles(g, ladderImage, 51, 67);
        drawLineBetweenTiles(g, ladderImage, 71, 91);
        drawLineBetweenTiles(g, ladderImage, 80, 100);
    }
    //@param g - the Graphics2D context to draw with
    private void drawChutes(Graphics2D g) {
    	//sets up the graphics for the snakes
    	drawLineBetweenTiles(g, chuteImage, 16, 6);
        drawLineBetweenTiles(g, chuteImage, 47, 26);
        drawLineBetweenTiles(g, chuteImage, 49, 11);
        drawLineBetweenTiles(g, chuteImage, 56, 53);
        drawLineBetweenTiles(g, chuteImage, 62, 19);
        drawLineBetweenTiles(g, chuteImage, 64, 60);
        drawLineBetweenTiles(g, chuteImage, 87, 24);
        drawLineBetweenTiles(g, chuteImage, 93, 73);
        drawLineBetweenTiles(g, chuteImage, 95, 75);
        drawLineBetweenTiles(g, chuteImage, 98, 78);
    }
    //creates the line that needs to be drawn for either the chute or ladder
    //@param g - the Graphics2D context to draw with
    //@param img - the image to draw
    //@param start - the starting position number
    //@param end - the ending position number
    private void drawLineBetweenTiles(Graphics2D g, Image img, int start, int end) {
    	Point startPt = getTileCenter(start);
    	Point endPt = getTileCenter(end);
    	double angle = Math.atan2(endPt.y - startPt.y, endPt.x - startPt.x);
        double distance = startPt.distance(endPt);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.translate(startPt.x, startPt.y);
        g2.rotate(angle);
        int imgWidth = (int) distance;
        int imgHeight = 30;
        g2.drawImage(img, 0, -imgHeight / 2, imgWidth, imgHeight, null);
        g2.dispose();
    	//g.drawLine(startPt.x, startPt.y, endPt.x, endPt.y);
    }
    //@param position the position number (1 to 100)
    //@return the center point in pixel coordinates
    private Point getTileCenter(int position) {
    	Point coords=getTileCoordinates(position);
    	return new Point(coords.x + TILE_SIZE/2, coords.y + TILE_SIZE/2);
    }
    //creates the graphics for the players which are shown as dots
    private void drawPlayers(Graphics g) {
        for (int i = 0; i < players.length; i++) {
            Player p = players[i];
            Point coords = getTileCoordinates(p.getPosition());

            g.setColor(i == 0 ? Color.RED : Color.BLUE);
            g.fillOval(coords.x + 10 + (i * 25), coords.y + 30, 20, 20);
        }
    }
//gets the coordinates of the tile based on the position of the character
    //@param position the position number (1 to 100)
    //@return the Point representing top-left pixel of the tile
    private Point getTileCoordinates(int position) {
        if (position < 1 || position > 100) return new Point(0, 0);
        position--;

        
        int row = position / BOARD_SIZE;
        int col;

        if (row % 2 == 0) {
            col = position % BOARD_SIZE;
        } else {
            col = BOARD_SIZE - 1 - (position % BOARD_SIZE);
        }

        int drawRow = BOARD_SIZE - 1 - row;
        return new Point(col * TILE_SIZE, drawRow * TILE_SIZE);
    }
//@param player the player whose position has changed
    public void updatePlayerPosition(Player player) {
        repaint();
    }
}
