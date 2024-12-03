package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.othello.GUI;

import javax.swing.*;
import java.awt.*;

class GUITests {

    private GUI gui;

    private int countPieces() {
        int pieceCount = 0;
        JPanel buttonPanel = (JPanel) gui.getContentPane().getComponent(0);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                String concatenated = Integer.toString(i) + j;
                int result = Integer.parseInt(concatenated);
                JButton tile = (JButton) buttonPanel.getComponent(result);
                if (tile.getIcon() != null) {  // Assuming 0 represents an empty tile
                    pieceCount++;
                }
            }
        }
        return pieceCount;
    }

    @BeforeEach
    void setUp() {
        gui = new GUI();  // Initialize the GUI object for each test
    }

    @Test
    void setupMenuTest() {
        JMenuBar menuBar = gui.getJMenuBar();
        Assertions.assertNotNull(menuBar);
        Assertions.assertEquals(3, menuBar.getMenuCount());

        JMenu fileMenu = menuBar.getMenu(0);
        Assertions.assertEquals("File", fileMenu.getText());

        JMenuItem quitItem = fileMenu.getItem(1);
        Assertions.assertEquals("Quit", quitItem.getText());
    }

    @Test
    void getColorTest() {
        Color defaultColor = gui.getColor();
        Assertions.assertNotNull(defaultColor);
        Assertions.assertEquals(new Color(45, 174, 82), defaultColor);
    }

    @Test
    void refreshGridTest() {
        gui.refreshGrid();

        JPanel buttonPanel = (JPanel) gui.getContentPane().getComponent(0);
        JButton tile = (JButton) buttonPanel.getComponent(55);  // Row 5, Column 5 -> 55th index

        Assertions.assertNotNull(tile.getIcon());
    }

    @Test
    void engineMoveTest() {
        // Count the number of pieces before engine move
        int initialPieceCount = countPieces();

        gui.engineMove();

        // Count the number of pieces after engine move
        int newPieceCount = countPieces();

        Assertions.assertTrue(newPieceCount > initialPieceCount);
    }

    @Test
    void onClickValidMoveTest() {
        gui.onClick(6,5); // Valid move

        JPanel buttonPanel = (JPanel) gui.getContentPane().getComponent(0);
        JButton tile = (JButton) buttonPanel.getComponent(65);  // Row 6, Column 5 -> 65th index

        Assertions.assertNotNull(tile.getIcon());
    }

    @Test
    void onClickInvalidMoveTest() {
        gui.onClick(0,0); // Invalid move

        JPanel buttonPanel = (JPanel) gui.getContentPane().getComponent(0);
        JButton badTile = (JButton) buttonPanel.getComponent(0); // Row 0, Column 0 -> 0th index

        Assertions.assertNull(badTile.getIcon());
    }

    @Test
    void onClickPlayAITest() throws InterruptedException {
        JMenuBar menuBar = gui.getJMenuBar();
        JMenu fileMenu = menuBar.getMenu(0); // File Menu
        JMenu newMenu = (JMenu) fileMenu.getItem(0);  // New menu
        JMenuItem newAIItem = newMenu.getItem(1); // New AI menu item

        newAIItem.doClick();  // Simulate clicking on the new AI menu item

        gui.onClick(6, 5); // Valid move
        Thread.sleep(400); // Allow AI to move

        JPanel buttonPanel = (JPanel) gui.getContentPane().getComponent(0);
        JButton tile = (JButton) buttonPanel.getComponent(65);  // Row 6, Column 5 -> 65th index
        JButton AITile = (JButton) buttonPanel.getComponent(66); // Row 6, Column 6 -> 66th index

        Assertions.assertNotNull(tile.getIcon());
        Assertions.assertNotNull(AITile.getIcon());
    }

    @Test
    void simulateTest() {
        // Count the number of pieces before simulate
        int initialPieceCount = countPieces();

        gui.simulate();

        // Count the number of pieces after simulate
        int newPieceCount = countPieces();

        Assertions.assertTrue(newPieceCount > initialPieceCount);
    }

    @Test
    void runTest() {
        gui.run();
        Assertions.assertTrue(gui.isVisible());
    }

    @Test
    void actionPerformedResetTest() {
        // Do one move before resetting
        gui.onClick(6, 5);

        int initialPieceCount = countPieces();

        JMenuBar menuBar = gui.getJMenuBar();
        JMenu gameMenu = menuBar.getMenu(2); // Game Menu
        JMenuItem resetItem = gameMenu.getItem(0);  // Reset menu item

        resetItem.doClick();  // Simulate clicking on the reset menu item

        int newPieceCount = countPieces();
        Assertions.assertTrue(newPieceCount < initialPieceCount);
    }

    @Test
    void actionPerformedNewPlayerTest() {
        // Do one move before resetting
        gui.onClick(6, 5);
        int initialPieceCount = countPieces();

        JMenuBar menuBar = gui.getJMenuBar();
        JMenu fileMenu = menuBar.getMenu(0); // File Menu
        JMenu newMenu = (JMenu) fileMenu.getItem(0);  // New menu
        JMenuItem newPlayerItem = newMenu.getItem(0); // New Player menu item

        newPlayerItem.doClick();  // Simulate clicking on the new player menu item

        int newPieceCount = countPieces();
        Assertions.assertTrue(newPieceCount < initialPieceCount);
    }

    @Test
    void actionPerformedUndoTest() {
        // Populate history before undoing
        for (int i=3; i< 6; i++) {
            for (int j = 3; j < 6; j++) {
                gui.onClick(i, j);
            }
        }
        int initialPieceCount = countPieces();

        JMenuBar menuBar = gui.getJMenuBar();
        JMenu gameMenu = menuBar.getMenu(2); // Game Menu
        JMenuItem undoItem = gameMenu.getItem(1);  // Undo menu item

        undoItem.doClick();  // Simulate clicking on the undo menu item

        int newPieceCount = countPieces();
        Assertions.assertTrue(newPieceCount < initialPieceCount);
    }

    @Test
    void actionPerformedNewAITest() {
        // Do one move before resetting
        gui.onClick(6, 5);
        int initialPieceCount = countPieces();

        JMenuBar menuBar = gui.getJMenuBar();
        JMenu fileMenu = menuBar.getMenu(0); // File Menu
        JMenu newMenu = (JMenu) fileMenu.getItem(0);  // New menu
        JMenuItem newAIItem = newMenu.getItem(1); // New AI menu item

        newAIItem.doClick();  // Simulate clicking on the new AI menu item

        int newPieceCount = countPieces();
        Assertions.assertTrue(newPieceCount < initialPieceCount);
    }

    @Test
    void actionPerformedSimulateTest() {
        // Keep track of the number of pieces before simulate
        int initialPieceCount = countPieces();

        JMenuBar menuBar = gui.getJMenuBar();
        JMenu gameMenu = menuBar.getMenu(2); // Game Menu
        JMenuItem simulateItem = gameMenu.getItem(2);  // Simulate menu item

        simulateItem.doClick();  // Simulate clicking on the simulate menu item

        int newPieceCount = countPieces();
        Assertions.assertTrue(newPieceCount > initialPieceCount);
    }
}