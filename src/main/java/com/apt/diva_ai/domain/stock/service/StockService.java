package com.apt.diva_ai.domain.stock.service;

import com.apt.diva_ai.domain.stock.entity.Stock;

public interface StockService {

    Stock findStock(String stockCode);
}
