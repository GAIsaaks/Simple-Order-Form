package com.kvtrades.simpleorderform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kvtrades.simpleorderform.api.API;
import com.kvtrades.simpleorderform.model.Product;
import com.kvtrades.simpleorderform.model.ProductOrder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyOrders extends AppCompatActivity {

    EditText mobileNumET;
    Intent i;
    private String ip = "http://10.0.2.2/";
    private String approot = "/2021_github_projects/order-form/";

    ArrayList<ProductOrder> productOrders;
    RecyclerView recyclerView;
    private API apiInterface;
    private Retrofit retrofit;
    ProductOrderAdapter productOrderAdapter;
    Button getOrdersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        recyclerView = findViewById(R.id.listOrdersRV);

        retrofit = new Retrofit.Builder()
                .baseUrl(ip+approot)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyOrders.this));
        productOrderAdapter = new ProductOrderAdapter(MyOrders.this,MyOrders.this);

        apiInterface = retrofit.create(API.class);

        getOrdersBtn = findViewById(R.id.getOrdersBtn);
        getOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobileNumET = findViewById(R.id.mobileNumET);
                int searchNumber;
                if (mobileNumET.getText().toString().isEmpty() || mobileNumET.getText().toString() == "") {
                    Toast.makeText(MyOrders.this, "Please enter a number of 9 digits", Toast.LENGTH_SHORT).show();
                }
                else {
                    searchNumber = Integer.parseInt(mobileNumET.getText().toString());
                    getAllProductOrders(searchNumber);
                }
            }
        });
    }

    private void getAllProductOrders(int searchNumber) {
        Call<ArrayList<ProductOrder>> call = apiInterface.getProductOrders("getproducts", searchNumber);

        call.enqueue(new Callback<ArrayList<ProductOrder>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductOrder>> call, Response<ArrayList<ProductOrder>> response) {
                if (!response.isSuccessful()) {
                    //Log.d("prods_headers", ""+response.headers() );
                    //Log.d("prods_code", ""+response.code() );
                    return;
                }

                productOrders = response.body();
                productOrderAdapter.setProductOrders(productOrders);

                recyclerView.setAdapter(productOrderAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<ProductOrder>> call, Throwable t) {
                Toast.makeText(MyOrders.this, t.getMessage(), Toast.LENGTH_LONG).show();
                //Log.d("getProcts",t.getMessage());
            }
        });
    }

}