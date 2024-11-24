package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.othello.GUI;
import src.othello.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TileTests {

    private Tile tile;

    @BeforeEach
    void setUp() {
        GUI gui = new GUI();  // Create a GUI instance to pass as parent
        tile = new Tile(gui, 2, 3);  // Initialize Tile at row 2, col 3
    }

    @Test
    void getRow() {
        assertEquals(2, tile.getRow());
    }

    @Test
    void getCol() {
        assertEquals(3, tile.getCol());
    }

    @Test
    void setPiece() {
        tile.setPiece(1);  // Set black piece
        Icon icon = tile.getIcon();
        assertNotNull(icon);

        tile.setPiece(-1);  // Set white piece
        Icon iconWhite = tile.getIcon();
        assertNotNull(iconWhite);
    }

    @Test
    void setHighlighted() {
        tile.setHighlighted(true);  // Highlight tile
        assertEquals(Color.WHITE, ((LineBorder) tile.getBorder()).getLineColor());

        tile.setHighlighted(false);  // Remove highlight
        assertEquals(Color.BLACK, ((LineBorder) tile.getBorder()).getLineColor());
    }
}