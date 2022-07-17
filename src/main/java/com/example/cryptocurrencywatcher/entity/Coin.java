package com.example.cryptocurrencywatcher.entity;

import javax.persistence.*;

@Entity
@Table(name = "coin")
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long pk;
    @Column(name = "id_coin")
    private String id;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "price_usd")
    private String price_usd;

    public Coin() {
    }

    public Coin(String id, String symbol, String price_usd) {
        this.id = id;
        this.symbol = symbol;
        this.price_usd = price_usd;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    @Override
    public String toString() {
        return "{id='" + id + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price_usd='" + price_usd + '\'' +
                '}';
    }
}
