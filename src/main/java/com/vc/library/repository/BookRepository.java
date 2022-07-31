package com.vc.library.repository;

import com.vc.library.dto.BookCountDto;
import com.vc.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shankar.s
 */

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    Page<Book> findAllByTitleLike(String title, PageRequest pageRequest);

    @Query("select new com.vc.library.dto.BookCountDto(b.title, sum(b.quantity)) from Book b group by b.title")
    List<BookCountDto> countBooks();
}
