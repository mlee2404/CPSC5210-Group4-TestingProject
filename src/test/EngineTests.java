package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.othello.Board;
import src.othello.Engine;

import static org.junit.jupiter.api.Assertions.*;

class EngineTests {

    @Test
    void evaluateTest() {
        Board board = new Board();
        int initialScore = Engine.evaluate(board);

        board.playMove(3, 4);
        int newScore = Engine.evaluate(board);

        Assertions.assertNotEquals(initialScore, newScore);  // Ensure score changes after a move
    }

    @Test
    void minimaxTest() {
        Board board = new Board();
        int scoreDepth1 = Engine.minimax(board, 1, board.getTurn());
        int scoreDepth2 = Engine.minimax(board, 2, board.getTurn());

        Assertions.assertNotEquals(scoreDepth1, scoreDepth2);  // Ensure different scores at different depths
    }

    @Test
    void bestMoveTest() {
        Board board = new Board();
        Integer[] move = Engine.bestMove(board);

        Assertions.assertNotNull(move);  // Ensure move is not null
        Assertions.assertTrue(board.isLegal(move[0], move[1]));  // Ensure move is legal
    }

    @Test
    void limitOpponentOptionsTest() {
        Board board = new Board();
        Integer[] move = Engine.limitOpponentOptions(board);

        Assertions.assertNotNull(move);  // Ensure move is not null
        Assertions.assertTrue(board.isLegal(move[0], move[1]));  // Ensure move is legal
    }

    @Test
    void greedySelectionTest() {
        Board board = new Board();
        Integer[] move = Engine.greedySelection(board);

        Assertions.assertNotNull(move);  // Ensure move is not null
        Assertions.assertTrue(board.isLegal(move[0], move[1]));  // Ensure move is legal
    }

    @Test
    void bestMoveEdgeCaseTest() {
        Board board = new Board();
        board.playMove(3, 4);  // Simulate a move
        board.playMove(2, 4);  // Another move

        Integer[] move = Engine.bestMove(board);
        Assertions.assertNotNull(move);  // Ensure move is valid even with constrained options
    }

    @Test
    void minimaxGameEndTest() {
        Board board = new Board();

        // Simulate near end-game state
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 == 0) board.setPiece(1, i, j);
                else board.setPiece(-1, i, j);
            }
        }

        int score = Engine.minimax(board, 2, 1);
        Assertions.assertTrue(score != 0);  // Minimax should handle end-game states
    }

    @Test
    void evaluateCornerPriorityTest() {
        Board board = new Board();
        board.playMove(0, 0);  // Simulate occupying a corner

        int score = Engine.evaluate(board);
        Assertions.assertTrue(score > 0);  // Corner positions should have higher scores
    }

    @Test
    void evaluateSidePriorityTest() {
        Board board = new Board();
        board.playMove(0, 4);  // Simulate occupying a side

        int score = Engine.evaluate(board);
        Assertions.assertTrue(score > 0);  // Side positions should have higher scores
    }
}