package com.example.test;

import java.io.Serializable;

// Implement Serializable agar objek ini bisa dikirim lewat Intent ke halaman Detail
public class House implements Serializable {
    private String title;
    private String price;
    private String description;
    private int imageResId; // Menyimpan ID gambar dari folder drawable

    public House(String title, String price, String description, int imageResId) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public String getPrice() { return price; }
    public String getDescription() { return description; }
    public int getImageResId() { return imageResId; }
}