package com.kuntsevich.task6.factory;

import com.kuntsevich.task6.dao.BookListDao;
import com.kuntsevich.task6.dao.impl.WarehouseBookListDaoImpl;

public class DaoFactory {

    private static volatile DaoFactory instance;

    private final BookListDao warehouseBookListDao = new WarehouseBookListDaoImpl();

    private DaoFactory() {}

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
