package com.example.symatiqueapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.symatiqueapplication.R;
import com.example.symatiqueapplication.models.CartItem;

import java.util.List;

public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.PopupViewHolder> {

    private List<CartItem> cartItemList;
    private LayoutInflater mInflater;

    public PopupAdapter(Context context, List<CartItem> cartItemList) {
        this.mInflater = LayoutInflater.from(context);
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public PopupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.popup_item, parent, false);
        return new PopupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopupViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.productNameTextView.setText(cartItem.getProductName());
        holder.productPriceTextView.setText(cartItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }


    public double calculateTotal() {
        double total = 0.0;
        for (CartItem cartItem : cartItemList) { // Use 'cartItemList' instead of 'shoppingCart'
            String priceString = cartItem.getPrice().replace("DT", "").trim();
            double price = Double.parseDouble(priceString);
            total += price * cartItem.getQuantity();
        }
        return total;
    }

    public static class PopupViewHolder extends RecyclerView.ViewHolder {

        TextView productNameTextView;
        TextView productPriceTextView;

        public PopupViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.textView_productName);
            productPriceTextView = itemView.findViewById(R.id.textView_productPrice);
        }
    }
}