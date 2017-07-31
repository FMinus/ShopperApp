package com.poc_post.poce_commerce.presenterTests;

import com.poc_post.poce_commerce.entities.Product;
import com.poc_post.poce_commerce.presenters.ProductListPresenter;
import com.poc_post.poce_commerce.repositories.ProductRepository;
import com.poc_post.poce_commerce.ui.screen_contracts.ProductListContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import io.reactivex.Observable;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class ProductListPresenterTests {

    @Spy ProductRepository repository;
    @Mock ProductListContract.View mView;

    ProductListPresenter presenter;

    MockWebServer mockWebServer;
    Retrofit retrofit;
    String id = "123456";
    String mockProductJSONBody = "[{\"_id\":\""+id+"\",\"_rev\":\"1-d051af4b134f1133a15ba22665f8335f\",\"name\":\"milk\",\"price\":5.6,\"picture_url\":\"http://www.legendairy.com.au/~/media/legendairy/images/lhp/milk-carton-thumbnail.jpg\"}]";


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


        mockWebServer = new MockWebServer();
        retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        repository = retrofit.create(ProductRepository.class);
        presenter = new ProductListPresenter(repository,mView);
        mockWebServer.enqueue(new MockResponse().setBody(mockProductJSONBody));
    }

    @Test
    public void shouldCallDisplayProductsOnViewOnceRepositoryReturns() throws Exception {

        //setup the repository to return empty list when findAll is called
        when(repository.findAll()).thenReturn(Observable.just(Arrays.asList(new Product())));

        presenter.findAllProducts();

        verify(mView,times(1)).showProducts(Mockito.anyListOf(Product.class));
    }

}
