package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.othello.Board;

public class BoardTests {
    @Test
    void inBoundsLowerBoundTest() {
        Board board = new Board();
        Assertions.assertTrue(board.inBounds(0, 0));
    }

    @Test
    void inBoundsUpperBoundTest() {
        Board board = new Board();
        Assertions.assertTrue(board.inBounds(9, 9));
    }

    @Test
    void inBoundsOutOfLowerBoundTest() {
        Board board = new Board();
        Assertions.assertFalse(board.inBounds(9, -1));
    }

    @Test
    void inBoundsOutOfUpperBoundTest() {
        Board board = new Board();
        Assertions.assertFalse(board.inBounds(10, 0));
    }
}
