package com.efc.cryptoapi.controller;

import com.efc.cryptoapi.entity.Coin;
import com.efc.cryptoapi.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/coin")
public class CoinController {

  @Autowired
  private CoinRepository coinRepository;

  @GetMapping
  public ResponseEntity findAll() {
    return new ResponseEntity<>(coinRepository.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{name}")
  public ResponseEntity findByName(@PathVariable("name") String name) {
    try {
      return new ResponseEntity<>(coinRepository.findByName(name), HttpStatus.OK);
    }
    catch (Exception error) {
      return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity insert(@RequestBody Coin coin) {
    try {
      coin.setDateTime(new Timestamp(System.currentTimeMillis()));
      return new ResponseEntity<>(coinRepository.save(coin), HttpStatus.CREATED);
    } catch (Exception error) {
      return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



}
