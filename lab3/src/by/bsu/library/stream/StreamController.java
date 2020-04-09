package by.bsu.library.stream;

import by.bsu.library.entity.Book;
import by.bsu.library.entity.Author;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamController {

    private static final Logger log = LogManager.getLogger(StreamController.class.getName());

    public StreamController() {
    }

    public boolean isMoreThen200Pages(List<Book> bookList) {
        return bookList.stream().filter((it) -> it.getNumberOfPages() > 200).peek(it -> log.debug(it)).count() > 0 ? true : false;
    }

    public boolean isMoreThen200PagesPar(List<Book> bookList) {
        return bookList.parallelStream().filter((it) -> it.getNumberOfPages() > 200).peek(it -> log.debug(it)).count() > 0 ? true : false;
    }

    public Book max(List<Book> bookList) {
        Optional<Book> max_optional = bookList.stream().max(Comparator.comparingInt(Book::getNumberOfPages));
        if (!max_optional.isPresent())
            System.out.println("List is probably empty");
        return max_optional.orElse(new Book());
    }

    public Book maxPar(List<Book> bookList) {
        Optional<Book> max_optional = bookList.parallelStream().max(Comparator.comparingInt(Book::getNumberOfPages));
        if (!max_optional.isPresent())
            System.out.println("List is probably empty");
        return max_optional.orElse(new Book());
    }

    public Book min(List<Book> bookList) throws Exception {
        return bookList.stream().min(Comparator.comparingInt(Book::getNumberOfPages)).orElseThrow(Exception::new);
    }

    public Book minPar(List<Book> bookList) throws Exception {
        return bookList.parallelStream().min(Comparator.comparingInt(Book::getNumberOfPages)).orElseThrow(Exception::new);
    }

    public List<Book> oneAuthor(List<Book> bookList) {
        return bookList.stream().filter((it) -> it.getAuthors().size() == 1).peek(it -> log.debug(it)).collect((Collectors.toCollection(ArrayList::new)));
    }

    public List<Book> oneAuthorPar(List<Book> bookList) {
        return bookList.parallelStream().filter((it) -> it.getAuthors().size() == 1).peek(it -> log.debug(it)).collect((Collectors.toCollection(ArrayList::new)));
    }

    public List<Book> sortByNameAndPages(List<Book> bookList) {
        return bookList.parallelStream().sorted(Comparator.comparing(Book::getTitle).thenComparing(Book::getNumberOfPages)).collect((Collectors.toCollection(ArrayList::new)));
    }


    public List<Book> sortByNameAndPagesPar(List<Book> bookList) {
        return bookList.parallelStream().sorted(Comparator.comparing(Book::getTitle).thenComparing(Book::getNumberOfPages)).collect((Collectors.toCollection(ArrayList::new)));
    }

    public List<Author> getAuthor(List<Book> bookList) {
        List<Author> authors = new ArrayList();
        bookList.stream().forEach(item -> {
            authors.addAll(item.getAuthors());
            log.debug(item.getAuthors());
        });
        return authors.stream().distinct().collect(Collectors.toList());
    }
}
