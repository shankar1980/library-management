package com.vc.library.service;

import com.vc.library.dto.BookCountDto;
import com.vc.library.entity.Book;
import com.vc.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author shankar.s
 */

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getBooks(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Book> search(String title, int page, int size) {
        return bookRepository.findAllByTitleLike("%" + title + "%", PageRequest.of(page, size));
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void delete(String isbn) {
        Optional<Book> book = bookRepository.findById(isbn);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
        } else {
            log.info("Book not found for the ISBN: {}", isbn);
        }
    }

    public List<BookCountDto> countBooks() {
        List<BookCountDto> obj = bookRepository.countBooks();
        return obj;
    }
}
