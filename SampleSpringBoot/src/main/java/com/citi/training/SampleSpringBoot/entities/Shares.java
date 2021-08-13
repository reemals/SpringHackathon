package com.citi.training.SampleSpringBoot.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="shares")
public class Shares implements Serializable {

    @Id
    @Column(name="Symbol")
    private String symbol;

    @Column(name="Closing Price")
    private Double closingPrice;
    @Column(name="Volume")
    private Double volume;
    @Column(name="Purchase Price")
    private Double purchasePrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}

