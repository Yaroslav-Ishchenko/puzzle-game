package wix.game.fifteen;

import wix.game.IGame;
import wix.game.validation.GridValidator;
import wix.game.validation.IGridValidator;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

public class FifteenGame implements IGame {
    private final static Random RANDOM = new Random();
    private final IGridValidator validator;
    int[] board;
    int height;
    int width;
    int length;
    int currPosition;

    public FifteenGame(int width, int height) {
        assert height == width;
        this.width = width;
        this.height = height;
        this.length = height * width;

        this.board = new int[length];
        validator = new GridValidator(width, height);
        generateBoard();
    }

    public int[] generateBoard() {
        List<Integer> range = IntStream.range(1, width * height)
            .boxed()
            .collect(toList());

        shuffle(range);
        for (int i = 0; i < length - 1; i++) {
            board[i] = range.get(i);
        }

        return board;
    }

    public int[] board() {
        return board;
    }
}
