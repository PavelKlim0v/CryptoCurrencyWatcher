package com.example.cryptocurrencywatcher.controller;

import com.example.cryptocurrencywatcher.entity.Coin;
import com.example.cryptocurrencywatcher.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private CoinService coinLoreService;

    @GetMapping("/")
    public String getList(Model model) {
        List<Coin> list = (List) coinLoreService.findAll();
        model.addAttribute("list", list);
        return "start-page";
    }

    @PostMapping("/")
    public String actionList(Model model) {
        return "redirect:/start-page";
    }
}
