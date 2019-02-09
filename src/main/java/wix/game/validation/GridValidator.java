package wix.game.validation;

public class GridValidator implements IGridValidator {
    int width;
    int height;

    public GridValidator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isValid(Integer[] board) { // generify with comparator
        int length = width * height;
        int totalInversions = 0;
            for (int i = 0; i < length - 1; i++) {
                int inversion = 0;
                for (int j = i + 1; j < length - 2; j++) {
                    if (board[i] > board[j]) {
                        inversion++;
                    }
                }
                totalInversions += inversion;
            }
            //( (grid width odd) && (#inversions even) )  ||  ( (grid width even) && ((blank on odd row from bottom) == (#inversions even)) )
            boolean gridWithEven = width % 2 == 0;
            boolean inversionsEven = totalInversions % 2 == 0;
            boolean blankOnOddRow = height % 2 == 0;

            return gridWithEven && blankOnOddRow && inversionsEven|| (!gridWithEven && inversionsEven);
    }
}
