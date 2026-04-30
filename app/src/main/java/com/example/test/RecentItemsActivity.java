package com.example.test;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.HouseAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecentItemsActivity extends AppCompatActivity {

    private RecyclerView rvRecentItems;
    private HouseAdapter adapter;
    private List<House> houseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_items);

        rvRecentItems = findViewById(R.id.rvRecentItems);

        // Buat data dummy
        houseList = new ArrayList<>();
        // Pastikan kamu punya gambar di folder res/drawable, atau sementara pakai ic_launcher
        houseList.add(new House("Modern House", "$ 500.000", "Deskripsi rumah modern yang sangat nyaman di pusat kota.", R.drawable.umah1));
        houseList.add(new House("Villa Bali", "$ 750.000", "Villa mewah dengan pemandangan sawah dan kolam renang.", R.drawable.umah2));
        houseList.add(new House("Family Home", "$ 300.000", "Rumah ramah lingkungan dengan 3 kamar tidur.", R.drawable.umah3));
        houseList.add(new House("Minimalist Apt", "$ 200.000", "Apartemen minimalis cocok untuk pekerja muda.", R.drawable.umah4));
        houseList.add(new House("Modern Villa", "$ 200.000", "Villa modern cocok untuk pekerja muda.", R.drawable.umah5));
        houseList.add(new House("Minimalist Apt", "$ 200.000", "Apartemen minimalis cocok untuk pekerja muda.", R.drawable.umah6));



        // Setup RecyclerView dengan Grid 2 Kolom
        rvRecentItems.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new HouseAdapter(this, houseList);
        rvRecentItems.setAdapter(adapter);
    }
}