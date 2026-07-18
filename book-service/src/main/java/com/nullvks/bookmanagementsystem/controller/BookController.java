package com.nullvks.bookmanagementsystem.controller;

import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.nullvks.bookmanagementsystem.dto.BookDTO;
import com.nullvks.bookmanagementsystem.entity.Book;
import com.nullvks.bookmanagementsystem.repository.BookRepository;
import com.nullvks.bookmanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//Notes
//http://localhost:8080/swagger-ui/index.html - Swagger UI

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
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        bookDTO = bookService.createBook(bookDTO);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> selectBook(@PathVariable long id) {
        BookDTO selectedBook = bookService.selectBook(id);
        return new ResponseEntity<>(selectedBook, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> selectAllBook(){
        List<BookDTO> allBook = bookService.selectAllBook();
        return new ResponseEntity<>(allBook, HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBookByID(@PathVariable long id, @RequestBody BookDTO bookDTO){
        BookDTO bookUpdated = bookService.updateBook(id, bookDTO);
        return new ResponseEntity<>(bookUpdated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookByID(@PathVariable long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBookByString(@RequestParam String book){
        List<BookDTO> foundBooks = bookService.searchBook(book);
        return new ResponseEntity<>(foundBooks,HttpStatus.OK);
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

    //List of all available API routes
    @GetMapping("/routes")
    public ResponseEntity<List<Map<String, String>>> getAvailableRoutes() {
        List<Map<String, String>> routes = List.of(
            Map.of(
                "method", "POST",
                "endpoint", "/api/books",
                "description", "Create a new book",
                "example", "http://localhost:8080/api/books",
                "requestBody", "{\"title\": \"Book Title\", \"authorName\": \"Author Name\"}"
            ),
            Map.of(
                "method", "GET",
                "endpoint", "/api/books/{id}",
                "description", "Get a book by ID",
                "example", "http://localhost:8080/api/books/1",
                "requestBody", "N/A"
            ),
            Map.of(
                "method", "GET",
                "endpoint", "/api/books",
                "description", "Get all books",
                "example", "http://localhost:8080/api/books",
                "requestBody", "N/A"
            ),
            Map.of(
                "method", "PUT",
                "endpoint", "/api/books/{id}",
                "description", "Update a book by ID",
                "example", "http://localhost:8080/api/books/1",
                "requestBody", "{\"title\": \"Updated Title\", \"authorName\": \"Updated Author\"}"
            ),
            Map.of(
                "method", "DELETE",
                "endpoint", "/api/books/{id}",
                "description", "Delete a book by ID",
                "example", "http://localhost:8080/api/books/1",
                "requestBody", "N/A"
            ),
            Map.of(
                "method", "GET",
                "endpoint", "/api/books/search?book={title}",
                "description", "Search books by title",
                "example", "http://localhost:8080/api/books/search?book=Gatsby",
                "requestBody", "N/A"
            ),
            Map.of(
                "method", "GET",
                "endpoint", "/api/docs/routes",
                "description", "List all available API routes (this endpoint)",
                "example", "http://localhost:8080/api/books/routes",
                "requestBody", "N/A"
            )
        );

        return new ResponseEntity<>(routes, HttpStatus.OK);
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

