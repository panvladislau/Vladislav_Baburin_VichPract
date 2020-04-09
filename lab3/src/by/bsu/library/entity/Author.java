package by.bsu.library.entity;

import java.util.List;
import java.util.Objects;

public class Author {
    private String name;
    private short age;
    List<Book> books;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, short age) {
        this.name = name;
        this.age = age;
    }

    public Author(String name, short age, List<Book> books) {
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public short getAge() {
        return age;
    }

    public List<Book> getBooks() {
        return books;
    }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Author={name='");
        sb.append(name);
        sb.append("', age=");
        sb.append(age);
        sb.append(", books=['");
        books.forEach(it -> sb.append(it.getTitle() + "', "));
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return age == author.age &&
                name.equals(author.name) &&
                books.equals(author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

}
