package com.nullvks.bookmanagementsystem.service;

import com.nullvks.bookmanagementsystem.dto.BookDTO;
import com.nullvks.bookmanagementsystem.entity.Book;
import com.nullvks.bookmanagementsystem.exception.BookNotFoundException;
import com.nullvks.bookmanagementsystem.mapper.BookMapper;
import com.nullvks.bookmanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.stream.Collectors;
//import com.nullvks.bookmanagementsystem.mapper;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    //insert book
    public BookDTO createBook(BookDTO bookDTO){
        Book savedBook = BookMapper.toEntity(bookDTO);

        // send to kafka topic group for consumer
        kafkaTemplate.send("book-created", BookMapper.toDTO(savedBook));

        bookDTO = BookMapper.toDTO(bookRepository.save(savedBook));
        return bookDTO;
    }

//    public BookDTO createBook(BookDTO bookDTO) {
//        Book savedBook = bookRepository.save(BookMapper.toEntity(bookDTO));
//
//        // --- DID YOU ADD THIS LINE? ---
//        kafkaTemplate.send("book-created", BookMapper.toDTO(savedBook));
//
//        return BookMapper.toDTO(savedBook);
//    }

    //select book
    // 1. "books" is the name of the folder in Redis
    // 2. key = "#id" means "Use the ID as the unique filename"
    @Cacheable(value = "books", key = "#bookID")
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
    public BookDTO updateBook(long bookID, BookDTO bookDTO){
        Book book = bookRepository.findById(bookID).orElseThrow(()-> new RuntimeException("Book not found related to the id inserted"));
        book.setTitle(bookDTO.getTitle());
        book.setAuthorName(bookDTO.getAuthorName());
        book.setCreatedBy(bookDTO.getCreatedBy());
        BookDTO bookDTOreturned = BookMapper.toDTO(bookRepository.save(book));
        return bookDTOreturned;
    }

    //delete book
    public void deleteBook(long bookID){
        if(!bookRepository.existsById(bookID)){
            throw new BookNotFoundException("Cannot find book to be deleted for id:"+ bookID);
        }
        bookRepository.deleteById(bookID);
    }

    //search book
    public List<BookDTO> searchBook(String bookQuery){
        List<Book> book = bookRepository.findByAuthorNameContainingIgnoreCase(bookQuery);
        //cant use BookMapper.toDTO() use below for List
        List bookDTO = book.stream().map(BookMapper::toDTO).collect(Collectors.toList());
        return bookDTO;
    }






    //update book
//    public Book updateBook(long bookID, String author, String bookTitle){
//        BookDTO bookReturned = selectBook(bookID);
////        bookReturned.set
//        return bookReturned;
//    }

    //delete book
  //  public void deleteBook(long bookID){
//        bookRepository.deleteById(bookID);
//    }



//    @Autowired
//    private BookRepository bookRepository;
//
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
}

