package com.example.second.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.api.service.StockService;
import com.example.second.config.MsgProducer;
import com.example.second.entity.*;
import com.example.second.service.OrderService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Transactional
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @Reference(version = "2.0.0")
    StockService stockService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private MsgProducer msgProducer;


    private String KEY="STOCK";
    private String LOCK_KEY="LOCK_KEY";
    private int LOCK_TIME=5;

    @PostMapping(value = "order/createOrder")
    public Map<String,Object> createOrder(@RequestBody HttpRequest httpRequest){
        RLock rLock = redissonClient.getLock(LOCK_KEY);
        Map<String, Object> responseMap = new HashMap<String, Object>();
        HttpResponseBody responseBody = new HttpResponseBody();
        HttpResponseHead responseHead = new HttpResponseHead();
        Gson gson=new Gson();
        String str=gson.toJson(httpRequest.getRequestBody().getRequestData());
        Order order=gson.fromJson(str,Order.class);
        try{
            rLock.lock();
//            Thread.sleep(2000);
            String stockValue=stringRedisTemplate.opsForValue().get(KEY);
            int buys=order.getOrderAmout();
            int stockValueNow=Integer.parseInt(stockValue);
            log.info(Thread.currentThread().getName()+" now stock is "+stockValue);
            QueueMessage message = new QueueMessage();

            if(stockValue!=null && stockValueNow>buys){
                orderService.createOrder(order);
                stringRedisTemplate.opsForValue().set(KEY,stockValueNow-buys+"");
//                message.setMsgId(UUID.randomUUID().toString());
//                message.setMsgText(JSONObject.toJSON(order).toString());
//                msgProducer.sendMsg(message);
////                stringRedisTemplate.opsForValue().set(order.getId()+"","true");
//                responseBody.setResultCode("S00001");
//                responseBody.setResultMessage("succeed!");
                //System.out.println("succeed");
                if(stockService.updateStock(order.getOrderAmout(),order.getOrderSku())){
                    responseBody.setResultCode("S00001");
                    responseBody.setResultMessage("succeed!");
                    System.out.println("succeed");
                }
                else{
                    responseBody.setResultCode("F00001");
                    responseBody.setResultMessage("failed!");
                    System.out.println("failed");
                }
            }
//            else if(stockValue==null){
//                stockValue = stockService.queryStock()+"";
//                stringRedisTemplate.opsForValue().set(KEY,stockValueNow-buys+"");
//                orderService.createOrder(order);
//                responseBody.setResultCode("S00001");
//                responseBody.setResultMessage("succeed!");
////                //System.out.println("succeed");
////                if(stockService.updateStock(order.getOrderAmout(),order.getOrderSku())){
////                    responseBody.setResultCode("S00001");
////                    responseBody.setResultMessage("succeed!");
////                    System.out.println("succeed");
////                }
////                else{
////                    responseBody.setResultCode("F00001");
////                    responseBody.setResultMessage("failed!");
////                    System.out.println("failed");
////                }
//            }
            else{
                responseBody.setResultCode("F00002");
                responseBody.setResultMessage("failed!stock is not enough");
//                System.out.println("failed");
            }


        }catch (Exception e){
            e.printStackTrace();
            responseBody.setResultCode("F00003");
            responseBody.setResultMessage("failed!exception");
//            System.out.println("failed");
        }finally {
            rLock.unlock();
            responseHead.setToken("");
            responseHead.setDate(new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new Date()));
            responseMap.put("responseBody",responseBody);
            responseMap.put("responseHead",responseHead);
        }

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.print(1/0);
        return responseMap;
    }

    @GetMapping(value = "order/test")
    public String test(){
        return "test now";
    }

}
