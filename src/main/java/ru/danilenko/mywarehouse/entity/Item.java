package ru.danilenko.mywarehouse.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Item {

    private Integer id;


    private String stockNum;


    private String name;


    private String description;

    @Builder.Default()
    private Long price = 0L;

    @Builder.Default()
    private Boolean inStock = false;

}
