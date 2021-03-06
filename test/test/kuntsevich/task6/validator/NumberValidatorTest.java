package test.kuntsevich.task6.validator;

import com.kuntsevich.task6.validator.NumberValidator;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumberValidatorTest {

    private NumberValidator numberValidator = new NumberValidator();

    @Test
    public void testIsNumberStringValidPositive() {
        assertTrue(numberValidator.isNumberStringValid("-1923"));
    }

    @Test
    public void testIsNumberStringValidNegative() {
        assertFalse(numberValidator.isNumberStringValid("1.2"));
    }
}