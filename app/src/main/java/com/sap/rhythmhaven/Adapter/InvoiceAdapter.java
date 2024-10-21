package com.sap.rhythmhaven.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sap.rhythmhaven.R;
import com.sap.rhythmhaven.entity.Invoice;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    private List<Invoice> invoices;
    private SimpleDateFormat dateFormat;

    public InvoiceAdapter(List<Invoice> invoices) {
        this.invoices = invoices;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invoice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Invoice invoice = invoices.get(position);
        holder.textViewInvoiceId.setText("Mã hóa đơn: " + invoice.getId());
        holder.textViewDate.setText("Ngày: " + dateFormat.format(invoice.getDate()));
        holder.textViewTotalAmount.setText(String.format(Locale.getDefault(), "Tổng tiền:%.2f", invoice.getTotalAmount()));
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewInvoiceId, textViewDate, textViewTotalAmount;

        ViewHolder(View itemView) {
            super(itemView);
            textViewInvoiceId = itemView.findViewById(R.id.textViewInvoiceId);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTotalAmount = itemView.findViewById(R.id.textViewTotalAmount);
        }
    }
}