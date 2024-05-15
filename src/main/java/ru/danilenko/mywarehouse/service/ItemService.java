package ru.danilenko.mywarehouse.service;


import ru.danilenko.mywarehouse.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getSearchedItems(String title,Long price, Boolean priceIsBigger, Boolean inStock, Integer pageNum, Integer itemsPerPage, String sortBy);
    List<ItemDto> getAllItems();

    ItemDto getItemByStockNumber(String stockNum);

    boolean saveItem(ItemDto itemDto);

    boolean updateItem(String stockNum, ItemDto itemDto);

    boolean deleteItem(String stockNum);
}
