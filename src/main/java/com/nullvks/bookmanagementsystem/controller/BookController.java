package com.nullvks.bookmanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.nullvks.bookmanagementsystem.dto.BookDTO;
import com.nullvks.bookmanagementsystem.entity.Book;
import com.nullvks.bookmanagementsystem.repository.BookRepository;
import com.nullvks.bookmanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/selectBook/{id}")
    public ResponseEntity<BookDTO> selectBook(@PathVariable long id) {
        BookDTO selectedBook = bookService.selectBook(id);
        return new ResponseEntity<>(selectedBook, HttpStatus.OK);
    }

    @GetMapping("/selectALlBook")
    public ResponseEntity<List<BookDTO>> selectAllBook(){
        List<BookDTO> allBook = bookService.selectAllBook();
        return new ResponseEntity<>(allBook, HttpStatus.FORBIDDEN);
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<BookDTO> updateBookByID(@PathVariable long id, @RequestBody BookDTO bookDTO){
        BookDTO bookUpdated = bookService.updateBook(id, bookDTO);
        return new ResponseEntity<>(bookUpdated, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteNook/{id}")
    public ResponseEntity<BookDTO> deleteBookByID(@PathVariable long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //alternative, RequestParam = get the request parameters
//    @GetMapping("/selectBook/")
//    public ResponseEntity<BookDTO> selectBook(@RequestParam long id) {
//        BookDTO selectedBook = bookService.selectBook(id);
//        return new ResponseEntity<>(selectedBook, HttpStatus.OK);
//    }

//    @PostMapping
//    public ResponseEntity<BookDTO> update(@RequestBody BookDTO)


//http://localhost:8080/api/books/hello
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
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

