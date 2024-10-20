package com.sap.rhythmhaven;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.sap.rhythmhaven.API.ProductRepository;
import com.sap.rhythmhaven.API.ProductService;
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
        welcomeText.setText("Welcome to the Home Page!");

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
