package com.vc.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author shankar.s
 */

@Data
@AllArgsConstructor
public class BookCountDto {
    private String title;
    private long quantity;
}
