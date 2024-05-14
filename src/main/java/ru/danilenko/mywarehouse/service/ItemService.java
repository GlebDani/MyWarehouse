package ru.danilenko.mywarehouse.service;

import ru.danilenko.mywarehouse.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAllItems();

    ItemDto getItemByStockNumber(String stockNum);

    boolean saveItem(ItemDto itemDto);

    boolean updateItem(String stockNum, ItemDto itemDto);

    boolean deleteItem(String stockNum);
}
