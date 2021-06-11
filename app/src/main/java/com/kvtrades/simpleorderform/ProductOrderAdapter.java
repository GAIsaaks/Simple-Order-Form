package com.kvtrades.simpleorderform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kvtrades.simpleorderform.model.ProductOrder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProductOrderAdapter extends RecyclerView.Adapter<ProductOrderAdapter.ProductOrderAdapterVH> {

    public ArrayList<ProductOrder> orders;
    public Context context;

    private String ip = "http://10.0.2.2:8080";
    private String approot = "/2021_github_projects/order-form/";
    Intent i;
    Activity activity;

    public ProductOrderAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void setProductOrders(ArrayList<ProductOrder> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ProductOrderAdapter.ProductOrderAdapterVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ProductOrderAdapter.ProductOrderAdapterVH(LayoutInflater.from(context).inflate(R.layout.orders_rv_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductOrderAdapter.ProductOrderAdapterVH holder, int position) {
        ProductOrder order = orders.get(position);
        String title = order.getProductTitle();
        double price = order.getPrice();
        int qty = order.getQty();
        double sum = order.getSum();
        String cDate = order.getOrderDate();

        holder.proTitleTV.setText(title);
        holder.proPriceTV.setText(""+price);
        holder.proQtyTV.setText(""+qty);
        holder.proSumTV.setText(""+sum);
        holder.clientOrderDateTV.setText(cDate);

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ProductOrderAdapterVH extends RecyclerView.ViewHolder {
        TextView proTitleTV, proPriceTV, proQtyTV, proSumTV, clientOrderDateTV;

        public ProductOrderAdapterVH(@NonNull @NotNull View itemView) {
            super(itemView);
            proTitleTV = itemView.findViewById(R.id.proTitleTV);
            proPriceTV = itemView.findViewById(R.id.proPriceTV);
            proQtyTV = itemView.findViewById(R.id.proQtyTV);
            proSumTV = itemView.findViewById(R.id.proSumTV);
            clientOrderDateTV = itemView.findViewById(R.id.clientOrderDateTV);
        }
    }
}
