package com.mhalton.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "authors", schema = "literalura")
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    private int birthYear;

    private int deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books =  new ArrayList<>();

    public Author() {}

    public Author(String name, int birthYear, int deathYear)
    {
        this.name = fixName(name);
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getName()
    {
        return name;
    }

    public void setBooks()
    {
        for (Book book : books)
            book.setAuthor(this);
    }

    public void addBook(Book book)
    {
        books.add(book);
    }

    public String fixName(String name)
    {
        if (name.contains(", "))
        {
            List<String> names = Arrays.stream(name.split(", ")).toList();
            return names.getLast() + " " + names.getFirst();
        }

        return  name;
    }

    @Override
    public String toString()
    {
        return """
                
                Author: %s
                Birth Year: %d
                Death Year: %d
                Books: %s""".formatted(
                        name,
                        birthYear,
                        deathYear,
                        books.stream().map(Book::getTitle).toList());
    }
}
