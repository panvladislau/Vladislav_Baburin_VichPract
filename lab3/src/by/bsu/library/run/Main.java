package by.bsu.library.run;

import by.bsu.library.entity.Book;
import by.bsu.library.entity.Author;
import by.bsu.library.stream.StreamController;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        log.info("Hello user");
        Author author1 = new Author("StoreName");
        Author author2 = new Author("StoreName2");
        Book it = new Book("b1",600, Arrays.asList(author1));
        Book it2 = new Book("b2",100,Arrays.asList(author1,author2));
        Book it3 = new Book("b3",210,Arrays.asList(author2));
        Book it4 = new Book("b4",100,Arrays.asList(author2));
        author1.setBooks(Arrays.asList(it,it2));
        author2.setBooks(Arrays.asList(it2,it3,it4));
        List<Book> booksList = Arrays.asList(it,it2,it3,it4);
        StreamController stream = new StreamController();
        long time = System.nanoTime();
        Boolean flag = stream.isMoreThen200Pages(booksList);
        time = System.nanoTime() - time;
        System.out.println("isMoreThen200Pages: " + flag);
        log.info("time without p: " + time);
        time = System.nanoTime();
        flag = stream.isMoreThen200PagesPar(booksList);
        time = System.nanoTime() - time;
        System.out.println("isMoreThen200PagesPar: " + flag);
        log.info("time with p: " + time);
        System.out.println("\nMax by count: " + stream.max(booksList));
        try {
            System.out.println("\nMin by count: " + stream.min(booksList));
        } catch (Exception ex){
            System.out.println("\nNo values represented");
        }
        System.out.println("\nExist in one store: " + stream.oneAuthor(booksList));
        time = System.nanoTime();
        List<Book> result = stream.sortByNameAndPages(booksList);
        time = System.nanoTime() - time;
        System.out.println("\nsortByNameAndPages: " + result);
        log.info("time without p: " + time);
        time = System.nanoTime();
        result = stream.sortByNameAndPagesPar(booksList);
        time = System.nanoTime() - time;
        System.out.println("\nsortByNameAndPagesPar " + result);
        log.info("time with p: " + time);
        System.out.println("Unique a: " + stream.getAuthor(booksList));
    }
}