package com.example.second.service;


import com.example.second.dao.OrderDao;
import com.example.second.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public int createOrder(Order order) {
        return orderDao.insert(order);
    }
}
