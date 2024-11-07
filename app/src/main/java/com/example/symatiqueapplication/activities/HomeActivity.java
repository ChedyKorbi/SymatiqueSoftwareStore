package com.example.symatiqueapplication.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.symatiqueapplication.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);


        Button buttonCheckProducts = findViewById(R.id.buttonCheckProducts);

        // Simulate a loading delay using Handler
        buttonCheckProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the main activity when the button is clicked
                Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
                startActivity(intent);
                finish(); // Finish the loading activity
            }
        });
    }}