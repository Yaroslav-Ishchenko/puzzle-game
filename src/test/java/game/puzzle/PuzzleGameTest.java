package game.puzzle;

import game.puzzle.core.Board;
import game.puzzle.presentation.IGamePresenter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PuzzleGameTest {
    @Mock
    private IGamePresenter presenter;
    @Mock
    private Board board;

    private PuzzleGame puzzleGame;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        puzzleGame = new PuzzleGame(board, presenter);
    }

    @Test
    public void shouldStart() {
        //when
        puzzleGame.start();

        //then
        verify(board).init();
        verify(presenter).begin();
    }

    @Test
    public void shouldReset() {
        //when
        puzzleGame.reset();

        //then
        verify(board).init();
        verifyNoMoreInteractions(board);
    }

    @Test
    public void ShouldMoveTo() {
        //when
        puzzleGame.moveTo(IPuzzleGame.Move.LEFT);

        //then
        verify(board).move(eq(IPuzzleGame.Move.LEFT));
        verifyNoMoreInteractions(board);
    }

    @Test
    public void shouldCheckBoardIsCompleted() {
        //when
        puzzleGame.isCompleted();

        //then
        verify(board).isBoardComplete();
        verifyNoMoreInteractions(board);
    }

    @Test
    public void shouldReturnBoardGrid() {
        //when
        puzzleGame.getGrid();

        //then
        verify(board).getGrid();
    }
}