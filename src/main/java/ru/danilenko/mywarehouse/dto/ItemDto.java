package ru.danilenko.mywarehouse.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDto {

    @Pattern(regexp = "\\d{6}", message = "Артикул состоит из 6 цифр: 123456")
    private String stockNum;

    @Size(max = 255, message = "Название товара не должно превышать 255 символов")
    @NotBlank(message = "Название товара  не должно быть пустым")
    private String name;

    @Size(max = 4096, message = "Описание товара не должно превышать 4096 символов")
    private String description;

    @Min(value = 0L, message = "Цена товара не может быть меньше 0")
    @Builder.Default()
    private Long price = 0L;

    @Builder.Default()
    private Boolean inStock = false;
}
