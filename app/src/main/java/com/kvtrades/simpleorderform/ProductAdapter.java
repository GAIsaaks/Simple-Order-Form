package com.kvtrades.simpleorderform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kvtrades.simpleorderform.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterVH>{

    public ArrayList<Product> products;
    public Context context;

    private String ip = "http://10.0.2.2:8080";
    private String approot = "/2021_github_projects/order-form/";
    Intent i;
    Activity activity;

    public ProductAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ProductAdapter.ProductAdapterVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ProductAdapter.ProductAdapterVH(LayoutInflater.from(context).inflate(R.layout.products_rv_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.ProductAdapterVH holder, int position) {
        Product product = products.get(position);
        final int pid = product.getId();
        final String prod_name = product.getProd_name();
        final String prod_desc = product.getProd_desc();
        final double prod_price = product.getProd_price();

        holder.prod_id.setText(""+pid);
        holder.prod_name.setText(prod_name);
        holder.prod_desc.setText(prod_desc);
        holder.prod_price.setText(""+prod_price);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(activity, Order.class);
                i.putExtra("pid",pid);
                i.putExtra("prod_name",prod_name);
                i.putExtra("prod_desc",prod_desc);
                i.putExtra("prod_price",prod_price);
                activity.startActivityForResult(i, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductAdapterVH extends RecyclerView.ViewHolder {
        TextView prod_id, prod_name, prod_desc, prod_price;


        public ProductAdapterVH(@NonNull @NotNull View itemView) {
            super(itemView);

            prod_id = itemView.findViewById(R.id.productIDTV);
            prod_name = itemView.findViewById(R.id.productNameTV);
            prod_price = itemView.findViewById(R.id.productPriceTV);
            prod_desc = itemView.findViewById(R.id.productDescTV);
        }
    }
}
