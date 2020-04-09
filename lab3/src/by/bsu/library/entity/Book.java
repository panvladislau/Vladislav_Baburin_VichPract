package by.bsu.library.entity;

import java.util.List;
import java.util.Objects;

public class Book {
    private String title;
    private int numberOfPages;
    private List<Author> authors;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, int numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public Book(String title, int numberOfPages, List<Author> authors) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.authors = authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Books={name='");
        sb.append(title);
        sb.append("', numberOfPages=");
        sb.append(numberOfPages);
        sb.append(", author=['");
        authors.forEach(it -> sb.append(it.getName() + "', "));
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                title.equals(book.title) &&
                authors.equals(book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numberOfPages);
    }
}
