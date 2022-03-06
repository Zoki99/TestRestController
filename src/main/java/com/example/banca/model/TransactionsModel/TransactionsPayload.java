package com.example.banca.model.TransactionsModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionsPayload {
    private ArrayList<TranscationsList> list;

    public TransactionsPayload(ArrayList<TranscationsList> list) {
        this.list = list;
    }

    public TransactionsPayload() {
    }

    public ArrayList<TranscationsList> getList() {
        return list;
    }

    public void setList(ArrayList<TranscationsList> list) {
        this.list = list;
    }
}
