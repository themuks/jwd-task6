package test.kuntsevich.task6.util;

import com.kuntsevich.task6.util.BookIdGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BookIdGeneratorTest {

    @BeforeMethod
    public void beforeMethod() {
        BookIdGenerator.setCurrentId(1);
    }

    @Test
    public void testGenerateId() {
        BookIdGenerator bookIdGenerator = BookIdGenerator.getInstance();
        int actual = bookIdGenerator.generateId();
        int expected = 1;
        assertEquals(actual, expected);
    }
}