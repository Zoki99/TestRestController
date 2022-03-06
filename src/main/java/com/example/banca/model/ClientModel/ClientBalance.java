package com.example.banca.model.ClientModel;

import java.util.ArrayList;

public class ClientBalance {

    private String status;
    private ArrayList<Object> error;
    private ClientPayload payload;

    public ClientBalance(String status, ArrayList<Object> error, ClientPayload payload) {
        this.status = status;
        this.error = error;
        this.payload = payload;
    }


    public ClientBalance() {

    }

    public ClientBalance(ClientPayload payload) {
        this.payload = payload;
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

    public ClientPayload getPayload() {
        return payload;
    }

    public void setPayload(ClientPayload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ClientBalance{" +
                "status='" + status + '\'' +
                ", error=" + error +
                ", payload=" + payload.toString() +
                '}';
    }
}
