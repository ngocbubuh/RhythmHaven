package com.sap.rhythmhaven;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sap.rhythmhaven.Adapter.InvoiceAdapter;
import com.sap.rhythmhaven.entity.InvoiceManager;

public class InvoiceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private InvoiceAdapter invoiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        recyclerView = findViewById(R.id.recyclerViewInvoices);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        invoiceAdapter = new InvoiceAdapter(InvoiceManager.getInstance().getInvoices());
        recyclerView.setAdapter(invoiceAdapter);
    }
}