package com.nullvks.bookmanagementsystem.mapper;

import com.nullvks.bookmanagementsystem.dto.BookDTO;
import com.nullvks.bookmanagementsystem.entity.Book;

public class BookMapper {

    public static BookDTO toDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthorName(book.getAuthorName());
        bookDTO.setCreatedBy(book.getCreatedBy());
        return bookDTO;
    }

    public static Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthorName(bookDTO.getAuthorName());
        book.setCreatedBy(bookDTO.getCreatedBy());
        return book;
    }

    //to use MapStruct (optional)
//    @Mapper(componentModel = "spring")
//    public interface BookMapper {
//        Book toEntity(BookDTO dto);
//        BookDTO toDTO(Book book);
//    }


}
