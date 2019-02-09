package game.puzzle;

import lombok.extern.log4j.Log4j;
import game.puzzle.core.Board;
import game.puzzle.presentation.GamePresenter;
import game.puzzle.presentation.IGamePresenter;

@Log4j
public class PuzzleGame implements IPuzzleGame {
    private final IGamePresenter presenter;
    private final Board board;

    public PuzzleGame(int width, int height) {
        assert width == height;
        this.board = new Board(width * height);
        this.presenter = new GamePresenter(this);
    }

    PuzzleGame(Board board, IGamePresenter presenter) {
        this.board = board;
        this.presenter = presenter;
    }

    @Override
    public void start() {
        board.init();
        presenter.begin();
    }

    @Override
    public void reset() {
        board.init();
    }

    @Override
    public void moveTo(Move direction) {
        board.move(direction);
    }

    @Override
    public boolean isCompleted() {
        return board.isBoardComplete();
    }

    @Override
    public Integer[] getGrid() {
        return board.getGrid();
    }
}
