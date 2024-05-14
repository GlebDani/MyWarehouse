package ru.danilenko.mywarehouse.mapper;

import org.mapstruct.Mapper;
import ru.danilenko.mywarehouse.dto.ItemDto;
import ru.danilenko.mywarehouse.entity.Item;

@Mapper
public interface ItemMapper {

    ItemDto convertToDto(Item item);

    Item convertFromDto(ItemDto itemDto);
}
