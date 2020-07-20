package test.kuntsevich.task6.creator;

import com.kuntsevich.task6.creator.BookCreator;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.exception.BookCreationException;
import com.kuntsevich.task6.util.BookIdGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class BookCreatorTest {

    private BookCreator bookCreator = new BookCreator();

    @BeforeMethod
    public void beforeMethod() {
        BookIdGenerator.setCurrentId(1);
    }

    @Test
    public void testCreateBookPositive() {
        try {
            Book actual = bookCreator.createBook("Title", "genre", 100, List.of("Author"));
            Book expected = new Book(1, "Title", "genre", 100, List.of("Author"));
            assertEquals(actual, expected);
        } catch (BookCreationException e) {
            fail(e.toString());
        }
    }

    @Test(expectedExceptions = BookCreationException.class)
    public void testCreateBookException() throws BookCreationException {
        bookCreator.createBook("", "", -1, List.of(""));
    }
}