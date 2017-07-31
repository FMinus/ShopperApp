package com.poc_post.poce_commerce.repositories;

import com.poc_post.poce_commerce.entities.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductRepository {

    @GET("cloudant")
    Observable<List<Product>> findAll();

    @GET("products/name/{name}")
    Observable<List<Product>> findByName(@Path("name") String name);

    @GET("products/find/all")
    Call<List<Product>> simple();
}
