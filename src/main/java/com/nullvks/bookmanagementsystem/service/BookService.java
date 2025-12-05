package com.nullvks.bookmanagementsystem.service;

import com.nullvks.bookmanagementsystem.dto.BookDTO;
import com.nullvks.bookmanagementsystem.entity.Book;
import com.nullvks.bookmanagementsystem.mapper.BookMapper;
import com.nullvks.bookmanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
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

    //select book
    public BookDTO selectBook(long bookID){
        Book bookEntity =  bookRepository.findById(bookID).orElseThrow(()-> new RuntimeException("Book not found"));
        BookDTO bookDTO = BookMapper.toDTO(bookEntity);
        return bookDTO;
    }

    //select all book
    public List<BookDTO> selectAllBook(){
        List<Book> bookEntity = bookRepository.findAll();
        // This 'Stream' code converts every Book in the list to a BookDTO
        List<BookDTO> bookDTO = bookEntity.stream().map(BookMapper::toDTO).collect(Collectors.toList());
//        BookDTO bookDTO = BookMapper.toDTO(bookEntity);
        return bookDTO;
    }

    //update book
//    public Book updateBook(long bookID, String author, String bookTitle){
//        BookDTO bookReturned = selectBook(bookID);
////        bookReturned.set
//        return bookReturned;
//    }

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

