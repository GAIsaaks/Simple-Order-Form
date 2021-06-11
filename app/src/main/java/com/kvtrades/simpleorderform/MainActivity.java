package com.kvtrades.simpleorderform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.kvtrades.simpleorderform.api.API;
import com.kvtrades.simpleorderform.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText priceET;
    Intent i;
    private String ip = "http://10.0.2.2/";
    private String approot = "/2021_github_projects/order-form/";

    ArrayList<Product> products;
    RecyclerView recyclerView;
    private API apiInterface;
    private Retrofit retrofit;
    ProductAdapter productAdapter;

    TextView bizNameTV, bizEmailTV, bizWebAdTV, adSDescTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listProductsRV);

        retrofit = new Retrofit.Builder()
                .baseUrl(ip+approot)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        productAdapter = new ProductAdapter(MainActivity.this,MainActivity.this);

        apiInterface = retrofit.create(API.class);
        getAllProducts();
    }

    public void getAllProducts() {
        Call<ArrayList<Product>> call = apiInterface.getProducts("getproducts");

        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (!response.isSuccessful()) {
                    //Log.d("prods_headers", ""+response.headers() );
                    //Log.d("prods_code", ""+response.code() );
                    return;
                }

                products = response.body();
                productAdapter.setProducts(products);

                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                //Log.d("getProcts",t.getMessage());
            }
        });
    }
}