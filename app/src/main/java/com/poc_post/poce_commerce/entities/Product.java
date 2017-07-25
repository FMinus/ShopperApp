package com.poc_post.poce_commerce.entities;


import java.io.Serializable;

import lombok.Data;

@Data
public class Product implements Serializable{
    private Long id;
    private String name;
    private double price;
    private String picture_url;

}
