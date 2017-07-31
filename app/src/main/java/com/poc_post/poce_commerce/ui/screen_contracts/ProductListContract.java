package com.poc_post.poce_commerce.ui.screen_contracts;

import com.poc_post.poce_commerce.entities.Product;

import java.util.List;

public interface ProductListContract {

    interface View {

        void showProducts(List<Product> products);
        void showProduct(Product product);
        void addProductToCart(Product product);
        void toggleProgressBar(boolean isVisible);
    }

    interface UserActionsListener {

        void findAllProducts();
        void findProductByName(String productName);
    }
}
