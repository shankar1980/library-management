package com.vc.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_LI")
@EntityListeners({AuditingEntityListener.class})
public class User {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", length = 50)
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL", length = 100)
    private String email;
    @Column(name = "ROLE", length = 100)
    private String role;
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
