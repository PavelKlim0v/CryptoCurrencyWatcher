package com.example.cryptocurrencywatcher.controller;

import com.example.cryptocurrencywatcher.entity.Coin;
import com.example.cryptocurrencywatcher.entity.User;
import com.example.cryptocurrencywatcher.parser.ParserJson;
import com.example.cryptocurrencywatcher.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import static com.example.cryptocurrencywatcher.constants.Constants.i;

@Controller
public class CoinController {
    @Autowired
    private CoinService coinService;

    @GetMapping("/coin/{id}")
    public String getInfoByIdCoin(@PathVariable(value = "id") String id, Model model, @AuthenticationPrincipal User user) {
        Coin coin = coinService.findByIdString(id);
        List<Coin> list = new ArrayList<>();
        list.add(coin);
        if (i == 1) {
            coinService.getUserCoin(list);
            ++i;
        }
        model.addAttribute("list", list);
        return "coin-page";
    }

    @PostMapping("/coin/{id}")
    public String setInfoByIdCoin(@PathVariable(value = "id") String id, Model model, @AuthenticationPrincipal User user) {
        List<Coin> list = new ArrayList<>();
        ParserJson parserJson = new ParserJson();
        Coin newCoinValue = new Coin();

        Coin coin = coinService.findByIdLong(newCoinValue.getPk()).orElseThrow();
        if (coin.getId().equals(id)) {
            coin = parserJson.parserJson(id);
            list.add(coin);
            coinService.save(coin);
        }
        model.addAttribute("list", list);
        return "coin-page";
    }
}
