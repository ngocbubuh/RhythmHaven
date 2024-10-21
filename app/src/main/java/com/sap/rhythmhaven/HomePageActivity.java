package com.sap.rhythmhaven;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sap.rhythmhaven.API.ProductRepository;
import com.sap.rhythmhaven.API.ProductService;
import com.sap.rhythmhaven.Adapter.ProductAdapter;
import com.sap.rhythmhaven.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity {

    RecyclerView recyclerViewProduct;
    ProductAdapter productAdapter;
    ProductService productService;
    List<ProductEntity> productEntityList;
    Button buttonViewCart,buttonViewInvoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        recyclerViewProduct = findViewById(R.id.recyclerView);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));

        productEntityList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productEntityList);
        recyclerViewProduct.setAdapter(productAdapter);

        productService = ProductRepository.getProductService();
        loadProduct();

        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerViewProduct);

        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome to the RhythmHaven !");

        buttonViewCart = findViewById(R.id.buttonViewCart);
        buttonViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        buttonViewInvoices = findViewById(R.id.buttonViewInvoices);
        buttonViewInvoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, InvoiceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadProduct() {
        productEntityList.clear();
        Call<ProductEntity[]> call = productService.getProducts();
        call.enqueue(new Callback<ProductEntity[]>() {
            @Override
            public void onResponse(Call<ProductEntity[]> call, Response<ProductEntity[]> response) {
                if (response.body() != null) {
                    for (ProductEntity trainee : response.body()) {
                        productEntityList.add(trainee);
                    }
                    productAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ProductEntity[]> call, Throwable t) {
                // Handle failure
            }
        });
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            // Không cần xử lý vuốt nữa
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {
            // Không cần vẽ gì khi vuốt
        }
    };
}
