package ru.danilenko.mywarehouse.repository;

import ru.danilenko.mywarehouse.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    List<Item> findAll();

    Optional<Item> findByStockNumber(String stockNum);

    boolean save(Item item);

    boolean delete(Item item);

}
