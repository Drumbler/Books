package com.example.books.controller;


import com.example.books.entity.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import com.example.books.service.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/list")
    public ModelAndView getAllBooks() {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-books");
        mav.addObject("books", bookService.getBooks());
        return mav;
    }

    @GetMapping("/addBookForm")
    public ModelAndView addBookForm() {
        ModelAndView mav = new ModelAndView("add-book-form");
        Book book = new Book();
        mav.addObject("book", book);
        return mav;
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long bookId) {
        ModelAndView mav = new ModelAndView("add-book-form");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = new Book();
        if (optionalBook.isPresent()) {
            book = optionalBook.get();
        }
        mav.addObject("book", book);
        return mav;
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam Long bookId) {
        bookRepository.deleteById(bookId);
        return "redirect:/list";
    }
}
