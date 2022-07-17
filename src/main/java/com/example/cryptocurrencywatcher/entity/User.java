package com.example.cryptocurrencywatcher.entity;

import javax.persistence.*;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "userpic")
    private String userpic;
    @Column(name = "email")
    private String email;
    @Column(name = "locale")
    private String locale;

    private String coinId;
    private String coinSymbol;
    private String coinPrice_usd;

    public User() {
    }

    public User(String name, String userpic, String email, String locale) {
        this.name = name;
        this.userpic = userpic;
        this.email = email;
        this.locale = locale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getCoinSymbol() {
        return coinSymbol;
    }

    public void setCoinSymbol(String coinSymbol) {
        this.coinSymbol = coinSymbol;
    }

    public String getCoinPrice_usd() {
        return coinPrice_usd;
    }

    public void setCoinPrice_usd(String coinPrice_usd) {
        this.coinPrice_usd = coinPrice_usd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userpic='" + userpic + '\'' +
                ", email='" + email + '\'' +
                ", locale='" + locale + '\'' +
                ", coinId='" + coinId + '\'' +
                ", coinSymbol='" + coinSymbol + '\'' +
                ", coinPrice_usd='" + coinPrice_usd + '\'' +
                '}';
    }
}
