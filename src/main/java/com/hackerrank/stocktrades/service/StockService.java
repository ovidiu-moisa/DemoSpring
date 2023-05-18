package com.hackerrank.stocktrades.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;


@Service
public class StockService{
    private final StockTradeRepository stocktraderepository;


    @Autowired
    public StockService(StockTradeRepository stocktraderepository){
        this.stocktraderepository = stocktraderepository;
    }
    public ResponseEntity<StockTrade> newTrade (StockTrade stocktrade){
        StockTrade addedtrade = stocktraderepository.save(stocktrade);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedtrade);
    }

    public List<StockTrade> getAllTrades( ){
        return stocktraderepository.findAll();
    }
    public ResponseEntity<StockTrade> getTradeByID (Integer id){
        Optional<StockTrade> optionalstocktrade = stocktraderepository.findById(id);
        if(optionalstocktrade.isPresent()){
            StockTrade stockTrade = optionalstocktrade.get();
            return ResponseEntity.ok(stockTrade);
        }
        return ResponseEntity.notFound().build();
    }


}