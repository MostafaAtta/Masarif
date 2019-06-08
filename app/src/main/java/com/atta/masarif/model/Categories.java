package com.atta.masarif.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Categories {

    @SerializedName("categories")
    private ArrayList<String> categories;

    public Categories() {
    }

    public ArrayList<String> getCategories() {
        return categories;
    }
}
