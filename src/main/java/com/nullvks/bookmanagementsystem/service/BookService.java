package com.nullvks.bookmanagementsystem.service;

import com.nullvks.bookmanagementsystem.dto.BookDTO;
import com.nullvks.bookmanagementsystem.entity.Book;
import com.nullvks.bookmanagementsystem.mapper.BookMapper;
import com.nullvks.bookmanagementsystem.repository.BookRepository;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.nullvks.bookmanagementsystem.mapper;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    //insert book
    public BookDTO createBook(BookDTO bookDTO){
        Book book = BookMapper.toEntity(bookDTO);
        bookDTO = BookMapper.toDTO(bookRepository.save(book));
        return bookDTO;
    }

    //read book
    public Book readBook(long bookID){
        return bookRepository.findById(bookID).orElseThrow(()-> new RuntimeException("Book not found"));
    }

    //update book
    public Book updateBook(long bookID, String author, String bookTitle){
        Book bookReturned = readBook(bookID);
//        bookReturned.set
        return bookReturned;
    }

    //delete book
    public void deleteBook(long bookID){
        bookRepository.deleteById(bookID);
    }



//    @Autowired
//    private BookRepository bookRepository;
//
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
}

