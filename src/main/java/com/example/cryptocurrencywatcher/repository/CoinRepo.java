package com.example.cryptocurrencywatcher.repository;

import com.example.cryptocurrencywatcher.entity.Coin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepo extends CrudRepository<Coin, Long> {
    Coin findById(String id);
}
