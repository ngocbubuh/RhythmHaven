package com.sap.rhythmhaven.API;

public class ProductRepository {
    public static ProductService getProductService(){
        return APIClient.getClient().create(ProductService.class);
    }
}
