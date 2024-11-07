package com.example.symatiqueapplication.activities;

import android.Manifest;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.symatiqueapplication.Adapters.CartAdapter;
import com.example.symatiqueapplication.Adapters.PopupAdapter;
import com.example.symatiqueapplication.R;
import com.example.symatiqueapplication.Repository.CartRepository;
import com.example.symatiqueapplication.models.CartItem;
import com.example.symatiqueapplication.utils.BluetoothUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private CartRepository cartRepository;
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private List<CartItem> shoppingCart = new ArrayList<>();
    private BluetoothSocket bluetoothSocket;
    private BluetoothUtils bluetoothUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        initRepos();
        initBinding();
        initData();
        initRecycler();
        initBluetooth();
        handleClicks();
    }

    private void handleClicks() {
        Button checkoutbutton = findViewById(R.id.button_checkout);
        checkoutbutton.setOnClickListener(this::onButtonShowPopupWindowClick);

        Button buttonEmptyCart = findViewById(R.id.button_emptycart);
        buttonEmptyCart.setOnClickListener(this::onEmptyCartButtonClick);
    }

    public void onEmptyCartButtonClick(View view) {
        adapter.deleteAllItems();
        updateEmptyCartUI();
    }

    private void updateEmptyCartUI() {
        Button buttonEmptyCart = findViewById(R.id.button_emptycart);
        Button buttonCheckout = findViewById(R.id.button_checkout);

        if (shoppingCart.isEmpty()) {
            buttonEmptyCart.setVisibility(View.GONE);
            buttonCheckout.setEnabled(false);
        } else {
            buttonEmptyCart.setVisibility(View.VISIBLE);
            buttonCheckout.setEnabled(true);
        }
    }

    private void initBluetooth() {
        bluetoothUtils = new BluetoothUtils();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, 1);
        }
    }

    private void initBinding() {
        recyclerView = findViewById(R.id.cartRecyclerView);
    }

    private void initRepos() {
        cartRepository = new CartRepository(this);
    }

    private void initData() {
        shoppingCart = cartRepository.getAllCartItems();
        updateEmptyCartUI();
    }

    public void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(shoppingCart, this);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.imageView_back).setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, ProductsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                Toast.makeText(this, "Bluetooth permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onButtonShowPopupWindowClick(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        RecyclerView recyclerView = popupView.findViewById(R.id.cartItemRecyclerView);
        PopupAdapter popupAdapter = new PopupAdapter(this, shoppingCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(popupAdapter);

        TextView textViewActualTotal = popupView.findViewById(R.id.textView_actualTotal);
        double actualTotal = popupAdapter.calculateTotal();

        DecimalFormat formatter = new DecimalFormat("#######.00");
        String formattedTotal = String.format("DT %s", formatter.format(actualTotal));
        textViewActualTotal.setText(formattedTotal);

        // Close Icon
        ImageView imageViewClose = popupView.findViewById(R.id.imageViewClose);
        imageViewClose.setOnClickListener(v -> {
            // Dismiss the popup window
            popupWindow.dismiss();
        });

        // Print button
        Button printButton = popupView.findViewById(R.id.button_imprimerFacture);
        printButton.setOnClickListener(v -> {
            try {
                // Call the method to find the Bluetooth device
                bluetoothUtils.findBT("printerName", CartActivity.this);
                // Open Bluetooth connection
                bluetoothUtils.openBT(this);
                // Send data to print
                sendDataToPrinter(popupAdapter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void sendDataToPrinter(PopupAdapter popupAdapter) throws IOException {
        String data = generatePrintData(popupAdapter);
        bluetoothUtils.sendData(data);
        // Close Bluetooth connection after printing
        bluetoothUtils.closeBT();
    }

    private String generatePrintData(PopupAdapter popupAdapter) {
        StringBuilder builder = new StringBuilder();
        // Append data from the adapter to be printed
        // Example:
        // for (CartItem item : popupAdapter.getData()) {
        //     builder.append(item.getProductName()).append("\n");
        // }
        return builder.toString();
    }
}
