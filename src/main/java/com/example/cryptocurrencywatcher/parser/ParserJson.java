package com.example.cryptocurrencywatcher.parser;

import com.example.cryptocurrencywatcher.entity.Coin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static com.example.cryptocurrencywatcher.constants.Constants.*;

public class ParserJson {
    private static Coin coinObj;
    private static String string;

    public Coin parserJson(String id) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        coinObj = new Coin();
        try {
            string = URL + id;
            ResponseEntity<String> entity = restTemplate.getForEntity(string, String.class);
            string = entity.getBody();
            List<Map<String, String>> map = objectMapper.readValue(string, new TypeReference<List<Map<String, String>>>() {});

            Coin finalObjCoin = coinObj;
            map.forEach(m -> {
                m.forEach((key, value) -> {
                    switch (key) {
                        case ID:
                            finalObjCoin.setId(value);
                            break;
                        case SYMBOL:
                            finalObjCoin.setSymbol(value);
                            break;
                        case PRICE_USD:
                            finalObjCoin.setPrice_usd(value);
                            break;
                        default:
                            break;
                    }
                });
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Coin(coinObj.getId(), coinObj.getSymbol(), coinObj.getPrice_usd());
    }
}
