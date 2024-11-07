package com.example.symatiqueapplication.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "cart_items")
public class CartItem {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private int productId;

    @DatabaseField(canBeNull = false)
    private String productName;

    @DatabaseField
    private int quantity;

    @DatabaseField(canBeNull = false)
    private String price;

    @DatabaseField
    private int imageResourceId;

    // Empty constructor required for ORMLite
    public CartItem() {
    }

    public CartItem(Product product) {
        this.productName = product.getName();
        this.quantity = 1;
        this.price = product.getPrice();
        this.imageResourceId = product.getImageResourceId();
    }

    public CartItem(CartItem product) {
        // You might want to implement this constructor if needed
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
