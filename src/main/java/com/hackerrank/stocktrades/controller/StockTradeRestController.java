package com.hackerrank.stocktrades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import com.hackerrank.stocktrades.service.StockService;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RequestMapping("/trades")
@RestController
public class StockTradeRestController {

  private final StockService stockService;

  @Autowired
  public StockTradeRestController (StockService stockService){
    this.stockService = stockService;
  }
  @PostMapping
  public ResponseEntity<StockTrade> newTrade(@Valid @RequestBody StockTrade stocktrade){
    return stockService.newTrade(stocktrade);}

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> trow400onvalidationFail(BindException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed");
  }

  @GetMapping
  public ResponseEntity<List<StockTrade>> getAllTrades(
          @RequestParam(required = false) String type,
          @RequestParam(required = false) Integer userId){
    List<StockTrade> alltrades =  stockService.getAllTrades();
    List<StockTrade> filteredtrades = new ArrayList<>();
    for(StockTrade trade : alltrades){
      if((type ==null || trade.getType().equals(type))&&
              (userId ==null || trade.getUserId().equals(userId))){
        filteredtrades.add(trade);
      }
    }
    return ResponseEntity.ok(filteredtrades);
  }
  @GetMapping("/{id}")
  public ResponseEntity<StockTrade> getTradeByID(@PathVariable Integer id){
    return stockService.getTradeByID(id);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTrade(@PathVariable Integer id){
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
  }
  @PutMapping("/{id}")
  public ResponseEntity<Void> putTrade(@PathVariable Integer id){
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
  }
  @PatchMapping("/{id}")
  public ResponseEntity<Void> patchTrade(@PathVariable Integer id){
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
  }

}