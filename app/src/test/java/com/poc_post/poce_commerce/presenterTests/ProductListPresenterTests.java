package com.poc_post.poce_commerce.presenterTests;

import com.poc_post.poce_commerce.entities.Product;
import com.poc_post.poce_commerce.presenters.ProductListPresenter;
import com.poc_post.poce_commerce.repositories.ProductRepository;
import com.poc_post.poce_commerce.ui.screen_contracts.ProductListContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;
import retrofit2.Response;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductListPresenterTests {

    @Mock ProductRepository repository;
    @Mock ProductListContract.View mView;

    @InjectMocks ProductListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


//        mockWebServer = new MockWebServer();
//        retrofit = new Retrofit.Builder()
//                .baseUrl(mockWebServer.url("").toString())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

//        repository = retrofit.create(ProductRepository.class);
//        presenter = new ProductListPresenter(repository,mView);
//        mockWebServer.enqueue(new MockResponse().setBody(mockProductJSONBody));
    }

    @Test
    public void shouldCallDisplayProductsOnViewOnceRepositoryReturns() throws Exception {

        //setup the repository to return empty list when findAll is called
        when(repository.findAll()).thenReturn(Observable.just(Arrays.asList(new Product(), new Product())));

        presenter.findAllProducts();

        verify(mView, times(1)).showProducts(Mockito.anyListOf(Product.class));
    }

    @Test
    public void shouldCallDisplayProductsOnViewOnceRepositoryReturnsObservers() throws Exception {

        //setup the repository to return empty list when findAll is called
        when(repository.findAll()).thenReturn(Observable.just(Arrays.asList(new Product(), new Product())));
        TestSubscriber<Response<Product>> testSubscriber = new TestSubscriber<>();

        TestScheduler testScheduler = new TestScheduler();

        presenter.findAllProducts(testScheduler, testScheduler);

        verify(mView, times(1)).showProducts(Mockito.anyListOf(Product.class));
    }
}
