package wix.game.fifteen;

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
    private final IGridValidator validator;
    Integer[] board;
    int height;
    int width;
    int length;
    int currPosition;

    public FifteenGame(int width, int height) {
        assert height == width;
        this.width = width;
        this.height = height;
        this.length = height * width;

        this.board = new Integer[length];
        validator = new GridValidator(width, height);

        init();
    }

    private void init() {
        while (!validator.isValid(generateBoard())){
            log.debug("Generated invalid initial board, regenerating ...");
        }
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

    public Integer[] board() {
        return board;
    }
}
