package com.vc.library.controller;

import com.vc.library.dto.BookCountDto;
import com.vc.library.entity.Book;
import com.vc.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shankar.s
 */

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(path = "/getBooks", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> getBooks(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return bookService.getBooks(page, size);
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Book> search(@RequestParam(name = "title") String title, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return bookService.search(title, page, size);
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping(path = "/{isbn}")
    public void deleteBook(@PathVariable(name = "isbn") String isbn) {
        bookService.delete(isbn);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookCountDto> countNoofBooks() {
        return bookService.countBooks();
    }

}
