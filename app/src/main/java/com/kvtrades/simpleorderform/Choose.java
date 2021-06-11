package com.kvtrades.simpleorderform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {

    Intent i;
    Button toProdBtn, toOrdersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        toProdBtn = findViewById(R.id.viewProdsBtn);
        toOrdersBtn = findViewById(R.id.viewMyOrdersBtn);

        toProdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Choose.this, MainActivity.class);
                startActivity(i);
            }
        });
        toOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Choose.this, MyOrders.class);
                startActivity(i);
            }
        });
    }
}