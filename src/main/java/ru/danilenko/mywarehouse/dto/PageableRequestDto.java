package ru.danilenko.mywarehouse.dto;

import lombok.Data;

@Data
public class PageableRequestDto{
    private Integer pageNum;
    private Integer bookPerPage;
    private String sortBy;
    private String itemName;
}
