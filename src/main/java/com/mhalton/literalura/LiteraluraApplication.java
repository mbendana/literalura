package com.mhalton.literalura;

import com.mhalton.literalura.main.Main;
import com.mhalton.literalura.repository.IAuthorRepository;
import com.mhalton.literalura.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner
{
//    private final IBookRepository bookRepo;
    private final IAuthorRepository authorRepo;

    public static void main(String[] args)
    {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Autowired
    public LiteraluraApplication(IBookRepository bookRepo, IAuthorRepository authorRepo)
    {
//        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    // The exception added is to handle the Thread.sleep function
    @Override
    public void run(String... args) throws InterruptedException
    {
//        Main main = new Main(bookRepo, authorRepo);
        Main main = new Main(authorRepo);
        main.runApplication();
    }
}
