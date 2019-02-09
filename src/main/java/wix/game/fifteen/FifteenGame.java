package wix.game.fifteen;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import wix.game.IPuzzleGame;
import wix.game.validation.GridValidator;
import wix.game.validation.IGridValidator;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

@Log4j
public class FifteenGame implements IPuzzleGame {
    @Getter
    private final Integer[] board;

    private final IGridValidator validator;
    int height;
    int width;
    int length;
    int currPosition;

    public FifteenGame(int width, int height) {
        assert height == width;
        this.width = width;
        this.height = height;
        this.length = height * width;

        board = new Integer[length];
        validator = new GridValidator(width, height);
        init();
    }

    private void init() {
        while (!validator.isValid(generateBoard())) {
            log.debug("Generated invalid initial board, regenerating ...");
        }
        currPosition = length - 1;
    }

    @Override
    public void moveTo(Move direction) {
        if (isValidMove(direction)) {
            move(direction);
            isBoardComplete();
        } else {
           // throw new RuntimeException("Can't move into direction=[" + direction + "], try other");
        }
    }

    private void move(Move direction) {
        switch (direction) {
            case LEFT:
                board[currPosition] = board[currPosition - 1];
                board[currPosition - 1] = null;
                currPosition -= 1;
                break;
            case RIGHT:
                board[currPosition] = board[currPosition + 1];
                board[currPosition + 1] = null;
                currPosition += 1;
                break;
            case UP:
                board[currPosition] = board[currPosition - width];
                board[currPosition - width] = null;
                currPosition -= width;
                break;
            case DOWN:
                board[currPosition] = board[currPosition + width];
                board[currPosition + width] = null;
                currPosition += width;
                break;
        }
        if (isBoardComplete()) {
            throw new RuntimeException("Game finished!");
        }
    }

    private boolean isBoardComplete() {
        if (board[0] != null) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            if (board[i] != i) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidMove(Move direction) {
        switch (direction) {
            case LEFT:
                return currPosition > 0;
            case RIGHT:
                return currPosition < length - 1;
            case UP:
                return currPosition - width > 0;
            case DOWN:
                return currPosition + width < length -1;
        }
        return false;
    }

    private Integer[] generateBoard() {
        List<Integer> range = IntStream.range(1, length) //change geneartion here if you need 0 at any cell of the grid
            .boxed()
            .collect(toList());

        shuffle(range);

        for (int i = 0; i < length - 1; i++) {
            board[i] = range.get(i);
        }

        return board;
    }
}
