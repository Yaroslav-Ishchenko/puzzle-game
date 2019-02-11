package game.puzzle.core.validation;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
public class GridValidator implements IGridValidator {
    private final int length;

    @Override
    public boolean isValid(Integer[] board) {
        int columnRowLength = (int) Math.sqrt(length);
        int inversion = 0;
        int blankPosition = 0;
        int rowNum = 0;
        //count inversions for every element in the sequence except last one
        for (int i = 0; i < length; i++) {

            if (i % columnRowLength == 0) { // count row
                rowNum++;
            }

            if (board[i] == 0) { // fid blank cell
                blankPosition = rowNum;
                continue;
            }

            for (int j = i + 1; j < length; j++) { // look ahead
                if (board[j] != 0 && board[i] > board[j]) {
                    inversion++;
                }
            }
        }

        log.debug("Total Inversions Count= " + inversion + "");
        boolean gridWithEven = columnRowLength % 2 == 0;
        boolean inversionsEven = inversion % 2 == 0;
        boolean blankOnEvenRow = blankPosition % 2 == 0;
        log.debug("gridWithEven= " + gridWithEven + "");
        log.debug("inversionsEven= " + inversionsEven + "");
        log.debug("blankOnEvenRow= " + blankOnEvenRow + "");

        if (gridWithEven) {
            if (blankOnEvenRow) {
                return inversionsEven;
            } else {
                return !inversionsEven;
           }
        } else {
            return inversionsEven;
        }
    }
}
