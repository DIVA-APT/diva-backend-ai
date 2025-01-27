package com.apt.diva_ai.domain.stock.service;

import com.apt.diva_ai.domain.stock.entity.Stock;
import com.apt.diva_ai.domain.stock.repository.StockRepository;
import com.apt.diva_ai.global.exception.CustomException;
import com.apt.diva_ai.global.exception.errorCode.StockErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Stock findStock(String stockCode) {
        return stockRepository.findByStockCode(stockCode).orElseThrow(() -> new CustomException(
            StockErrorCode.STOCK_CODE_NOT_FOUND));
    }
}
