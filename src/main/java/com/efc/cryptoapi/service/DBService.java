package com.efc.cryptoapi.service;

import com.efc.cryptoapi.entity.Coin;
import com.efc.cryptoapi.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class DBService {

  @Autowired
  private CoinRepository repository;

  public void instanciaDB(){
    Coin c1 = new Coin();
    c1.setName("BITCOIN");
    c1.setPrice(new BigDecimal(1000));
    c1.setQuantity(new BigDecimal(0.012));
    c1.setDateTime(new Timestamp(System.currentTimeMillis()));

    Coin c2 = new Coin();
    c2.setName("BITCOIN");
    c2.setPrice(new BigDecimal(600));
    c2.setQuantity(new BigDecimal(0.005));
    c2.setDateTime(new Timestamp(System.currentTimeMillis()));

    Coin c3 = new Coin();
    c3.setName("ETHEREUM");
    c3.setPrice(new BigDecimal(1500));
    c3.setQuantity(new BigDecimal(2.5));
    c3.setDateTime(new Timestamp(System.currentTimeMillis()));

    repository.save(c1);
    repository.save(c2);
    repository.save(c3);

  }

}
