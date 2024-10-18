package com.sap.rhythmhaven;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private GridView gridView;
    private Button viewCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gridView = findViewById(R.id.gridView);
        viewCartButton = findViewById(R.id.viewCartButton);

        // Prepare grid items
        List<GridItem> items = new ArrayList<>();
        items.add(new GridItem(R.drawable.dan_bau, "Đàn bầu"));
        items.add(new GridItem(R.drawable.dan_nhi, "Đàn nhị"));
        items.add(new GridItem(R.drawable.dan_nguyet, "Đàn nguyệt"));
        items.add(new GridItem(R.drawable.dan_tranh, "Đàn tranh"));
        items.add(new GridItem(R.drawable.dan_ty_ba, "Đàn tỳ bà"));
        items.add(new GridItem(R.drawable.sao_truc, "Sáo trúc"));
        // Add more items as needed

        // Set up GridView adapter
        GridAdapter adapter = new GridAdapter(this, items);
        gridView.setAdapter(adapter);

        viewCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement view cart functionality
                Toast.makeText(HomeActivity.this, "View Cart clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
