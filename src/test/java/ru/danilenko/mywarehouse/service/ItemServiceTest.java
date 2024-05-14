package ru.danilenko.mywarehouse.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.danilenko.mywarehouse.dto.ItemDto;
import ru.danilenko.mywarehouse.entity.Item;
import ru.danilenko.mywarehouse.repository.ItemRepository;
import ru.danilenko.mywarehouse.service.impl.ItemServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("Item service test")
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;


    @InjectMocks
    private ItemServiceImpl itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("get all items test")
    @Test
    public void getAllItemsTest() {
        List<Item> list = new ArrayList<>();
        when(itemRepository.findAll()).thenReturn(list);
        assertThat(itemService.getAllItems()).hasSize(0);

    }

    @DisplayName("get item by stockNum test")
    @Test
    public void getItemByStockNumberTest() {
        Item item = Item.builder().name("test").build();
        when(itemRepository.findByStockNumber(anyString())).thenReturn(Optional.ofNullable(item));
        assertThat(itemService.getItemByStockNumber("111111").getName()).isEqualTo("test");

    }

    @DisplayName("save item test")
    @Test
    public void saveItemTest() {
        ItemDto itemDto = ItemDto.builder().build();
        when(itemRepository.findByStockNumber(anyString())).thenReturn(Optional.empty());
        when(itemRepository.save(any(Item.class))).thenReturn(true);
        assertThat(itemService.saveItem(itemDto)).isTrue();
    }

    @DisplayName("update item test")
    @Test
    public void updateItemTest() {
        String stockNum = "1";
        Item itemToUpdate = Item.builder().stockNum(stockNum).name("test").build();
        ItemDto itemDto = ItemDto.builder().stockNum(stockNum).name("test").build();
        when(itemRepository.findByStockNumber(anyString())).thenReturn(Optional.ofNullable(itemToUpdate));
        when(itemRepository.delete(itemToUpdate)).thenReturn(true);
        when(itemRepository.save(any(Item.class))).thenReturn(true);
        assertThat(itemService.updateItem(stockNum, itemDto)).isTrue();
    }

    @DisplayName("delete item test")
    @Test
    public void deleteItemTest() {
        Item item = Item.builder().name("test").build();
        when(itemRepository.findByStockNumber(anyString())).thenReturn(Optional.ofNullable(item));
        assertThat(itemService.deleteItem("111111")).isTrue();
    }
}
