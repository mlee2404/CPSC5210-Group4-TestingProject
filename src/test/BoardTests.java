package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.othello.Board;

public class BoardTests {
    @Test
    void inBoundsTest() {
        Board board = new Board();
        Assertions.assertTrue(board.inBounds(5, 5));
    }

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
    void inBoundsOutOfLowerBoundTest1() {
        Board board = new Board();
        Assertions.assertFalse(board.inBounds(9, -1));
    }

    @Test
    void inBoundsOutOfLowerBoundTest2() {
        Board board = new Board();
        Assertions.assertFalse(board.inBounds(-1, 9));
    }

    @Test
    void inBoundsOutOfLowerBoundTest3() {
        Board board = new Board();
        Assertions.assertFalse(board.inBounds(-1, -1));
    }

    @Test
    void inBoundsOutOfUpperBoundTest1() {
        Board board = new Board();
        Assertions.assertFalse(board.inBounds(10, 0));
    }

    @Test
    void inBoundsOutOfUpperBoundTest2() {
        Board board = new Board();
        Assertions.assertFalse(board.inBounds(0, 10));
    }

    @Test
    void inBoundsOutOfUpperBoundTest3() {
        Board board = new Board();
        Assertions.assertFalse(board.inBounds(10, 10));
    }

    @Test
    void isEmptyTest() {
        Board board = new Board();
        Assertions.assertTrue(board.isEmpty(0,0));
    }

    @Test
    void isEmptyNotEmptyTest() {
        Board board = new Board();
        Assertions.assertFalse(board.isEmpty(4,4));
    }

    @Test
    void isFilledTest() {
        Board board = new Board();
        Assertions.assertFalse(board.isFilled());
    }
}
