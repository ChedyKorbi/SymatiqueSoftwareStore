package com.example.symatiqueapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.symatiqueapplication.Adapters.ProductAdapter;
import com.example.symatiqueapplication.R;
import com.example.symatiqueapplication.Repository.CartRepository;
import com.example.symatiqueapplication.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private List<Product> productList;
    private CartRepository cartRepository; // Reference the CartRepository
    private TextView textViewCartBadge; // TextView reference for cart badge
    private ImageView cartIconImageView; // ImageView reference for cart icon

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_list);

        // Initialize the cart repository
        cartRepository = new CartRepository(this);

        initData();

        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set the adapter
        ProductAdapter productAdapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(productAdapter);

        // Set click listener for the cart icon
        cartIconImageView = findViewById(R.id.imageView_cart);
        cartIconImageView.setOnClickListener(v -> {
            // Handle cart icon click
            Intent intent = new Intent(ProductsActivity.this,CartActivity.class);
            startActivity(intent);
        });

        // Find the cart badge TextView
        textViewCartBadge = findViewById(R.id.textView_cart_badge);

        // Update the cart badge with the current item count
        updateCartBadge();
    }

    private void initData() {
        productList = getSampleProductList();
        // adapter should be notified when productList is updated
    }

    // Sample method to generate a list of products for testing
    private List<Product> getSampleProductList() {
        List<Product> sampleList = new ArrayList<>();
        sampleList.add(new Product(1, "SmartCRM", "119.99 DT", "Mobile APP", R.drawable.smartcrmimg));
        sampleList.add(new Product(2, "Smart Merchandising", "229.99 DT", "CRM", R.drawable.smartmerch));
        sampleList.add(new Product(3, "Swifto ERP", "149.99 DT", "ERP", R.drawable.smartmerch));
        sampleList.add(new Product(4, "SmartSeller", "179.99 DT", "ERP", R.drawable.smartcrmimg)); // Fixed the ID and Image to be unique

        return sampleList;
    }

    // Method to update the cart badge count
    public void updateCartBadge() {
        int itemCount = cartRepository.getItemCount(); // Get the item count from repository
        if (textViewCartBadge != null) {
            runOnUiThread(() -> {
                textViewCartBadge.setText(String.valueOf(itemCount));
                textViewCartBadge.setVisibility(itemCount > 0 ? View.VISIBLE : View.INVISIBLE);
            });
        }
    }

    // You may want to override onResume to refresh the cart count when coming back to this activity
    @Override
    protected void onResume() {
        super.onResume();
        updateCartBadge();
    }

    // ... Rest of your existing code for the activity if there's more ...

}