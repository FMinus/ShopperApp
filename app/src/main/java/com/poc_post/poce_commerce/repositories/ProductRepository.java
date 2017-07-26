package com.poc_post.poce_commerce.repositories;

import com.poc_post.poce_commerce.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductRepository {

    @GET("cloudant")
    Call<List<Product>> findAll();

    @GET("products/name/{name}")
    Call<List<Product>> findByName(@Path("name") String name);
}
