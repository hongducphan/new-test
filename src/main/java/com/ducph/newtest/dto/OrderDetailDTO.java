package com.ducph.newtest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderDetailDTO {

    @Min(0)
    private int id;

    @NotBlank
    private String name;
}
