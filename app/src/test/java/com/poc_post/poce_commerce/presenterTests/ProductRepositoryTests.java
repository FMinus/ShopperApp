package com.poc_post.poce_commerce.presenterTests;

import com.poc_post.poce_commerce.entities.Product;
import com.poc_post.poce_commerce.repositories.ProductRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductRepositoryTests {

    MockWebServer mockWebServer;
    Retrofit retrofit;
    ProductRepository productRepository;
    String id = "123456";

    String mockProductJSONBody = "[{\"_id\":\""+id+"\",\"_rev\":\"1-d051af4b134f1133a15ba22665f8335f\",\"name\":\"milk\",\"price\":5.6,\"picture_url\":\"http://www.legendairy.com.au/~/media/legendairy/images/lhp/milk-carton-thumbnail.jpg\"}]";

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productRepository = retrofit.create(ProductRepository.class);
        mockWebServer.enqueue(new MockResponse().setBody(mockProductJSONBody));
    }

    @Test
    public void shouldReturnMockResponseFromSimpleCall() throws Exception {

        Call<List<Product>> all = productRepository.simple();
        Response<List<Product>> execute = all.execute();
        System.out.println(execute.body());
    }

    @Test
    public void shouldReturnMockResponseFromRxObservable() throws Exception {

        Observable<List<Product>> all = productRepository.findAll();
        List<Product> products = all.blockingFirst();
        System.out.println(products);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }
}
