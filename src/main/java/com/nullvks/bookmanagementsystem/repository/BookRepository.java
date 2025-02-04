package com.nullvks.bookmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nullvks.bookmanagementsystem.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
}
