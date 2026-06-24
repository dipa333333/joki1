package com.example.test;

import java.io.Serializable;

public class House implements Serializable {
    private int id;
    private String title;
    private String price;
    private String description;
    private String imageUri; // <-- BERUBAH: Sekarang kita simpan lokasi (URI) gambar sebagai String

    // Constructor untuk insert (tanpa ID)
    public House(String title, String price, String description, String imageUri) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUri = imageUri;
    }

    // Constructor untuk read dari database (pakai ID)
    public House(int id, String title, String price, String description, String imageUri) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUri = imageUri;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUri() { return imageUri; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }
}