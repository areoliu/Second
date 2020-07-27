package com.example.second.controller;

import com.example.second.entity.*;
import com.example.second.service.OrderService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "order/createOrder")
    public Map<String,Object> createOrder(@RequestBody HttpRequest httpRequest){
        Map<String, Object> responseMap = new HashMap<String, Object>();
        HttpResponseBody responseBody = new HttpResponseBody();
        HttpResponseHead responseHead = new HttpResponseHead();
        Gson gson=new Gson();
        String str=gson.toJson(httpRequest.getRequestBody().getRequestData());
        Order order=gson.fromJson(str,Order.class);
        orderService.createOrder(order);
        System.out.println(order);
        responseBody.setResultCode("S00001");
        responseBody.setResultMessage("succeed!");
        responseHead.setToken("");
        responseHead.setDate(new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new Date()));
        responseMap.put("responseBody",responseBody);
        responseMap.put("responseHead",responseHead);
        return responseMap;
    }

}
