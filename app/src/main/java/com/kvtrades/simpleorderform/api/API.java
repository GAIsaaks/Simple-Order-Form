package com.kvtrades.simpleorderform.api;


import com.kvtrades.simpleorderform.model.Product;
import com.kvtrades.simpleorderform.model.ProductOrder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("api/")
    Call<ArrayList<Product>> getProducts(@Query("getproducts") String getproducts);

    @GET("api/")
    Call<ArrayList<ProductOrder>> getProductOrders(@Query("getProductOrders") String getProductOrders, @Query("searchNumber") int searchNumber);

    //@FormUrlEncoded
    @GET("api/")
    Call<String> addOrder(@Query("addorder") String addorder, @Query("product_id") int product_id, @Query("qty") int qty,
                          @Query("sum") double sum, @Query("client_name") String client_name,
                          @Query("client_mobile") int client_mobile, @Query("client_email") String client_email,
                          @Query("client_address") String client_address);//, @Field("created") String created
}
