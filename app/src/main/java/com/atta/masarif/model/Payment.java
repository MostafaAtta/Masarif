package com.atta.masarif.model;

public class Payment {

    private int userId, id;

    private String name, details, date, amount, category;

    public Payment(int userId, int id, String name, String details, String date, String amount, String category) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.details = details;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public Payment(int userId, String name, String details, String date, String amount, String category) {
        this.userId = userId;
        this.name = name;
        this.details = details;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}
