package com.sap.rhythmhaven.API;

import com.sap.rhythmhaven.entity.ProductEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    String PRODUCTS = "products";

    @GET(PRODUCTS)
    Call<ProductEntity[]> getProducts();

    @GET(PRODUCTS + "/{id}")
    Call<ProductEntity> getProductById(@Path("id") Object id);
}
