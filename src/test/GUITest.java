package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.othello.GUI;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GUITest {

    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = new GUI();  // Initialize the GUI object for each test
    }

    @Test
    void setupMenu() {
        JMenuBar menuBar = gui.getJMenuBar();
        assertNotNull(menuBar);
        assertEquals(3, menuBar.getMenuCount());

        JMenu fileMenu = menuBar.getMenu(0);
        assertEquals("File", fileMenu.getText());

        JMenuItem quitItem = fileMenu.getItem(1);
        assertEquals("Quit", quitItem.getText());
    }

    @Test
    void getColor() {
        Color defaultColor = gui.getColor();
        assertNotNull(defaultColor);
        assertEquals(new Color(45, 174, 82), defaultColor);
    }

    @Test
    void refreshGrid() {
        gui.refreshGrid();

        JPanel buttonPanel = (JPanel) gui.getContentPane().getComponent(0);
        assertEquals(100, buttonPanel.getComponentCount());

        Component firstTile = buttonPanel.getComponent(0);
        assertTrue(firstTile instanceof JButton);
    }

    @Test
    void engineMove() {
        gui.engineMove();

        JPanel buttonPanel = (JPanel) gui.getContentPane().getComponent(0);
        assertEquals(100, buttonPanel.getComponentCount());
    }

    @Test
    void onClick() {
        gui.onClick(4, 5);

        JPanel buttonPanel = (JPanel) gui.getContentPane().getComponent(0);
        JButton tile = (JButton) buttonPanel.getComponent(45);  // Row 4, Column 5 -> 45th index
        assertNotNull(tile);
    }

    @Test
    void simulate() {
        gui.simulate();

        JPanel buttonPanel = (JPanel) gui.getContentPane().getComponent(0);
        assertEquals(100, buttonPanel.getComponentCount());
    }

    @Test
    void run() {
        gui.run();
        assertTrue(gui.isVisible());
    }

    @Test
    void actionPerformed() {
        JMenuBar menuBar = gui.getJMenuBar();
        JMenu gameMenu = menuBar.getMenu(2); // Game Menu
        JMenuItem resetItem = gameMenu.getItem(0);  // Reset menu item

        resetItem.doClick();  // Simulate clicking on the reset menu item
        assertNotNull(gui);
    }
}
