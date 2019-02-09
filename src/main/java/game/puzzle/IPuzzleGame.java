package game.puzzle;

import game.puzzle.core.Move;

public interface IPuzzleGame {

    Integer[] getGrid();

    void start();

    void reset();

    void moveTo(Move direction);

    boolean isCompleted();
}
