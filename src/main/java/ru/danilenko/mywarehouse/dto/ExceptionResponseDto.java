package ru.danilenko.mywarehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponseDto {

    private String message;

    private int httpStatus;
}
