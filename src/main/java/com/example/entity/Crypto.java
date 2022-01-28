package com.example.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Crypto {

    @Id
    @NotNull
    private Long id;

    private String symbol;

    private Double price;
}