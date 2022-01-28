package com.example.controller;

import com.example.entity.Crypto;
import com.example.entity.User;
import com.example.service.CryptoService;
import com.example.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
public class SchedulerController {

    Logger logger = LoggerFactory.getLogger(SchedulerController.class);

    @Autowired
    CryptoService cryptoService;

    @Autowired
    UserService userService;

    @PostConstruct
    public void init() {
        Thread run = new Thread(() -> {
            while (true) {
                try {
                    List<Crypto> cryptos = cryptoService.getAll();
                    for (Crypto crypto : cryptos) {
                        try {
                            URL url = new URL("https://api.coinlore.net/api/ticker/?id=" + crypto.getId().toString());
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            con.setRequestMethod("GET");
                            con.setRequestProperty("Content-Type", "application/json");
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(con.getInputStream()));
                            JSONArray array = new JSONArray(in.readLine());
                            JSONObject object = array.getJSONObject(0);
                            Long id = Long.valueOf((String) object.get("id"));
                            Double price = Double.valueOf((String) object.get("price_usd"));
                            logger.info("Price for " + id + " = " + price);
                            cryptoService.updateCryptoPrice(price, id);
                            in.close();
                        } catch (Exception ignored) {}
                    }
                } catch (Exception ignored) {}
                List<User> users = userService.getAllUsers();
                for (User user: users) {
                    Crypto crypto = cryptoService.getCryptoById(user.getCryptoId());
                    if (crypto.getPrice() / user.getPrice() <= 0.99 || crypto.getPrice() / user.getPrice() >= 1.01) {
                        logger.warn("Price for crypto to user " + user.getName() + " changed more than 1%. Old price:" + user.getPrice() + " New price:" + crypto.getPrice());
                    }
                }
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                }
            }

        }
        );
        run.start();
    }

}
