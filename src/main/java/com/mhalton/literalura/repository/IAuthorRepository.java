package com.mhalton.literalura.repository;

import com.mhalton.literalura.model.Author;
import com.mhalton.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, Long>
{
//    List<Author> findAll();

    // Author search

    @Query("SELECT a FROM Author a JOIN a.books b ORDER BY a.name, a.birthYear")
    List<Author> findAllAuthors();

    Author findByNameIgnoreCase(String name);

    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND a.deathYear >= :year ORDER BY a.birthYear")
    List<Author> findAuthorsByYear(int year);

    // Book search
    @Query("SELECT b FROM Book b JOIN b.author a ORDER BY b.downloadCount DESC")
    List<Book> findAllBooks();

    @Query("SELECT b FROM Book b JOIN b.author a WHERE b.language = :language ORDER BY a.name, b.downloadCount DESC")
    List<Book> findBooksByLanguage(String language);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.name = :author ORDER BY b.downloadCount DESC")
    List<Book> findBooksByAuthor(String author);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE b.title ILIKE %:title%")
    List<Book> findBooksByTitleContainsIgnoreCase(String title);

    // Other search
    @Query("SELECT DISTINCT b.language FROM Book b JOIN b.author a ORDER BY b.language")
    List<String> findLanguages();

    @Query("SELECT DISTINCT a.name FROM Author a ORDER BY a.name")
    List<String> findAuthors();
}
