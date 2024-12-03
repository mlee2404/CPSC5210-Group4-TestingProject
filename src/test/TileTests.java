package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.othello.GUI;
import src.othello.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TileTests {

    private Tile tile;

    @BeforeEach
    void setUp() {
        GUI gui = new GUI();  // Create a GUI instance to pass as parent
        tile = new Tile(gui, 2, 3);  // Initialize Tile at row 2, col 3
    }

    @Test
    void onClickTest() {
        int count = 0;

        // Simulate a button click
        for (ActionListener listener : tile.getActionListeners()) {
            listener.actionPerformed(new ActionEvent(tile, ActionEvent.ACTION_PERFORMED, ""));
            count++;
        }

        // Assert that onClick was called once
        Assertions.assertEquals(1, count);
    }

    @Test
    void getRow() {
        Assertions.assertEquals(2, tile.getRow());
    }

    @Test
    void getCol() {
        Assertions.assertEquals(3, tile.getCol());
    }

    @Test
    void setPiece() {
        tile.setPiece(1);  // Set black piece
        Icon icon = tile.getIcon();
        Assertions.assertNotNull(icon);

        tile.setPiece(-1);  // Set white piece
        Icon iconWhite = tile.getIcon();
        Assertions.assertNotNull(iconWhite);
    }

    @Test
    void setHighlighted() {
        tile.setHighlighted(true);  // Highlight tile
        Assertions.assertEquals(Color.WHITE, ((LineBorder) tile.getBorder()).getLineColor());

        tile.setHighlighted(false);  // Remove highlight
        Assertions.assertEquals(Color.BLACK, ((LineBorder) tile.getBorder()).getLineColor());
    }
}