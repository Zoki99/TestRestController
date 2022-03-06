package com.example.banca.model.TransactionsModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionsRoot {
    private String status;
    private ArrayList<Object> error;
    private TransactionsPayload payload;

    public TransactionsRoot(String status, ArrayList<Object> error,TransactionsPayload payload) {
        this.status = status;
        this.error = error;
        this.payload = payload;
    }

    public TransactionsRoot() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Object> getError() {
        return error;
    }

    public void setError(ArrayList<Object> error) {
        this.error = error;
    }

    public TransactionsPayload getPayload() {
        return payload;
    }

    public void setPayload(TransactionsPayload payload) {
        this.payload = payload;
    }
}
