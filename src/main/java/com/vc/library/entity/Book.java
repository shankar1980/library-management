package com.vc.library.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author shankar.s
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOK")
@EntityListeners({AuditingEntityListener.class})
public class Book {
    @Id
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "PUBLISHER")
    private String publisher;
    @Column(name = "PAGES")
    private int pages;
    @Column(name = "QUANTITY")
    private int quantity;
    @Column(name = "AVAILABLE")
    private int available;
    @CreatedDate
    @Column(
            name = "CREATED_DATE",
            updatable = false
    )
    private LocalDateTime createdDate;
    @CreatedBy
    @Column(
            name = "CREATED_USERID",
            updatable = false
    )
    private Long createdBy = 1L;
    @LastModifiedDate
    @Column(
            name = "MODIFIED_DATE"
    )
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    @Column(
            name = "MODIFIED_USERID"
    )
    private Long lastModifiedBy = 1L;
}
