package com.citi.training.SampleSpringBoot.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="shares")
public class Shares implements Serializable {

    @Id
    @Column(name="Symbol")
    private String symbol;

    @Column(name="Purchased_price")
    private Double purchasedPrice;
    @Column(name="Volume")
    private int volume;
    @Column(name="Current_price")
    private Double currentPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(Double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}

