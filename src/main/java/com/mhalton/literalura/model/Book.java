package com.mhalton.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books", schema = "literalura")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long bookId;

    @Column(unique = true, nullable = false)
    private String title;

    @ManyToOne
    private Author author;

    private String language;

    @JsonAlias("download_count")
    private long downloadCount;

    public Book() {};

    public Book(
            long bookId,
            String title,
            List<String> languages,
            long downloadCount
            )
    {
        this.bookId = bookId;
        this.title = title;
        this.language = languages.getFirst();
        this.downloadCount = downloadCount;
    }

    public String getTitle()
    {
        return title;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    @Override
    public String toString()
    {
        return """
                
                -----BOOK-----
                Title: %s
                Author: %s
                Language: %s
                Download count: %s
                --------------
                """.formatted(
                        this.title,
                        this.author.getName(),
                        this.language.toUpperCase(),
                        this.downloadCount);
    }
}
