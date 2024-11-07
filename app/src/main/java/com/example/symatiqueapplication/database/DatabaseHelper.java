package com.example.symatiqueapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.symatiqueapplication.models.CartItem;
import com.example.symatiqueapplication.models.Product;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "cart_database";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate: Creating tables");
            TableUtils.createTableIfNotExists(connectionSource, CartItem.class);
            TableUtils.createTableIfNotExists(connectionSource, Product.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "onCreate: Error creating tables", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // Gérer les mises à jour de schéma ici si nécessaire
        if (oldVersion < 2) {
            try {
                // Add imageResourceId column
                getDao(CartItem.class).executeRaw("ALTER TABLE 'cart_items' ADD COLUMN 'imageResourceId' INTEGER;");
            } catch (SQLException e) {
                throw new RuntimeException("Failed to upgrade database", e);
            }
        }
    }
}

