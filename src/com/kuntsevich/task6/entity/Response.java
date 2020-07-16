package com.kuntsevich.task6.entity;

import java.util.List;

public class Response {

    private boolean error;
    private List<Book> result;

    public Response(boolean error, List<Book> result) {
        this.error = error;
        this.result = result;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Book getParam(int index) {
        return result.get(index);
    }

    public void setResultValue(int index, Book value) {
        result.set(index, value);
    }

    public int getResultCount() {
        return result.size();
    }

    // TODO toString etc...
}
