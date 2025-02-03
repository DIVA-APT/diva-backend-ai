package com.apt.diva_ai.domain.stock.repository;

import com.apt.diva_ai.domain.stock.entity.Stock;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByStockCode(String stockCode);
}
