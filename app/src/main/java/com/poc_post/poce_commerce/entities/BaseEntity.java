package com.poc_post.poce_commerce.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class BaseEntity implements Serializable {

    @SerializedName("_id")
    private String id;

    @SerializedName("_rev")
    private String _rev;
}
