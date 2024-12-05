package src.test.Mutant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.othello.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardMutantTests {

    //Original test case
    @Test
    void issEmptyOriginal() {
        Board board = new Board();
        Assertions.assertTrue(board.isEmpty(0,0),"Test case Passed!"); // Check if empty.
    }

    //Mutant test case
//    @Test
//    void isEmptyTestMutant() {
//        Board board = new Board();
//        Assertions.assertTrue(board.isEmpty(4,4),"Test case failed!"); // Check if empty.
//    }
    @Test
    void isEmptyTestMutant() {
        Board board = new Board();
        Assertions.assertFalse(board.isEmpty(4,4),"Test case Passed!"); // Check if empty.
    }

    @Test
    void isFilledTestOriginal() {
        Board board = new Board();
        Assertions.assertFalse(board.isFilled()); //Check if the board is fully filled
    }

    @Test
    void isFilledTestMutant() {
        Board board = new Board();
        Assertions.assertTrue(!board.isFilled()); //Check if the board is fully filled
    }


}
