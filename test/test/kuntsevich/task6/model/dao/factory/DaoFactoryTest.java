package test.kuntsevich.task6.model.dao.factory;

import com.kuntsevich.task6.model.dao.factory.DaoFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DaoFactoryTest {

    @Test
    public void testGetBookListDao() {
        assertNotNull(DaoFactory.getInstance().getBookListDao());
    }
}