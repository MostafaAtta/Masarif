package com.atta.masarif.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Payments {

    @SerializedName("payments")
    private ArrayList<Payment> Payments;

    public Payments() {
    }

    public ArrayList<Payment> getPayments() {
        return Payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.Payments = payments;
    }
}
