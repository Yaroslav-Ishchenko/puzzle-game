package wix.game.validation;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
public class GridValidator implements IGridValidator {
    private final int width;
    private final int height;

    @Override
    public boolean isValid(Integer[] board) {
        int length = width * height;
        int totalInversions = 0;
        int blankPosition = 0;
        int rowNum = 0;
        //count inversions for every element in the sequence except last one
        for (int i = 0; i < length - 1; i++) {
            int inversion = 0; // count inversions to the right from current element position

            if (i % width == 0) { // count row
                rowNum++;
            }

            if (board[i] == 0) { // fid blank cell
                blankPosition = rowNum;
                continue;
            }

            for (int j = i + 1; j < length; j++) { // look ahead
                if (board[i] > board[j] && board[j] != 0) {
                    inversion++;
                }
            }
            totalInversions += inversion;
        }
        //( (grid width odd) && (#inversions even) )  ||  ( (grid width even) && ((blank on odd row from bottom) == (#inversions even)) )
        log.info("Total Inversions Count= " + totalInversions + "");
        boolean gridWithEven = width % 2 == 0;
        boolean inversionsEven = totalInversions % 2 == 0;
        boolean blankOnEvenRow = blankPosition % 2 == 0;

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
