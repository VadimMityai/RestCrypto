package com.example.repository;

import com.example.entity.Crypto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Component
public interface CryptoRepository extends JpaRepository<Crypto, Long> {

    Optional<Crypto> findById(Long id);

    Crypto save(Crypto crypto);
}
