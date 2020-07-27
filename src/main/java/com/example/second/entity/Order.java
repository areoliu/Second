package com.example.second.entity;

import lombok.Data;

import java.util.Date;


@Data
public class Order {

    private int id;

    private int uid;

    private String orderId;

    private String orderSku;

    private int orderAmout;

    private double orderPrice;

    private Date createDate;

    private Date modefyDate;

    private int orderStatus;
}
