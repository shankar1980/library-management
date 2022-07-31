package com.vc.library.entity;

import com.vc.library.LendStatus;
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
@Table(name = "LEND_DETAILS")
@EntityListeners({AuditingEntityListener.class})
public class Lend {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ISBN")
    private Book book;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "status")
    private LendStatus status;

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
