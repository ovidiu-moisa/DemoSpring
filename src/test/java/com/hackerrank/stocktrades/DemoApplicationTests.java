package com.hackerrank.stocktrades;

import com.hackerrank.stocktrades.controller.StockTradeRestController;
import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import com.hackerrank.stocktrades.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private StockTradeRestController stockTradeRestController;

    @MockBean
    private StockTradeRepository stockTradeRepository;


    @Test
    public void testPostCallMokito(){
        StockTrade stockTrade = new StockTrade("sell",
                23,
                "AAC",
                12,
                133,
                1521522701000l);
        when(stockTradeRepository.save(any(StockTrade.class))).thenReturn(stockTrade);

        ResponseEntity<StockTrade> freshTrade = stockTradeRestController.newTrade(stockTrade);

        assert(freshTrade.getStatusCode().equals(HttpStatus.CREATED));
        assert(freshTrade.getBody().getType().equals("sell"));
        assert(freshTrade.getBody().getPrice().equals(133));
    }

    @Test
    public void testGetCallMokito() {
        StockTrade stockTrade = new StockTrade("sell",
                23,
                "AAC",
                12,
                133,
                1521522701000l);

        when(stockTradeRepository.findById(1)).thenReturn(Optional.of(stockTrade));

        ResponseEntity<StockTrade> foundTrades = stockTradeRestController.getTradeByID(1);

        assert(foundTrades.getStatusCode().equals(HttpStatus.OK));
        assert(foundTrades.getBody().getType().equals("sell"));
        assert(foundTrades.getBody().getPrice().equals(133));
    }




    }
