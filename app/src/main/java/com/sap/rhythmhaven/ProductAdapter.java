package com.sap.rhythmhaven;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sap.rhythmhaven.API.ProductService;
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
//        holder.nameTextView.setText(productEntity.getName());
        holder.nameTextView.setText(productEntity.getName());
        holder.descriptionTextView.setText(productEntity.getDescription());
        holder.quantityTextView.setText(productEntity.getQuantity());
        holder.typeTextView.setText(productEntity.getType());
        holder.priceTextView.setText(productEntity.getPrice());
    }

    @Override
    public int getItemCount() {
        return productEntityList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView,descriptionTextView,typeTextView,priceTextView,quantityTextView;

        public ProductViewHolder(@NonNull View itemView){
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nametxt);
            descriptionTextView = itemView.findViewById(R.id.descriptiontxt);
            typeTextView = itemView.findViewById(R.id.typetxt);
            priceTextView = itemView.findViewById(R.id.pricetxt);
            quantityTextView = itemView.findViewById(R.id.quantitytxt);
        }
    }
}

