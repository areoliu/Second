package com.example.second.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Stock {

    private int id;

    private String stockSku;

    private String stockAmout;

    private Date createDate;

    private Date modefyDate;

}
