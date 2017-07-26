package com.poc_post.poce_commerce.screen_contracts;

import com.poc_post.poce_commerce.entities.Product;

import java.util.List;

public interface ProductListContract {

    interface View {

        void showProducts(List<Product> products);
        void showProduct(Product product);
        void addProductToCart(Product product);
    }

    interface UserActionsListener {

        void findAllProducts();
        void findProductByName(String productName);
    }
}
