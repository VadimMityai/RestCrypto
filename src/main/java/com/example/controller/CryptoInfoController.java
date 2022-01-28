package com.example.controller;

import com.example.entity.Crypto;
import com.example.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CryptoInfoController {

    @Autowired
    CryptoService cryptoService;

    @GetMapping("/getPriceById")
    public Crypto crypto(@RequestParam(name = "id", defaultValue = "90") Long id) {
        return cryptoService.getCryptoById(id);
    }

    @GetMapping("/getAll")
    public List<Crypto> allCryptos() {
        return cryptoService.getAll();
    }
}