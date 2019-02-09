package wix.game;

public interface IPuzzleGame {
    enum Move {LEFT, RIGHT, UP, DOWN}

    void moveTo(Move direction);
}
