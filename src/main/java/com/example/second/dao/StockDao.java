package com.example.second.dao;

import com.example.second.entity.Stock;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao {

    @Update("update stock set stock_amout=stock_amout-1 where id=#{id} and stock_amout>0")
    public boolean updateStock(Stock Stock);
}
