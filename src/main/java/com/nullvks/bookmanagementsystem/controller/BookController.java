package com.nullvks.bookmanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.nullvks.bookmanagementsystem.dto.BookDTO;
import com.nullvks.bookmanagementsystem.entity.Book;
import com.nullvks.bookmanagementsystem.repository.BookRepository;
import com.nullvks.bookmanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
//    @Autowired
//    private BookDTO bookDTO;
//
//    public ResponseEntity

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        bookDTO = bookService.createBook(bookDTO);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

//    @Autowired
//    private BookDTO bookDTO;

//    @GetMapping
//    public BookDTO getBookTitle() {
//        //bookDTO.set
//
//        //book.setViewName("welcome");
//        //book.getModel().put("data", "Welcome home man");
//
//        //return bookDTO;
//    }
//    BookRepository bookRepository;
//
//    private final BookController(
//
//    )
//    @Autowired
//    private BookService bookService;
//
//    @GetMapping
//    public List<Book> getBooks() {
//        return bookService.getAllBooks();
//    }
}

