package com.kuntsevich.task6.entity;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private List<String> genres;
    private int pageCount;
    private List<String> authors;

    public Book(int id, String title, List<String> genres, int pageCount, List<String> authors) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.pageCount = pageCount;
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre(int index) {
        return genres.get(index);
    }

    public void setGenre(int index, String genre) {
        this.genres.set(index, genre);
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getAuthor(int index) {
        return authors.get(index);
    }

    public void setAuthor(String author, int index) {
        this.authors.set(index, author);
    }

    public int getAuthorsCount() {
        return authors.size();
    }

    public int getGenresCount() {
        return genres.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return id == book.id &&
                pageCount == book.pageCount &&
                title.equals(book.title) &&
                genres.equals(book.genres) &&
                authors.equals(book.authors);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + genres.hashCode();
        result = 31 * result + pageCount;
        result = 31 * result + authors.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", genres=").append(genres);
        sb.append(", pageCount=").append(pageCount);
        sb.append(", authors=").append(authors);
        sb.append('}');
        return sb.toString();
    }
}
