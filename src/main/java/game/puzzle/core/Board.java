package game.puzzle.core;

import game.puzzle.core.validation.GridValidator;
import game.puzzle.core.validation.IGridValidator;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;

@Log4j
public class Board {
    @Getter
    final Integer[] grid;

    int currPosition;
    private int length;
    private int columnRowLength;

    private final IGridValidator validator;

    public Board(IGridValidator validator, int length) {
        this.length = length;
        this.validator = validator;
        this.columnRowLength = (int) Math.sqrt(length);
        this.grid = new Integer[length];
    }

    public void init() {
        while (!validator.isValid(generateBoard())) {
            log.debug("Generated invalid initial board, regenerating ...");
        }
        currPosition = length - 1;
    }

    public void move(Move direction) {
        if (!isValidMove(direction)) {
            log.warn("Can't move into direction=[" + direction + "], try other");
            return;
        }
        switch (direction) {
            case LEFT:
                grid[currPosition] = grid[currPosition - 1];
                grid[currPosition - 1] = 0;
                currPosition -= 1;
                break;
            case RIGHT:
                grid[currPosition] = grid[currPosition + 1];
                grid[currPosition + 1] = 0;
                currPosition += 1;
                break;
            case UP:
                grid[currPosition] = grid[currPosition - columnRowLength];
                grid[currPosition - columnRowLength] = 0;
                currPosition -= columnRowLength;
                break;
            case DOWN:
                grid[currPosition] = grid[currPosition + columnRowLength];
                grid[currPosition + columnRowLength] = 0;
                currPosition += columnRowLength;
                break;
        }

    }

    public boolean isBoardComplete() {
        if (grid[0] != 0) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            if (grid[i] != i) {
                return false;
            }
        }
        return true;
    }

    private Integer[] generateBoard() {
        List<Integer> range = IntStream.range(1, length) //change geneartion here if you need 0 at any cell of the board
            .boxed()
            .collect(toList());

        shuffle(range);

        for (int i = 0; i < length - 1; i++) {
            grid[i] = range.get(i);
        }
        grid[length - 1] = 0;
        return grid;
    }

    private boolean isValidMove(Move direction) {
        switch (direction) {
            case LEFT:
                return currPosition > 0 && currPosition % columnRowLength != 0;
            case RIGHT:
                return currPosition < length - 1 && (currPosition + 1) % columnRowLength != 0;
            case UP:
                return currPosition - columnRowLength >= 0;
            case DOWN:
                return currPosition + columnRowLength < length;
        }
        return false;
    }
}
