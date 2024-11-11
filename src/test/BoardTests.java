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

    @Test
    void getPieceEmptySpaceTest() {
        Board board = new Board();
        Assertions.assertEquals(0, board.getPiece(0, 0)); // Should be empty
    }

    @Test
    void getPieceBlackPieceTest() {
        Board board = new Board();
        Assertions.assertEquals(1, board.getPiece(4, 5)); // Black piece
    }

    @Test
    void getPieceWhitePieceTest() {
        Board board = new Board();
        Assertions.assertEquals(-1, board.getPiece(4, 4)); // White piece
    }

    @Test
    void setPieceTest() {
        Board board = new Board();
        board.setPiece(1, 2, 2); // Set black piece at (2, 2)
        Assertions.assertEquals(1, board.getPiece(2, 2));
    }

    @Test
    void getTurnTest() {
        Board board = new Board();
        Assertions.assertEquals(1, board.getTurn()); // Initial turn is 1 (black)
    }

    @Test
    void setTurnTest() {
        Board board = new Board();
        board.setTurn(-1); // Change turn to white
        Assertions.assertEquals(-1, board.getTurn());
    }

    @Test
    void flipTest() {
        Board board = new Board();
        board.setPiece(1, 3, 3); // Black piece
        board.flip(3, 3);        // Flip to white
        Assertions.assertEquals(-1, board.getPiece(3, 3));
    }

    @Test
    void incrementTurnTest() {
        Board board = new Board();
        board.incrementTurn(); // Switch to white
        Assertions.assertEquals(-1, board.getTurn());
    }

    @Test
    void isSideTrueTest() {
        Board board = new Board();
        Assertions.assertTrue(board.isSide(0, 5)); // Top edge
    }

    @Test
    void isSideFalseTest() {
        Board board = new Board();
        Assertions.assertFalse(board.isSide(5, 5)); // Center
    }

    @Test
    void isCornerTrueTest() {
        Board board = new Board();
        Assertions.assertTrue(board.isCorner(0, 0)); // Top-left corner
    }

    @Test
    void isCornerFalseTest() {
        Board board = new Board();
        Assertions.assertFalse(board.isCorner(1, 1)); // Not a corner
    }

    @Test
    void countBlackPiecesTest() {
        Board board = new Board();
        Assertions.assertEquals(2, board.count(1)); // Two initial black pieces
    }

    @Test
    void countWhitePiecesTest() {
        Board board = new Board();
        Assertions.assertEquals(2, board.count(-1)); // Two initial white pieces
    }

    @Test
    void countEmptySpacesTest() {
        Board board = new Board();
        Assertions.assertEquals(96, board.count(0)); // Remaining empty spaces
    }

    @Test
    void gameOverInitialTest() {
        Board board = new Board();
        Assertions.assertEquals(0, board.gameOver()); // Game not over initially
    }

    // Assuming a winning scenario
    @Test
    void gameOverBlackWinsTest() {
        Board board = new Board();
        // Fill the board in a way that black wins
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board.setPiece(1, i, j);
            }
        }
        Assertions.assertEquals(1, board.gameOver());
    }

    @Test
    void nearbySquaresContainTrueTest() {
        Board board = new Board();
        Assertions.assertTrue(board.nearbySquaresContain(1, 4, 4)); // Black near 4,4
    }

    @Test
    void nearbySquaresContainFalseTest() {
        Board board = new Board();
        Assertions.assertFalse(board.nearbySquaresContain(1, 0, 0)); // No pieces near 0,0
    }

    @Test
    void getLegalMovesInitialTest() {
        Board board = new Board();
        Assertions.assertFalse(board.getLegalMoves().isEmpty()); // Should have legal moves at start
    }

    @Test
    void isLegalTrueTest() {
        Board board = new Board();
        Assertions.assertTrue(board.isLegal(3, 4)); // Valid move
    }

    @Test
    void isLegalFalseTest() {
        Board board = new Board();
        Assertions.assertFalse(board.isLegal(0, 0)); // Invalid move
    }

    @Test
    void playMoveValidTest() {
        Board board = new Board();
        board.playMove(3, 4); // Valid move
        Assertions.assertEquals(1, board.getPiece(3, 4)); // Black piece placed
        Assertions.assertEquals(1, board.getPiece(4, 4)); // White piece flipped
    }

    @Test
    void playMoveArrayValidTest() {
        Board board = new Board();
        board.playMove(new Integer[]{3, 4}); // Valid move
        Assertions.assertEquals(1, board.getPiece(3, 4)); // Black piece placed
    }

    @Test
    void resetBoardTest() {
        Board board = new Board();
        board.playMove(3, 4); // Make a move
        board.reset(); // Reset the board
        Assertions.assertEquals(0, board.getPiece(3, 4)); // The piece should be removed
        Assertions.assertEquals(1, board.getPiece(4, 5)); // Initial black piece should still exist
    }

    @Test
    void copyBoardTest() {
        Board board = new Board();
        Board copy = board.copy();
        Assertions.assertEquals(board.getPiece(4, 4), copy.getPiece(4, 4));
        copy.setPiece(1, 3, 3); // Modify the copy
        Assertions.assertNotEquals(board.getPiece(3, 3), copy.getPiece(3, 3)); // Ensure original is unaffected
    }

    @Test
    void undoAfterMoveTest() {
        Board board = new Board();
        board.playMove(3, 4); // Make a valid move
        board.undo();         // Undo the move

        // Ensure the piece at (3, 4) is now empty
        Assertions.assertEquals(0, board.getPiece(3, 4));

        // Ensure the board is in the initial state
        Assertions.assertEquals(-1, board.getPiece(4, 4));
        Assertions.assertEquals(1, board.getPiece(5, 4));
    }

    @Test
    void undoAfterSavePositionTest() {
        Board board = new Board();

        // Play a valid move and then undo it
        board.playMove(3, 4);

        // Check that the move was played
        Assertions.assertEquals(1, board.getPiece(3, 4)); // Black piece placed

        // Undo the move
        board.undo();

        // Verify that the board reverted to the initial state
        Assertions.assertEquals(0, board.getPiece(3, 4)); // The spot should be empty
        Assertions.assertEquals(-1, board.getPiece(4, 4)); // White piece restored
        Assertions.assertEquals(1, board.getTurn()); // Turn reverted back to black
    }


    @Test
    void undoOnInitialStateTest() {
        Board board = new Board();
        Assertions.assertThrows(IndexOutOfBoundsException.class, board::undo);
    }


}
