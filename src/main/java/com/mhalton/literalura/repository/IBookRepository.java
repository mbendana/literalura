package com.mhalton.literalura.repository;

import com.mhalton.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long>
{
    List<Book> findByTitle(String title);

    Book findByTitleContainsIgnoreCase(String name);
}
