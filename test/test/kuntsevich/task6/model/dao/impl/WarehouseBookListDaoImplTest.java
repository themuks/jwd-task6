package test.kuntsevich.task6.model.dao.impl;

import com.kuntsevich.task6.creator.BookCreator;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.entity.BookWarehouse;
import com.kuntsevich.task6.exception.BookCreationException;
import com.kuntsevich.task6.exception.DaoException;
import com.kuntsevich.task6.model.dao.impl.WarehouseBookListDaoImpl;
import com.kuntsevich.task6.util.BookIdGenerator;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class WarehouseBookListDaoImplTest {

    private WarehouseBookListDaoImpl warehouseBookListDao = new WarehouseBookListDaoImpl();
    private BookCreator bookCreator = new BookCreator();

    @BeforeClass
    public void beforeClass() {
        BookWarehouse.getInstance().clear();
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
            Book actual = bookCreator.createBook("Title3", "genre3", 102, List.of("author3"));
            warehouseBookListDao.addBook(actual);
            Book expected = BookWarehouse.getInstance().getBook(BookWarehouse.getInstance().getBooksCount() - 1);
            assertEquals(actual, expected);
        } catch (BookCreationException | DaoException e) {
            fail();
        }
    }

    @Test
    public void testRemoveBookPositive() {
        try {
            Book book = bookCreator.createBook("Title", "genre", 100, List.of("author"));
            int expected = BookWarehouse.getInstance().getBooksCount();
            warehouseBookListDao.addBook(book);
            warehouseBookListDao.removeBook(book);
            int actual = BookWarehouse.getInstance().getBooksCount();
            assertEquals(actual, expected);
        } catch (BookCreationException | DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindByIdPositive() {
        try {
            Book actual = warehouseBookListDao.findById(1);
            Book expected = new Book(1, "Title2", "genre2", 101, List.of("author2"));
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindByTitlePositive() {
        try {
            List<Book> actual = warehouseBookListDao.findByTitle("Title2");
            List<Book> expected = List.of(new Book(1, "Title2", "genre2", 101, List.of("author2")));
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindByGenrePositive() {
        try {
            List<Book> actual = warehouseBookListDao.findByGenre("genre2");
            List<Book> expected = List.of(new Book(1, "Title2", "genre2", 101, List.of("author2")));
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindByPageCountPositive() {
        try {
            List<Book> actual = warehouseBookListDao.findByPageCount(102);
            List<Book> expected = List.of(new Book(1, "Title3", "genre3", 102, List.of("author3")));
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testFindByAuthorsPositive() {
        try {
            List<Book> actual = warehouseBookListDao.findByAuthors(List.of("author2"));
            List<Book> expected = List.of(new Book(1, "Title2", "genre2", 101, List.of("author2")));
            assertEquals(actual, expected);
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void testSortByIdPositive() {
        List<Book> actual = warehouseBookListDao.sortById();
        List<Book> expected = List.of(
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(1, "Title3", "genre3", 102, List.of("author3")),
                new Book(2, "Title1", "genre1", 101, List.of("author1"))
        );
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByTitlePositive() {
        List<Book> actual = warehouseBookListDao.sortByTitle();
        List<Book> expected = List.of(
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        );
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByGenrePositive() {
        List<Book> actual = warehouseBookListDao.sortByGenre();
        List<Book> expected = List.of(
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        );
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByPageCountPositive() {
        List<Book> actual = warehouseBookListDao.sortByPageCount();
        List<Book> expected = List.of(
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        );
        assertEquals(actual, expected);
    }

    @Test
    public void testSortByAuthorsPositive() {
        List<Book> actual = warehouseBookListDao.sortByAuthors();
        List<Book> expected = List.of(
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        );
        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testAddBookException() throws DaoException {
        try {
            Book book = bookCreator.createBook("Title2", "genre2", 101, List.of("author2"));
            warehouseBookListDao.addBook(book);
        } catch (BookCreationException e) {
            fail();
        }
    }

    @Test(expectedExceptions = DaoException.class)
    public void testRemoveBookException() throws DaoException {
        try {
            Book book = bookCreator.createBook("Title", "genre", 100, List.of("author"));
            warehouseBookListDao.removeBook(book);
        } catch (BookCreationException e) {
            fail();
        }
    }

    @Test(expectedExceptions = DaoException.class)
    public void testFindByIdException() throws DaoException {
        warehouseBookListDao.findById(-1);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testFindByTitleException() throws DaoException {
        warehouseBookListDao.findByTitle("Invalid title");
    }

    @Test(expectedExceptions = DaoException.class)
    public void testFindByGenresException() throws DaoException {
        warehouseBookListDao.findByGenre("Invalid genre");
    }

    @Test(expectedExceptions = DaoException.class)
    public void testFindByPageCountException() throws DaoException {
        warehouseBookListDao.findByPageCount(-1);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testFindByAuthorsException() throws DaoException {
        warehouseBookListDao.findByAuthors(List.of("Invalid author"));
    }
}