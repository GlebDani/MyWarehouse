package ru.danilenko.mywarehouse.service.impl;

import org.mapstruct.factory.Mappers;


import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danilenko.mywarehouse.dto.ItemDto;
import ru.danilenko.mywarehouse.entity.Item;
import ru.danilenko.mywarehouse.exception.ItemException;
import ru.danilenko.mywarehouse.mapper.ItemMapper;
import ru.danilenko.mywarehouse.repository.ItemRepository;
import ru.danilenko.mywarehouse.service.ItemService;

import java.util.List;

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
    public List<ItemDto> getSearchedItems(String title,Long price, Boolean priceIsBigger, Boolean inStock, Integer pageNum, Integer itemsPerPage, String sortBy) {
        if(price == null && inStock == null) {
            return itemRepository.findByNameStartingWith(title, PageRequest.of(pageNum, itemsPerPage, Sort.by(sortBy))).stream().map(itemMapper::convertToDto).toList();
        }
        else if(price != null){
            if(priceIsBigger == null){
                return itemRepository.findByPrice(price, PageRequest.of(pageNum, itemsPerPage, Sort.by(sortBy))).stream().map(itemMapper::convertToDto).toList();
            } else {
                if(priceIsBigger){
                    return itemRepository.findByPriceIsGreaterThanEqual(price, PageRequest.of(pageNum, itemsPerPage, Sort.by(sortBy))).stream().map(itemMapper::convertToDto).toList();
                } else {
                    return itemRepository.findByPriceIsLessThanEqual(price, PageRequest.of(pageNum, itemsPerPage, Sort.by(sortBy))).stream().map(itemMapper::convertToDto).toList();
                }
            }
        }
        else {
            return itemRepository.findByInStock(inStock, PageRequest.of(pageNum, itemsPerPage, Sort.by(sortBy))).stream().map(itemMapper::convertToDto).toList();
        }
    }

    @Override
    public ItemDto getItemByStockNumber(String stockNum) {
        Item item = getExistedItem(stockNum);
        return itemMapper.convertToDto(item);
    }

    @Override
    public boolean saveItem(ItemDto itemDto) {
        itemRepository.findByStockNum(itemDto.getStockNum()).ifPresent(x -> {throw new ItemException("Товар с таким артикулем уже присутсвует");});
        Item item = itemMapper.convertFromDto(itemDto);
        itemRepository.save(item);
        return true;
    }

    @Override
    @Transactional
    public boolean updateItem(String stockNum, ItemDto itemDto) {
        Item itemToUpdate = getExistedItem(stockNum);
        if(!stockNum.equals(itemDto.getStockNum())){
            if(itemRepository.findByStockNum(itemDto.getStockNum()).isPresent()){
                throw new ItemException("Товар с таким артикулем уже присутсвует");
            }
        }
        itemToUpdate.setName(itemDto.getName());
        itemToUpdate.setDescription(itemDto.getDescription());
        itemToUpdate.setStockNum(itemDto.getStockNum());
        itemToUpdate.setPrice(itemDto.getPrice());
        itemToUpdate.setInStock(itemDto.getInStock());
        itemRepository.save(itemToUpdate);
        return true;
    }

    @Override
    public boolean deleteItem(String stockNum) {
        Item item = getExistedItem(stockNum);
        itemRepository.delete(item);
        return true;
    }

    private Item getExistedItem(String stockNum){
        return itemRepository.findByStockNum(stockNum).orElseThrow(()->new ItemException("Товар с таким артикулем не найден"));
    }
}
