package com.example.cryptocurrencywatcher.service;

import com.example.cryptocurrencywatcher.entity.Coin;
import com.example.cryptocurrencywatcher.entity.User;
import com.example.cryptocurrencywatcher.parser.ParserJson;
import com.example.cryptocurrencywatcher.repository.CoinRepo;
import com.example.cryptocurrencywatcher.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.example.cryptocurrencywatcher.constants.Constants.*;

@Service
@Component
public class CoinService {
    private final UserRepo userRepo;
    private final CoinRepo coinRepo;
    private Coin objCoin = new Coin();

    private static final Logger logger = LoggerFactory.getLogger(CoinService.class);

    @Autowired
    public CoinService(UserRepo userRepo, CoinRepo coinLoreResponseRepo) {
        this.userRepo = userRepo;
        this.coinRepo = coinLoreResponseRepo;
    }

    public Coin save(Coin coinLoreResponse) {
        return coinRepo.save(coinLoreResponse);
    }

    public Optional<Coin> findByIdLong(Long aLong) {
        return coinRepo.findById(aLong);
    }

    public Coin findByIdString(String string) {
        return coinRepo.findById(string);
    }

    public Iterable<Coin> findAll() {
        return coinRepo.findAll();
    }

    private void actionFindPercent() {
        List<User> users = userRepo.findAll();
        Coin newValue = new Coin();
        Collection<Coin> coins = (List) coinRepo.findAll();

        for (User user : users) {
            for (Coin coin2 : coins) {
                if (user.getCoinId().equals(coin2.getId())) {
                    newValue = coinRepo.findById(coin2.getId());
                }
            }
            if (user.getCoinId().equals(newValue.getId())) {
                double a1 = Double.parseDouble(user.getCoinPrice_usd());
                double a2 = Double.parseDouble(newValue.getPrice_usd());
                double result = (a2 / a1 * ONE_HUNDRED_PERCENT - ONE_HUNDRED_PERCENT);
                if (result > ONE_PERCENT) {
                    logger.warn(newValue.getId() + " " + user.getName() + " " + result);
                }
            }
        }
    }

    private List<Coin> createCoins() {
        ParserJson parserJson = new ParserJson();
        List<Coin> list = new ArrayList<>();

        objCoin = parserJson.parserJson(URL_90);
        list.add(coinRepo.save(objCoin));

        objCoin = parserJson.parserJson(URL_80);
        list.add(coinRepo.save(objCoin));

        objCoin = parserJson.parserJson(URL_48543);
        list.add(coinRepo.save(objCoin));
        return list;
    }

    private void updateCoins(Coin coin) {
        List<Coin> coins = (List) coinRepo.findAll();
        for (Coin item : coins) {
            if (coin.getId().equals(item.getId())) {
                objCoin = coinRepo.findById(item.getPk()).orElseThrow();
                objCoin.setId(coin.getId());
                objCoin.setSymbol(coin.getSymbol());
                objCoin.setPrice_usd(coin.getPrice_usd());
                coinRepo.save(objCoin);
            }
        }
    }

    @Scheduled(initialDelay = 3000, fixedDelayString = "${schedule.work}")
    public void runThread() {
        if (i == 0) {
            createCoins();
            ++i;
        }
        else if (i == 2) {
            ParserJson parserJson = new ParserJson();
            updateCoins(parserJson.parserJson(URL_90));
            updateCoins(parserJson.parserJson(URL_80));
            updateCoins(parserJson.parserJson(URL_48543));

            actionFindPercent();
        }
    }

    public void getUserCoin(List<Coin> coins) {
        List<User> users = userRepo.findAll();
        for (User user : users) {
            for (Coin coin : coins) {
                User u = userRepo.findByName(user.getName());
                u.setCoinId(coin.getId());
                u.setCoinSymbol(coin.getSymbol());
                u.setCoinPrice_usd(coin.getPrice_usd());
                userRepo.save(u);
                logger.info(user.getName() + " " + coin.getSymbol() + " " + coin.getPrice_usd());
            }
        }
    }
}
