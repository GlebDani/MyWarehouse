package ru.danilenko.mywarehouse.repository.impl;

import org.springframework.stereotype.Repository;
import ru.danilenko.mywarehouse.entity.Item;
import ru.danilenko.mywarehouse.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final List<Item> list = new ArrayList<>() {
    };

    {
        list.add(new Item(1, "111111", "Iphone", "15", 100000L, true));
    }

    private static int id = 2;

    @Override
    public List<Item> findAll() {
        return list;
    }

    @Override
    public Optional<Item> findByStockNumber(String stockNum) {
        return list.stream().filter(item -> item.getStockNum().equals(stockNum)).findAny();
    }

    @Override
    public boolean save(Item item) {
        item.setId(id++);
        list.add(item);
        return true;
    }

    @Override
    public boolean delete(Item item) {
        list.remove(item);
        return true;
    }
}
