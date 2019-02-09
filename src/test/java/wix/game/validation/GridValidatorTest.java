package wix.game.validation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GridValidatorTest {
    private GridValidator gridValidator;

    @Before
    public void setUp() {
        gridValidator = new GridValidator(4, 4);
    }

    @Test
    public void testEvenWidthBlankEvenRowInversionsEvenGridValid() {
        Integer[] sequence = {3, 9, 1, 15, 14, 11, 4, 6, 13, 10, 12, 2, 7, 8, 5, 0};
        assertTrue(gridValidator.isValid(sequence));
    }

    @Test
    public void testEvenWidthBlankEvenRowInversionsOddGridInvalid() {
        Integer[] sequence = {12, 1, 10, 2, 7, 11, 4, 14, 5, 9, 15, 8, 13, 6, 3, 0};
        assertFalse(gridValidator.isValid(sequence));
    }

    @Test
    public void testEvenWidthBlankOddRowInversionsOddGridValid() {
        Integer[] sequence = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 15, 13, 14, 12};
        assertTrue(gridValidator.isValid(sequence));
    }

    @Test
    public void testOddWidthBlankOddRowInversionsEvenGridValid() {
        gridValidator = new GridValidator(3, 3);
        Integer[] sequence = {1, 8, 2, 5, 4, 3, 7, 6, 0};
        assertTrue(gridValidator.isValid(sequence));
    }

    @Test
    public void testOddWidthBlankEvenRowInversionsOddGridInalid() {
        gridValidator = new GridValidator(3, 3);
        Integer[] sequence = {1, 8, 2, 5, 4, 3, 6, 7, 0};
        assertFalse(gridValidator.isValid(sequence));
    }
}