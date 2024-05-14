package ru.danilenko.mywarehouse.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.danilenko.mywarehouse.dto.BaseResponseDto;
import ru.danilenko.mywarehouse.dto.ItemDto;
import ru.danilenko.mywarehouse.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("")
    public ResponseEntity<List<ItemDto>> getItems() {
        List<ItemDto> list = itemService.getAllItems();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{stockNumber}")
    public ResponseEntity<ItemDto> getItem(@PathVariable("stockNumber") String stockNumber) {
        ItemDto itemDto = itemService.getItemByStockNumber(stockNumber);
        return ResponseEntity.ok(itemDto);
    }

    @PostMapping("")
    public ResponseEntity<BaseResponseDto> addItem(@Valid @RequestBody ItemDto itemDto) {
        itemService.saveItem(itemDto);
        return ResponseEntity.ok(new BaseResponseDto("Товар добавлен"));
    }

    @PutMapping("/{stockNumber}")
    public ResponseEntity<BaseResponseDto> updateItem(@PathVariable("stockNumber") String stockNumber, @Valid @RequestBody ItemDto itemDto) {
        itemService.updateItem(stockNumber, itemDto);
        return ResponseEntity.ok(new BaseResponseDto("Товар обновлен"));
    }

    @DeleteMapping("/{stockNumber}")
    public ResponseEntity<BaseResponseDto> deleteItem(@PathVariable("stockNumber") String stockNumber) {
        itemService.deleteItem(stockNumber);
        return ResponseEntity.ok(new BaseResponseDto("Товар удален"));
    }
}
