package com.kvtrades.simpleorderform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.kvtrades.simpleorderform.api.API;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Order extends AppCompatActivity {
    TextView prod_idTV, prod_nameTV, prod_descTV, prod_priceTV;
    String str_prod_name, str_prod_desc, str_prod_qty, str_cName,  str_cEmail, str_cAddress;
    int prod_Id, cNum, prod_qty;
    double prod_price;
    EditText prod_qtyET, cNameET, cNumET, cEmailET, cAddressET;
    Button orderBtn;
    Intent i;

    private String ip = "http://10.0.2.2/";
    private String approot = "/2021_github_projects/order-form/";
    private API apiInterface;
    private Retrofit retrofit;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        prod_idTV = findViewById(R.id.prodIdTV);
        prod_nameTV = findViewById(R.id.prodNameTV);
        prod_descTV = findViewById(R.id.prodDescTV);
        prod_priceTV = findViewById(R.id.prodPriceTV);;
        prod_qtyET = findViewById(R.id.qtyET);
        cNameET = findViewById(R.id.cNameET);
        cNumET = findViewById(R.id.cNumberET);
        cEmailET = findViewById(R.id.cEmailET);
        cAddressET = findViewById(R.id.cAddressET);
        orderBtn  = findViewById(R.id.orderBtn);

        i = getIntent();
        prod_Id = (int) i.getIntExtra("pid", 0);
        str_prod_name = i.getStringExtra("prod_name");
        str_prod_desc = i.getStringExtra("prod_desc");
        prod_price = (double) i.getDoubleExtra("prod_price", 0);

        prod_idTV.append(""+prod_Id);
        prod_nameTV.append(str_prod_name);
        prod_descTV.append(str_prod_desc);
        prod_priceTV.append(""+prod_price);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient();

        retrofit = new Retrofit.Builder()
                .baseUrl(ip+approot)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiInterface = retrofit.create(API.class);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!prod_qtyET.getText().toString().isEmpty() && !cNameET.getText().toString().isEmpty()
                    && !cNumET.getText().toString().isEmpty() && !cEmailET.getText().toString().isEmpty()
                    && !cAddressET.getText().toString().isEmpty()) {

                    prod_qty = Integer.parseInt(prod_qtyET.getText().toString());
                    str_cName = cNameET.getText().toString();
                    cNum = Integer.parseInt(cNumET.getText().toString());
                    str_cEmail = cEmailET.getText().toString();
                    str_cAddress = cAddressET.getText().toString();
                    double sum = prod_price * prod_qty;

                    makeOrder(prod_Id, prod_qty, sum, str_cName, cNum, str_cEmail, str_cAddress);
                }
                else {
                    Toast.makeText(Order.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    boolean isSuccess = false;
    private boolean makeOrder(int prod_id,  int prod_qty, double sum, String str_cName, int cNum, String str_cEmail, String str_cAddress) {

        Call<String> call = apiInterface.addOrder("addorder", prod_id, prod_qty , sum, str_cName, cNum, str_cEmail, str_cAddress);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Log.d("prods_headers", ""+response.headers() );
                    return;
                }

                if (response.body().contains("success")) {
                    Toast.makeText(Order.this, "Order Placed Successfully.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Order.this, "Error placing order, try again...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Order.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("getSuppressed", ""+t.getSuppressed() );
                Log.d("initCause", ""+t.initCause(t.getCause()) );
                Log.d("fillInStackTrace", ""+t.fillInStackTrace());
                Log.d("putOrder",t.getMessage());
                Log.d("sendData","prod_Id: "+prod_Id+", prod_qty: "+prod_qty+", sum: "+sum+
                        ", str_cName: "+str_cName+", cNum: "+cNum+", str_cEmail: "+str_cEmail+", str_cAddress: "+str_cAddress);
            }
        });

        return false;
    }

}