package com.vc.library.dto;

import com.vc.library.LendStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shankar.s
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LendDto {
    private Long id;
    private String isbn;
    private String title;
    private int quantity;
    private LendStatus status;
}
