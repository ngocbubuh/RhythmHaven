package com.sap.rhythmhaven;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sap.rhythmhaven.Adapter.CartAdapter;
import com.sap.rhythmhaven.entity.CartManager;
import com.sap.rhythmhaven.entity.Invoice;
import com.sap.rhythmhaven.entity.InvoiceManager;
import com.sap.rhythmhaven.entity.CartItem;

import java.util.Date;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private Button buttonCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartAdapter = new CartAdapter(CartManager.getInstance().getCartItems());
        recyclerView.setAdapter(cartAdapter);

        buttonCheckout = findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout();
            }
        });
    }

    private void checkout() {
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();
        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
            return;
        }

        double totalAmount = calculateTotalAmount(cartItems);
        String invoiceId = generateInvoiceId();
        Invoice invoice = new Invoice(invoiceId, new Date(), cartItems, totalAmount);

        InvoiceManager.getInstance().addInvoice(invoice);
        CartManager.getInstance().clearCart();

        Toast.makeText(this, "Thanh toán thành công. Mã hóa đơn: " + invoiceId, Toast.LENGTH_LONG).show();
        finish();
    }

    private double calculateTotalAmount(List<CartItem> cartItems) {
        double total = 0;
        for (CartItem item : cartItems) {
            total += Float.valueOf(item.getProduct().getPrice()) * Integer.valueOf(item.getQuantity());
        }
        return total;
    }

    private String generateInvoiceId() {
        return "INV-" + System.currentTimeMillis();
    }
}
