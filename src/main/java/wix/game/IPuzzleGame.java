package wix.game;

public interface IPuzzleGame {

    Integer[] getGrid();

    enum Move {LEFT, RIGHT, UP, DOWN}

    void start();

    void reset();

    void moveTo(Move direction);

    boolean isCompleted();
}
