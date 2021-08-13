package com.citi.training.SampleSpringBoot.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="shares")
public class Shares implements Serializable {

    @Id
    @Column(name="Symbol")
    private String symbol;

    @Column(name="Price")
    private Double Price;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPurchasePrice() {
        return Price;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.Price = purchasePrice;
    }
}

