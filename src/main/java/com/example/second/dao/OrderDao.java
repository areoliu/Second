package com.example.second.dao;

import com.example.second.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    @Insert("insert into order(uid,order_id)values(#{uid},#{orderId})")
    public int insert(Order order);
}
