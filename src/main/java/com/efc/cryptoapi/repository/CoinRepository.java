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

}
