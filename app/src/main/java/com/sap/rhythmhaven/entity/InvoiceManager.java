package com.sap.rhythmhaven.entity;

import com.sap.rhythmhaven.entity.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceManager {
    private static InvoiceManager instance;
    private List<Invoice> invoices;

    private InvoiceManager() {
        invoices = new ArrayList<>();
    }

    public static synchronized InvoiceManager getInstance() {
        if (instance == null) {
            instance = new InvoiceManager();
        }
        return instance;
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}