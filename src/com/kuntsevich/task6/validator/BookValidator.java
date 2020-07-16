package com.kuntsevich.task6.validator;

import java.util.List;

public class BookValidator {

    public static final int MIN_ID_VALUE = 0;
    public static final int MAX_ID_VALUE = 10000;
    public static final int MIN_TITLE_NAME_SIZE = 2;
    public static final int MAX_TITLE_NAME_SIZE = 200;
    public static final int MIN_GENRE_NAME_LENGTH = 4;
    public static final int MAX_GENRE_NAME_LENGTH = 16;
    public static final int MIN_AUTHOR_NAME_LENGTH = 3;
    public static final int MAX_AUTHOR_NAME_LENGTH = 16;
    public static final int MIN_PAGE_COUNT_VALUE = 40;
    public static final int MAX_PAGE_COUNT_VALUE = 10000;

    public boolean isIdValid(int id) {
        return MIN_ID_VALUE <= id && id <= MAX_ID_VALUE;
    }

    public boolean isTitleValid(String title) {
        if (title == null) {
            return false;
        }
        return MIN_TITLE_NAME_SIZE <= title.length() && title.length() <= MAX_TITLE_NAME_SIZE;
    }

    public boolean isGenresValid(List<String> genres) {
        if (genres == null) {
            return false;
        }
        for (var genre : genres) {
            if (MIN_GENRE_NAME_LENGTH > genre.length() || genre.length() > MAX_GENRE_NAME_LENGTH) {
                return false;
            }
        }
        return true;
    }

    public boolean isPageCountValid(int pageCount) {
        return MIN_PAGE_COUNT_VALUE <= pageCount && pageCount <= MAX_PAGE_COUNT_VALUE;
    }

    public boolean isAuthorsValid(List<String> authors) {
        if (authors == null) {
            return false;
        }
        for (var author : authors) {
            if (MIN_AUTHOR_NAME_LENGTH > author.length() || author.length() > MAX_AUTHOR_NAME_LENGTH) {
                return false;
            }
        }
        return true;
    }
}
