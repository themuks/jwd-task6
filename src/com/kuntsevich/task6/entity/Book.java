package com.kuntsevich.task6.entity;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private List<String> genres;
    private int pageCount;
    private String authorName;

    public Book(int id, String title, List<String> genres, int pageCount, String authorName) {
        this.id = id;
        this.title = title;
        this.genres = genres;
        this.pageCount = pageCount;
        this.authorName = authorName;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
                authorName.equals(book.authorName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + genres.hashCode();
        result = 31 * result + pageCount;
        result = 31 * result + authorName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", genres=").append(genres);
        sb.append(", pageCount=").append(pageCount);
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
