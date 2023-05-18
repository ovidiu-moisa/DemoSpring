package com.hackerrank.stocktrades.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
public class StockTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Pattern(regexp = "^(buy|sell)$")
    private String type;
    private Integer userId;
    private String symbol;
    @Min(value = 1)
    @Max(value = 100)
    private Integer shares;
    private Integer price;
    private Long timestamp;

    public StockTrade(String type, Integer userId, String symbol, Integer shares, Integer price, Long timestamp) {
        this.type = type;
        this.userId = userId;
        this.symbol =  symbol;
        this.shares = shares;
        this.price =  price;
        this.timestamp = timestamp;
    }

    public StockTrade(){
        //default constructor ???
    };

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getShares() {
        return shares;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getUserId() {
        return userId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
