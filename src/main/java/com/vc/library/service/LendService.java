package com.vc.library.service;

import com.vc.library.LendStatus;
import com.vc.library.dto.LendDto;
import com.vc.library.entity.Book;
import com.vc.library.entity.Lend;
import com.vc.library.repository.BookRepository;
import com.vc.library.repository.LendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author shankar.s
 */

@Service
public class LendService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LendRepository lendRepository;

    public List<LendDto> getBookingDetails(LendStatus[] status, Long createdBy) {
        List<Lend> lendList = lendRepository.findAllByCreatedByAndStatusIn(createdBy, Arrays.asList(status));
        List<LendDto> lendDtoList = Optional.ofNullable(lendList).orElseGet(Collections::emptyList).stream()
                .map(l -> LendDto.builder()
                        .id(l.getId())
                        .isbn(l.getBook().getIsbn())
                        .title(l.getBook().getTitle())
                        .quantity(l.getQuantity())
                        .status(l.getStatus())
                        .build()
                )
                .collect(Collectors.toList());
        return lendDtoList;
    }

    @Transactional
    public void lendItem(LendDto lendDto) {
        Optional<Book> optionalBook = bookRepository.findById(lendDto.getIsbn());
        if (optionalBook.isPresent() && optionalBook.get().getAvailable() > 0) {
            Book book = optionalBook.get();
            book.setAvailable(book.getAvailable() - 1);
            Lend lend = Lend.builder()
                    .book(book)
                    .quantity(lendDto.getQuantity())
                    .status(LendStatus.PENDING)
                    .build();
            bookRepository.save(book);
            lendRepository.save(lend);
        } else {
            throw new RuntimeException("Book not available");
        }
    }

    public void approveLendItem(Long id) {
        Optional<Lend> optionaLend = lendRepository.findById(id);
        if (optionaLend.isPresent()) {
            Lend lend = optionaLend.get();
            lend.setStatus(LendStatus.BORROWED);
            lendRepository.save(lend);
        } else {
            throw new RuntimeException("Invalid lend item");
        }
    }

    public void returnLendItem(Long id) {
        lendRequest(id, LendStatus.RETURNED);
    }

    public void cancelLendItem(Long id) {
        lendRequest(id, LendStatus.CANCELLED);
    }

    private void lendRequest(Long id, LendStatus lendStatus) {
        Optional<Lend> optionaLend = lendRepository.findById(id);
        if (optionaLend.isPresent()) {
            Lend lend = optionaLend.get();
            Book book = bookRepository.findById(lend.getBook().getIsbn()).get();
            book.setAvailable(book.getAvailable() + 1);
            lend.setStatus(lendStatus);
            bookRepository.save(book);
            lendRepository.save(lend);
        } else {
            throw new RuntimeException("Invalid lend item");
        }
    }
}
