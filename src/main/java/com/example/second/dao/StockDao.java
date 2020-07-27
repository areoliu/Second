package com.example.second.dao;

import com.example.second.entity.Stock;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao {

    @Update("update stock set stock_amout=stock_amout-#{buys} where stock_sku=#{stockSku} and stock_amout>#{buys}")
    public boolean updateStock(int buys,String stockSku);
}
