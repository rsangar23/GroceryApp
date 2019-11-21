package com.example.groceryapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Grocery {

    @SerializedName("Id")
    @Expose
    private String Id;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grocery(String id, String name, String quantity) {
        Id = id;
        this.quantity = quantity;
        this.name = name;
    }
}
