package wix.game.validation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GridValidatorTest {
    GridValidator gridValidator;

    @Before
    public void setUp() {
        gridValidator = new GridValidator(4, 4);
    }

    @Test
    public void testOnEvenRowsGrid() {
        Integer[] sequence = {12, 1, 10, 2, 7, 11, 4, 14, 5, 9, 15, 8, 13, 6, 3, 0};
        assertTrue(gridValidator.isValid(sequence));
    }

}