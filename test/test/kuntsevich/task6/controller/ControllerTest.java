package test.kuntsevich.task6.controller;

import com.kuntsevich.task6.controller.Controller;
import com.kuntsevich.task6.controller.entity.Request;
import com.kuntsevich.task6.controller.entity.Response;
import com.kuntsevich.task6.creator.BookCreator;
import com.kuntsevich.task6.entity.Book;
import com.kuntsevich.task6.entity.BookWarehouse;
import com.kuntsevich.task6.exception.BookCreationException;
import com.kuntsevich.task6.exception.DaoException;
import com.kuntsevich.task6.model.dao.impl.WarehouseBookListDaoImpl;
import com.kuntsevich.task6.util.BookIdGenerator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ControllerTest {

    private Controller controller = new Controller();
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
    public void testExecuteTaskEmptyCommand() {
        Map<String, String> params = new HashMap<>();
        Request request = new Request("INVALID_COMMAND", params);
        Response actual = controller.executeTask(request);
        Response expected = new Response(false, new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskAddBookCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("title", "Title3");
        params.put("genre", "genre3");
        params.put("pageCount", "102");
        params.put("authors", "author3");
        Request request = new Request("ADD_BOOK_COMMAND", params);
        Response actual = controller.executeTask(request);
        Response expected = new Response(false, new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskRemoveBookCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        params.put("title", "Title");
        params.put("genre", "genre");
        params.put("pageCount", "100");
        params.put("authors", "author");
        Request request = new Request("REMOVE_BOOK_COMMAND", params);
        BookWarehouse.getInstance().add(new Book(1, "Title", "genre", 100, List.of("author")));
        Response actual = controller.executeTask(request);
        Response expected = new Response(false, new ArrayList<>());
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindBookByIdCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        Request request = new Request("FIND_BOOK_BY_ID_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(1, "Title2", "genre2", 101, List.of("author2")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindBookByTitleCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("title", "Title2");
        Request request = new Request("FIND_BOOKS_BY_TITLE_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(1, "Title2", "genre2", 101, List.of("author2")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindBookByGenreCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("genre", "genre2");
        Request request = new Request("FIND_BOOKS_BY_GENRE_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(1, "Title2", "genre2", 101, List.of("author2")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindBookByPageCountCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("pageCount", "102");
        Request request = new Request("FIND_BOOKS_BY_PAGE_COUNT_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(1, "Title3", "genre3", 102, List.of("author3")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskFindBookByAuthorsCommand() {
        Map<String, String> params = new HashMap<>();
        params.put("authors", "author2");
        Request request = new Request("FIND_BOOKS_BY_AUTHORS_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>();
        result.add(new Book(1, "Title2", "genre2", 101, List.of("author2")));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskSortBooksByIdCommand() {
        Map<String, String> params = new HashMap<>();
        Request request = new Request("SORT_BOOKS_BY_ID_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>(List.of(
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(1, "Title3", "genre3", 102, List.of("author3")),
                new Book(2, "Title1", "genre1", 101, List.of("author1"))
        ));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskSortBooksByTitleCommand() {
        Map<String, String> params = new HashMap<>();
        Request request = new Request("SORT_BOOKS_BY_TITLE_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>(List.of(
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        ));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskSortBooksByGenreCommand() {
        Map<String, String> params = new HashMap<>();
        Request request = new Request("SORT_BOOKS_BY_GENRE_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>(List.of(
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        ));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskSortBooksByPageCountCommand() {
        Map<String, String> params = new HashMap<>();
        Request request = new Request("SORT_BOOKS_BY_PAGE_COUNT_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>(List.of(
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        ));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }

    @Test
    public void testExecuteTaskSortBooksByAuthorsCommand() {
        Map<String, String> params = new HashMap<>();
        Request request = new Request("SORT_BOOKS_BY_AUTHORS_COMMAND", params);
        Response actual = controller.executeTask(request);
        List<Book> result = new ArrayList<>(List.of(
                new Book(1, "Title2", "genre2", 101, List.of("author2")),
                new Book(2, "Title1", "genre1", 101, List.of("author1")),
                new Book(1, "Title3", "genre3", 102, List.of("author3"))
        ));
        Response expected = new Response(false, result);
        assertEquals(actual, expected);
    }
}