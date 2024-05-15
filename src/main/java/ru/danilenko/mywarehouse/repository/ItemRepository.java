package ru.danilenko.mywarehouse.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilenko.mywarehouse.entity.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByStockNum(String stockNum);
    List<Item> findByNameStartingWith(String title, Pageable pageable);
    List<Item> findByPrice(Long price, Pageable pageable);
    List<Item> findByPriceIsGreaterThanEqual(Long price, Pageable pageable);
    List<Item> findByPriceIsLessThanEqual(Long price, Pageable pageable);
    List<Item> findByInStock(Boolean inStock, Pageable pageable);
}
