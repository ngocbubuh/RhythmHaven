package com.sap.rhythmhaven.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sap.rhythmhaven.R;
import com.sap.rhythmhaven.entity.CartManager;
import com.sap.rhythmhaven.entity.ProductEntity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<ProductEntity> productEntityList;

    public ProductAdapter ( Context context, List<ProductEntity> productEntityList){
        this.context = context;
        this.productEntityList = productEntityList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductEntity productEntity = productEntityList.get(position);
        holder.nameTextView.setText("Tên nhạc cụ :" + productEntity.getName());
        holder.descriptionTextView.setText("Chú thích: "+productEntity.getDescription());
        holder.quantityTextView.setText("Loai nha cụ : "+productEntity.getQuantity());
        holder.typeTextView.setText(productEntity.getType());
        holder.priceTextView.setText("Giá tiền : "+productEntity.getPrice() + " VND");

        holder.addtocartButton.setOnClickListener(v -> {
            CartManager.getInstance().addToCart(productEntity);
            Toast.makeText(v.getContext(), "Added to cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productEntityList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,descriptionTextView,typeTextView,priceTextView,quantityTextView;
        Button addtocartButton;

        public ProductViewHolder(@NonNull View itemView){
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nametxt);
            descriptionTextView = itemView.findViewById(R.id.descriptiontxt);
            typeTextView = itemView.findViewById(R.id.typetxt);
            priceTextView = itemView.findViewById(R.id.pricetxt);
            quantityTextView = itemView.findViewById(R.id.quantitytxt);
            addtocartButton = itemView.findViewById(R.id.addtocart);
        }
    }
}

