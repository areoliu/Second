package com.example.second.dao;

import com.example.second.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into orders(uid,order_id)values(#{uid},#{orderId})")
    public int insert(Order order);
}
