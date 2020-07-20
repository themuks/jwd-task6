package test.kuntsevich.task6.model.service.impl;

import com.kuntsevich.task6.creator.BookCreator;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.entity.BookWarehouse;
import com.kuntsevich.task6.exception.BookCreationException;
import com.kuntsevich.task6.exception.DaoException;
import com.kuntsevich.task6.exception.ServiceException;
import com.kuntsevich.task6.model.dao.impl.WarehouseBookListDaoImpl;
import com.kuntsevich.task6.model.service.impl.BookServiceImpl;
import com.kuntsevich.task6.util.BookIdGenerator;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class BookServiceImplTest {

    private BookCreator bookCreator = new BookCreator();
    private WarehouseBookListDaoImpl warehouseBookListDao = new WarehouseBookListDaoImpl();

    @BeforeClass
    public void beforeClass() {
        BookWarehouse.getInstance().clear();
        BookIdGenerator.setCurrentId(1);
        try {
            Book book = bookCreator.createBook("Title2", "genre2", 101, List.of("author2"));
            warehouseBookListDao.addBook(book);
            book = bookCreator.createBook("Title1", "genre1", 101, List.of("author1"));
            warehouseBookListDao.addBook(book);
        } catch (BookCreationException | DaoException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        BookIdGenerator.setCurrentId(1);
    }

    @AfterClass
    public void afterClass() {
        BookWarehouse.getInstance().clear();
    }

    @Test
    public void testAddBookPositive() {
        try {
            Book expected = new Book(1, "Title3", "genre3", 102, List.of("author3"));
            BookServiceImpl.getInstance().addBook("Title3", "genre3", "102", "author3");
            Book actual = BookWarehouse.getInstance().getBook(BookWarehouse.getInstance().getBooksCount() - 1);
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testRemoveBookPositive() {
        try {
            int expected = BookWarehouse.getInstance().getBooksCount();
            BookServiceImpl.getInstance().addBook("Title", "genre", "100", "author");
            BookServiceImpl.getInstance().removeBook("1", "Title", "genre", "100", "author");
            int actual = BookWarehouse.getInstance().getBooksCount();
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail(e.toString());
        }
    }

    @Test
    public void testFindByIdPositive() {
        try {
            Book actual = BookServiceImpl.getInstance().findById("1");
            Book expected = new Book(1, "Title2", "genre2", 101, List.of("author2"));
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindByTitlePositive() {
        try {
            List<Book> actual = BookServiceImpl.getInstance().findByTitle("Title2");
            List<Book> expected = List.of(new Book(1, "Title2", "genre2", 101, List.of("author2")));
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindByGenrePositive() {
        try {
            List<Book> actual = BookServiceImpl.getInstance().findByGenre("genre2");
            List<Book> expected = List.of(new Book(1, "Title2", "genre2", 101, List.of("author2")));
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindByPageCountPositive() {
        try {
            List<Book> actual = BookServiceImpl.getInstance().findByPageCount("102");
            List<Book> expected = List.of(new Book(1, "Title3", "genre3", 102, List.of("author3")));
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testFindByAuthorsPositive() {
        try {
            List<Book> actual = BookServiceImpl.getInstance().findByAuthors("author2");
            List<Book> expected = List.of(new Book(1, "Title2", "genre2", 101, List.of("author2")));
            assertEquals(actual, expected);
        } catch (ServiceException e) {
            fail();
        }
    }

    @Test
    public void testSortByIdPositive() {
        List<Book> actual = BookServiceImpl.getInstance().sortById();
        List<Book> expected = List.of(
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(1, "Title3", "genre3", 102, List.of("author3")),
                new Book(2, "Title1", "genre1", 101, List.of("author1"))
        );
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByTitlePositive() {
        List<Book> actual = BookServiceImpl.getInstance().sortByTitle();
        List<Book> expected = List.of(
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        );
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByGenrePositive() {
        List<Book> actual = BookServiceImpl.getInstance().sortByGenre();
        List<Book> expected = List.of(
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        );
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByPageCountPositive() {
        List<Book> actual = BookServiceImpl.getInstance().sortByPageCount();
        List<Book> expected = List.of(
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        );
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByAuthorsPositive() {
        List<Book> actual = BookServiceImpl.getInstance().sortByAuthors();
        List<Book> expected = List.of(
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        );
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testAddBookException() throws ServiceException {
        BookServiceImpl.getInstance().addBook("Title2", "genre2", "101", "author2");
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testRemoveBookException() throws ServiceException {
        BookServiceImpl.getInstance().removeBook("id", "Title", "genre", "100", "author");
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindByIdException() throws ServiceException {
        BookServiceImpl.getInstance().findById("-1");
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindByTitleException() throws ServiceException {
        BookServiceImpl.getInstance().findByTitle("Invalid title");
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindByGenresException() throws ServiceException {
        BookServiceImpl.getInstance().findByGenre("Invalid genre");
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindByPageCountException() throws ServiceException {
        BookServiceImpl.getInstance().findByPageCount("-1");
    }

    @Test(expectedExceptions = ServiceException.class)
    public void testFindByAuthorsException() throws ServiceException {
        BookServiceImpl.getInstance().findByAuthors("Invalidauthor");
    }
}