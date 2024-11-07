package com.example.symatiqueapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.symatiqueapplication.R;
import com.example.symatiqueapplication.Repository.CartRepository;
import com.example.symatiqueapplication.models.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> shoppingCart;
    private Context context;

    public CartAdapter(List<CartItem> shoppingCart, Context context) {
        this.shoppingCart = shoppingCart;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem currentItem = shoppingCart.get(position);

        holder.textViewItemName.setText(currentItem.getProductName());
        holder.textViewItemQuantity.setText(String.valueOf(currentItem.getQuantity()));
        holder.textViewItemPrice.setText(currentItem.getPrice());
        holder.imageViewProduct.setImageResource(currentItem.getImageResourceId());

        holder.buttonDeleteItem.setOnClickListener(view -> {
            CartRepository cartItemRepo = new CartRepository(context);
            cartItemRepo.deleteItemById(currentItem.getId());
            shoppingCart.remove(currentItem);
            notifyItemRemoved(position);
            Toast.makeText(context, "Item deleted from cart ", Toast.LENGTH_SHORT).show();
        });
    }

    public void deleteAllItems() {
        CartRepository cartItemRepo = new CartRepository(context);
        cartItemRepo.deleteAllItems();

        // Clear the shoppingCart list
        shoppingCart.clear();

        // Notify the adapter that all items are removed
        notifyDataSetChanged();

        // Show a toast indicating that all items are delete
        Toast.makeText(context, "All items deleted from cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return shoppingCart.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName;
        TextView textViewItemQuantity;
        TextView textViewItemPrice;
        Button buttonDeleteItem;
        ImageView imageViewProduct;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewItemName = itemView.findViewById(R.id.textView_itemName);
            textViewItemQuantity = itemView.findViewById(R.id.textView_itemQuantity);
            textViewItemPrice = itemView.findViewById(R.id.textView_itemPrice);
            buttonDeleteItem = itemView.findViewById(R.id.button_deleteitem);
            imageViewProduct = itemView.findViewById(R.id.imageView_product);
        }
    }
}
