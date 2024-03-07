package com.springboot.rest.webservices.bookmanager.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Controller
public class ClientController {
    @Autowired
    private final BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    public ClientController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index(){
        return "welcome";
    }

    @GetMapping("/nav")
    public String nav(){
        return "nav";
    }



    @GetMapping("/add-book")
    public String addBook(ModelMap model){
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping(value = "/add-book")
    public String addNewBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:library";
    }

    @GetMapping("/library")
    public String showLibrary(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "library";
    }

    @GetMapping("/delete-book")
    public String deleteBook (@RequestParam int id){
        bookRepository.deleteById(id);
        return "redirect:/library";
    }





}
