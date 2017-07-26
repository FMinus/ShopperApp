package com.poc_post.poce_commerce.repositories;

import com.poc_post.poce_commerce.entities.Product;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ProductRepository {

    @GET("cloudant")
    Observable<List<Product>> findAll();

    @GET("products/name/{name}")
    Observable<List<Product>> findByName(@Path("name") String name);
}
