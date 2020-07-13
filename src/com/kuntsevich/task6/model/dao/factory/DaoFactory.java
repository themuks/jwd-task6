package com.kuntsevich.task6.model.dao.factory;

import com.kuntsevich.task6.model.dao.BookListDao;
import com.kuntsevich.task6.model.dao.impl.WarehouseBookListDaoImpl;

public class DaoFactory {

    private static volatile DaoFactory instance;

    private final BookListDao warehouseBookListDao = new WarehouseBookListDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    instance = new DaoFactory();
                }
            }
        }
        return instance;
    }

    public BookListDao getBookListDao() {
        return warehouseBookListDao;
    }
}
