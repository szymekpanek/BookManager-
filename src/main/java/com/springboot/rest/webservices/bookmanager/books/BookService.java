package com.springboot.rest.webservices.bookmanager.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class BookService {
    private static int bookCounter;
    @Autowired
    BookRepository bookRepository;
    private static List<Book> books = new ArrayList<>();
    public void addBook(String title, String author, String isbn, String username) {
        Book book = new Book(++bookCounter, title, author, isbn, username);
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteById(int id) {
        Predicate<? super Book> predicate = book -> book.getId() == id;
        books.removeIf(predicate);
    }


}
