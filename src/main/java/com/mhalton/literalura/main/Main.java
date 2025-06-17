package com.mhalton.literalura.main;

import com.mhalton.literalura.model.*;
import com.mhalton.literalura.repository.IAuthorRepository;
import com.mhalton.literalura.repository.IBookRepository;
import com.mhalton.literalura.service.APIConsumer;
import com.mhalton.literalura.service.Converter;
import com.mhalton.literalura.service.Language;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main
{
//    private final IBookRepository bookRepo;
    private final IAuthorRepository authorRepo;
    private final Scanner scanner = new Scanner(System.in);

    private final String intExpected    = "\nInteger input expected! ";
    private final String wrongOpt       = "\nWrong option entered! ";
    private final String noAuthorsFound = "\nNo authors found in the database! ";
    private final String backToMain     = "Back to main menu...\n";

//    public Main(IBookRepository booKRepo,  IAuthorRepository authorRepo)
    public Main( IAuthorRepository authorRepo)
    {
        this.authorRepo = authorRepo;
//        this.bookRepo = booKRepo;
    }

    // The exception added is to handle the Thread.sleep function
    public void runApplication() throws InterruptedException
    {
        int option = -1;

        String mainMenu = """
                    
                    ----------------------------
                    
                    Choose from the following options:
                    
                    1. Search book by title
                    2. List books in database
                    3. List authors in database
                    4. List authors alive in a specific year
                    5. List books by language
                    6. List books by author
                    0. Exit
                    
                    ----------------------------
                    
                    """;

        while (option != 0)
        {
            System.out.println(mainMenu);

            while (scanner.hasNext())
            {
                if (scanner.hasNextInt())
                {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    break;
                }
                else
                {
                    System.out.println(intExpected + backToMain);
                    scanner.nextLine();
                    System.out.println(mainMenu);
                }
            }

            switch (option)
            {
                case 1:
                    getBookByTitle();
                    break;
                case 2:
                    getAllBooks();
                    break;
                case 3:
                    getAllAuthors();
                    break;
                case 4:
                    getAuthorsByYear();
                    break;
                case 5:
                    getBooksByLanguage();
                    break;
                case 6:
                    getBooksByAuthor();
                    break;
                case 0:
                    System.out.println("\nExiting application...\n");
                    break;
                default:
                    System.out.println(wrongOpt + backToMain);
                    break;
            }

            sleep(3000);
        }

        // Close the scanner. Once out of the main loop,
        // the scanner is no longer used.
        scanner.close();
    }

    private void getBookByTitle()
    {
        String gutendexBaseSearchUrl = "https://gutendex.com/books/?search=";

        System.out.println("\nEnter book title:\n");

        // Replace space with '+' in title string entered by user
        String title = scanner.nextLine().replace(" ", "+");

        // If the user didn't enter anything, exit immediately
        if (title.isEmpty())
        {
            System.out.println("\nNo book title was entered. " + backToMain);
            return;
        }

        // Get books from database
        // There may be more than one book with the same partial title
        // provided by the user, if implemented
        List<Book> books = authorRepo.findBooksByTitleContainsIgnoreCase(title);

        // Initialize variables to be used if book is not found in database
        Author author = null;
        Book book = null;
        RBook rBook = null;
        List<RBook> rBooks = null;
        RBookList rBookList = null;

        // If book is not found in database, do the following...
        if (books == null || books.isEmpty())
        {
            // Get the list of books from the Gutendex API
            String bookListJson = APIConsumer.getData(gutendexBaseSearchUrl + title);

            // Convert the JSON data from the Gutendex API to an actual Java object list
            rBookList = Converter.convertData(bookListJson, RBookList.class);

            // Filter the books with no authors and languages (mainly authors)
            // Gutendex sometimes returns an empty authors list []
            rBooks =  rBookList.results().stream()
                               .filter(b -> ! b.languages().isEmpty() && ! b.authors().isEmpty()).toList();

            // If book(s) was found, do the following:
            if (!rBooks.isEmpty())
            {
//             // Let the user know the book wasn't found in the database.
                System.out.println("\nBook containing '" + title + "' was not found in the database.\n");

                // Show the user the books found in the Gutendex Library, if more than one is found
                if (rBooks.size() > 1)
                {
                    int option = -1;

                    System.out.println("\nThe following books were found in the Gutendex Library:\n");

                    System.out.println("\nSelect the book you'd like to save in the database:\n");

                    for (int i = 0; i < rBooks.size(); i++)
                        System.out.printf("%d. %s, %s, %s , %d%n",
                                          i + 1,
                                          rBooks.get(i).title(),
                                          rBooks.get(i).authors().getFirst(),
                                          rBooks.get(i).languages().getFirst(),
                                          rBooks.get(i).downloadCount()
                                         );

                    System.out.println();

                    if (scanner.hasNextInt())
                    {
                        option = scanner.nextInt();
                        scanner.nextLine();
                    }
                    else
                    {
                        System.out.println(wrongOpt + backToMain);
                        return;
                    }

                    if (option > 0 && option <= rBooks.size())
                    {
                        rBook = rBooks.get(option - 1);
                    }
                }
                else
                {
                    rBook = rBooks.getFirst();
                }

                // Instantiate a new book because book was not found in database
                book = new Book(
                        rBook.id(),
                        rBook.title(),
                        rBook.languages(),
                        rBook.downloadCount());

                // Get the first RAuthor from the book
                RAuthor firstRAuthor = rBook.authors().getFirst();

                // Change the lastname, firstname name format to firstname lastname
                // because that's how it is saved in the database
                String authorName = "";

                if (firstRAuthor.name().contains(", "))
                {
                    List<String> names = Arrays.stream(firstRAuthor.name().split(", ")).toList();
                    authorName = names.getLast() + " " + names.getFirst();
                }
                else
                {
                    authorName = firstRAuthor.name();
                }

                // Get the author from the database
                author = authorRepo.findByNameIgnoreCase(authorName);

                // If the author is not found in the database, do the following...
                if (author == null)
                {
                    // Instantiate a new author
                    author = new Author(firstRAuthor.name(), firstRAuthor.birthYear(), firstRAuthor.deathYear());
                }

                // Add the book to the authors book list
                author.addBook(book);
                // Set the author in each book
                author.setBooks();
            }
            else
            {
                System.out.println("\nBook not found! " + backToMain);
                return;
            }
        }
        else
        {
            books.forEach(System.out::println);
            return;
        }

        // This is supposed to run ONLY if the book was not found in the database
        // And if a new book was successfully instantiated
        // And an author was found in the database
        // Or an author was successfully instantiated
        authorRepo.save(author);
        System.out.println(book);
    }

    private void getAllBooks()
    {
        List<Book> allBooks = authorRepo.findAllBooks();

        if (allBooks.isEmpty())
        {
            System.out.println("\nNo books found in the database! " +  backToMain);
            return;
        }

        allBooks.forEach(System.out::println);
    }

    private void getAllAuthors()
    {
        List<Author> allAuthors = authorRepo.findAllAuthors();

        if (allAuthors.isEmpty())
        {
            System.out.println(noAuthorsFound +  backToMain);
            return;
        }

        allAuthors.forEach(System.out::println);
    }

    private void getAuthorsByYear()
    {
        int year = 0;

        System.out.println("\nEnter the year when the author was still alive:\n");

        if (scanner.hasNextInt())
        {
            year = scanner.nextInt();
            scanner.nextLine();
        }
        else
        {
            System.out.println(wrongOpt + backToMain);
            return;
        }

        List<Author> allAuthors = authorRepo.findAuthorsByYear(year);

        if (allAuthors.isEmpty())
        {
            System.out.println(noAuthorsFound +  backToMain);
            return;
        }

        allAuthors.forEach(System.out::println);;
    }

    private void getBooksByLanguage()
    {
        // Get all languages from the database
        List<String> languages = authorRepo.findLanguages();

        // If no languages found, exit
        if (languages.isEmpty())
        {
            System.out.println("\nNo languages found in the database! " +  backToMain);
            return;
        }

        System.out.println("\nEnter the book language you'd like to search:\n");

        // Show languages menu to user
        for (int i = 0; i < languages.size(); i++)
        {
            System.out.println(i + 1 + ". " + languages.get(i).toUpperCase() +
                    " (" + Language.getLanguage(languages.get(i)) + ")");
        }

        System.out.println();

        // Get language option from user
        int option = -1;

        if (scanner.hasNextInt())
        {
             option = scanner.nextInt();

            // Check if option is valid
            // If yes, get the books from the database
            // If not, let the user know and go back to the main menu
            if (option >= 1 && option <= languages.size())
            {
                // Get the books from the database by the language specified
                authorRepo.findBooksByLanguage(languages.get(option - 1)).forEach(System.out::println);
            }
            else
            {
                System.out.println(wrongOpt + backToMain);
            }
        }
        else
        {
            scanner.nextLine();
            System.out.println(intExpected +  backToMain);
        }
    }

    private void getBooksByAuthor()
    {
        // Get all authors from the database
        List<String> authors = authorRepo.findAuthors();

        // If no authors are found, exit
        if (authors.isEmpty())
        {
            System.out.println(noAuthorsFound + backToMain);
            return;
        }

        // Prompt user for author from the list retrieved from the database
        System.out.println("\nEnter the name of the author whose books you'd like to search:\n");

        // Show authors menu to user
        for (int i = 0; i < authors.size(); i++)
        {
            System.out.println(i + 1 + ". " + authors.get(i));
        }

        System.out.println();

        int option = -1;

        // Get author option from user
        if (scanner.hasNextInt())
        {
            option = scanner.nextInt();

            // Check if option is valid
            // If yes, get the books from the database
            // If not, let the user know and go back to the main menu
            if (option >= 1 && option <= authors.size())
            {
                // Get the books from the database by the author specified
                authorRepo.findBooksByAuthor(authors.get(option - 1)).forEach(System.out::println);
            }
            else
            {
                System.out.println(wrongOpt + backToMain);
            }
        }
        else
        {
            scanner.nextLine();
            System.out.println(intExpected +  backToMain);
        }
    }
}
