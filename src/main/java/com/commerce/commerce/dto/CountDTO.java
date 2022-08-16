package com.commerce.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountDTO {
    private Long count;
    private String message;

    public CountDTO(Long count) {
        this.count = count;
    }
}
