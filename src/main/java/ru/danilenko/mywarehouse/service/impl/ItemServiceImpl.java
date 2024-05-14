package ru.danilenko.mywarehouse.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danilenko.mywarehouse.dto.ItemDto;
import ru.danilenko.mywarehouse.entity.Item;
import ru.danilenko.mywarehouse.exception.ItemException;
import ru.danilenko.mywarehouse.mapper.ItemMapper;
import ru.danilenko.mywarehouse.repository.ItemRepository;
import ru.danilenko.mywarehouse.service.ItemService;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream().map(itemMapper::convertToDto).toList();
    }

    @Override
    public ItemDto getItemByStockNumber(String stockNum) {
        Item item = getExistedItem(stockNum);
        return itemMapper.convertToDto(item);
    }

    @Override
    public boolean saveItem(ItemDto itemDto) {
        itemRepository.findByStockNumber(itemDto.getStockNum()).ifPresent(x -> {throw new ItemException("Товар с таким артикулем уже присутсвует");});
        Item item = itemMapper.convertFromDto(itemDto);
        return itemRepository.save(item);
    }

    @Override
    public boolean updateItem(String stockNum, ItemDto itemDto) {
        Item itemToUpdate = getExistedItem(stockNum);
        if(!stockNum.equals(itemDto.getStockNum())){
            if(itemRepository.findByStockNumber(itemDto.getStockNum()).isPresent()){
                throw new ItemException("Товар с таким артикулем уже присутсвует");
            }
        }
        Item updatedItem = itemMapper.convertFromDto(itemDto);
        updatedItem.setId(itemToUpdate.getId());
        itemRepository.delete(itemToUpdate);
        return itemRepository.save(updatedItem);
    }

    @Override
    public boolean deleteItem(String stockNum) {
        Item item = getExistedItem(stockNum);
        return itemRepository.delete(item);
    }

    private Item getExistedItem(String stockNum){
        return itemRepository.findByStockNumber(stockNum).orElseThrow(()->new ItemException("Товар с таким артикулем не найден"));
    }
}
