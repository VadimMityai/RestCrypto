package com.example.service;

import com.example.entity.Crypto;
import com.example.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CryptoService {

    @Autowired
    CryptoRepository cryptoRepository;

    public Crypto getCryptoById(Long id) {
        Optional<Crypto> optionalCrypto = cryptoRepository.findById(id);
        return optionalCrypto.get();
    }

    public List<Crypto> getAll() {
        return cryptoRepository.findAll();
    }

    public void updateCryptoPrice(Double price, Long id) {
        Crypto crypto = getCryptoById(id);
        crypto.setPrice(price);
        cryptoRepository.save(crypto);
    }
}
