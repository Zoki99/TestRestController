package com.example.banca.model.TransactionsModel;

public class TransactionsType {
        public String enumeration;
        public String value;

    public TransactionsType(String enumeration, String value) {
        this.enumeration = enumeration;
        this.value = value;
    }

    public TransactionsType() {
    }

    public String getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(String enumeration) {
        this.enumeration = enumeration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TransactionsType{" +
                "enumeration='" + enumeration + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
