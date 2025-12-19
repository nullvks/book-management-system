package com.nullvks.bookmanagementsystem.repository;

import com.nullvks.bookmanagementsystem.dto.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nullvks.bookmanagementsystem.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthorNameContainingIgnoreCase (String author);
}
