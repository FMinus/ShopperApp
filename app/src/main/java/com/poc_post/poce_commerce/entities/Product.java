package com.poc_post.poce_commerce.entities;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class Product extends BaseEntity {

    private String name;
    private double price;
    private String picture_url;

}
