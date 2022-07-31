package com.vc.library;

import com.vc.library.entity.Book;
import com.vc.library.entity.User;
import com.vc.library.service.BookService;
import com.vc.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class LibrarymgmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarymgmtApplication.class, args);
    }

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initDB() {

        userService.createUser(mapUser("admin", "asdf123", "admin@gmail.com", "admin"));
        userService.createUser(mapUser("borrower1", "asdf123", "borrower1@gmail.com", "borrower"));

        bookService.addBook(mapBook("9780545162074", "Harry Potter Paperback", "J. K. Rowling", "Arthur A. Levine Books", 100, 5,5));
        bookService.addBook(mapBook("9781982136468", "The Only Good Indians: A Novel", "Jones, Stephen Graham", "Gallery / Saga Press", 50, 10,10));
        bookService.addBook(mapBook("9781848310117", "Introducing Buddha: A Graphic Guide", "Borin Van Loon, Jane Hope", "Icon Books", 200, 15,15));
        bookService.addBook(mapBook("9781785783135", "Introducing Mind and Brain", "Angus Gellatly, Oscar Zarate", "Icon Books", 500, 50,50));
        bookService.addBook(mapBook("9788126523504", "Introducing Mind and Brain", "Dr.J.Ravichandran", "Wiley", 500, 25,25));
        bookService.addBook(mapBook("9788184820201", "Mahabharata", "Dr.J.Ravichandran", "Amar Chitra Katha Pvt Ltd", 32, 25,0));
    }

    private User mapUser(String name, String password, String email, String role) {
        return User.builder()
                .name(name)
                .password(password)
                .email(email)
                .role(role)
                .build();
    }

    private Book mapBook(String isbn, String title, String author, String publisher, int page, int quantity, int available) {
        return Book.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .publisher(publisher)
                .pages(page)
                .quantity(quantity)
                .available(available)
                .build();
    }

}
