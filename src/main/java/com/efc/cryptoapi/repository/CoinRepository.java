package com.efc.cryptoapi.repository;

import com.efc.cryptoapi.dto.CoinDTO;
import com.efc.cryptoapi.entity.Coin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CoinRepository {

  private static String INSERT = "INSERT INTO  coin (name, price, quantity, datetime) VALUES (?, ?, ?, ?)";
  private static String SELECT_ALL = "SELECT name, SUM(quantity) AS Quantity FROM coin GROUP BY name";
  private static String SELECT_BY_NAME = "SELECT * FROM coin WHERE UPPER(name) = ?";
  private static String DELETE = "DELETE FROM coin WHERE id = ?";

  private JdbcTemplate jdbcTemplate;

  public CoinRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

  public Coin save(Coin coin){
    Object[] attr = new Object[] {
      coin.getName(),
      coin.getPrice(),
      coin.getQuantity(),
      coin.getDateTime()
    };
    jdbcTemplate.update(INSERT, attr);
    return coin;
  }

  public List<CoinDTO> findAll(){
    return jdbcTemplate.query(SELECT_ALL, new RowMapper<CoinDTO>() {
      @Override
      public CoinDTO mapRow(ResultSet data, int rowNum) throws SQLException {

        CoinDTO coin = new CoinDTO();
        coin.setName(data.getString("name"));
        coin.setQuantity(data.getBigDecimal("quantity"));

        return coin;
      }
    });
  }

  public List<Coin> findByName(String name){
    Object[] attr = new Object[] { name.toUpperCase() };
    return jdbcTemplate.query(SELECT_BY_NAME, new RowMapper<Coin>() {
      @Override
      public Coin mapRow(ResultSet data, int rowNum) throws SQLException {

        Coin coin = new Coin();
        coin.setId(data.getInt("id"));
        coin.setName(data.getString("name"));
        coin.setPrice(data.getBigDecimal("price"));
        coin.setQuantity(data.getBigDecimal("quantity"));
        coin.setDateTime(data.getTimestamp("dateTime"));

        return coin;
      }
    }, attr);
  }

  public Integer delete(Integer id){
    return jdbcTemplate.update(DELETE, id);
  }

}
