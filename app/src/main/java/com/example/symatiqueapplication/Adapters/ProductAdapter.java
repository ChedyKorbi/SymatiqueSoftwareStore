package com.example.symatiqueapplication.Adapters;

import static android.widget.Toast.LENGTH_SHORT;

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
import com.example.symatiqueapplication.activities.ProductsActivity;
import com.example.symatiqueapplication.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product currentProduct = productList.get(position);

        holder.imageView.setImageResource(currentProduct.getImageResourceId());
        holder.textViewProductName.setText(currentProduct.getName());
        holder.textViewProductPrice.setText(currentProduct.getPrice());
        holder.textViewProductCategory.setText(currentProduct.getCategory());

        holder.buttonAddToCart.setOnClickListener(view -> {
            CartRepository cartItemRepo = new CartRepository(context);

            // Check if the product is already in the cart
            if (cartItemRepo.isProductInCart(currentProduct.getName())) {
                Toast.makeText(context, "Product is already in the cart", LENGTH_SHORT).show();
            } else {
                // Add the product to the cart
                cartItemRepo.addProductToCart(currentProduct);
                if (context instanceof ProductsActivity) {
                    ((ProductsActivity) context).updateCartBadge();
                }
                Toast.makeText(context, "Added to cart successfully", LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewProductName;
        TextView textViewProductPrice;
        TextView textViewProductCategory;
        Button buttonAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView_product);
            textViewProductName = itemView.findViewById(R.id.textView_productName);
            textViewProductPrice = itemView.findViewById(R.id.textView_productPrice);
            textViewProductCategory = itemView.findViewById(R.id.textView_productCategory);
            buttonAddToCart = itemView.findViewById(R.id.button_addToCart);
        }
    }
}
