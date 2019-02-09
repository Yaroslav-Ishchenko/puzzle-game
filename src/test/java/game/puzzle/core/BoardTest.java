package game.puzzle.core;

import game.puzzle.core.validation.IGridValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class BoardTest {

    @Mock
    private IGridValidator validator;

    private Board board;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Integer[] values = {3, 2, 1, 0}; // one of possible generated sequences
        when(validator.isValid(eq(values))).thenReturn(true);
        board = new Board(validator, 4);
    }

    @Test
    public void shouldGenerateBoardsUntilOneValidIsFound() {
        //when
        board.init();

        //then
        assertEquals(3, board.currPosition);
    }

    @Test
    public void solveTheBoard() {
        //when
        board.init();
        //perform moves to solve the puzzle
        getListOfMoves().forEach(board::move);

        //then
        assertTrue(board.isBoardComplete());

        Integer[] actualGrid = board.getGrid();
        Integer[] expectedGrid = {0, 1, 2, 3};
        assertTrue(Arrays.equals(expectedGrid, actualGrid));
    }

    private List<Move> getListOfMoves() {
        return asList(
            Move.UP,
            //start invalid move
            Move.UP, Move.RIGHT,
            //end
            Move.LEFT,
            //start invalid move
            Move.LEFT,
            //end
            Move.DOWN, Move.UP, Move.RIGHT, Move.DOWN,
            //start invalid move
            Move.DOWN,
            //end
            Move.LEFT, Move.UP, Move.RIGHT, Move.DOWN, Move.LEFT, Move.UP);
    }
}