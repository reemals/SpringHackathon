package com.citi.training.SampleSpringBoot.entities;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="shares")
public class Shares implements Serializable {

//    public Shares(String symbol, int volume) {
//        this.symbol = symbol;
//        this.volume = volume;
//    }
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="Symbol")
    private String symbol;

    @Column(name="transaction_price")
    private Double transaction_price;
    @Column(name="Volume")
    private int volume;
    @Column(name="transaction_type")
    private String transaction_type;

    @Column(name="transaction_date")
    private Date transaction_date;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getTransaction_price() {
        return transaction_price;
    }

    public void setTransaction_price(Double price) {
//        Stock stock = YahooFinance.get(symbol);
        this.transaction_price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

}

