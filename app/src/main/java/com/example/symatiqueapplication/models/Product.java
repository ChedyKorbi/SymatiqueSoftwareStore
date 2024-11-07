package com.example.symatiqueapplication.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "product")

public class Product {
    @DatabaseField(generatedId = true) // Cela générera automatiquement un ID unique pour chaque élément du panier.
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String price;
    @DatabaseField
    private String category;
    @DatabaseField
    private int imageResourceId;


    public Product() {}
    // Constructor to initialize the Product object
    public Product(int id, String name, String price, String category, int imageResourceId) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.imageResourceId = imageResourceId;
    }

    // Getter methods to access the private properties
    public  String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

}
