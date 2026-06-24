package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecentItemsActivity extends AppCompatActivity {

    private RecyclerView rvRecentItems;
    private HouseAdapter adapter;
    private List<House> houseList;
    private DatabaseHelper dbHelper;
    private Button btnAddHouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_items);

        rvRecentItems = findViewById(R.id.rvRecentItems);
        btnAddHouse = findViewById(R.id.btnAddHouse);
        dbHelper = new DatabaseHelper(this);

        rvRecentItems.setLayoutManager(new GridLayoutManager(this, 2));

        // Pindah ke FormHouseActivity saat tombol ditambah diklik
        btnAddHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecentItemsActivity.this, FormHouseActivity.class);
                startActivity(intent);
            }
        });
    }

    // Menggunakan onResume agar list selalu update saat kembali dari FormActivity
    @Override
    protected void onResume() {
        super.onResume();
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        // Mengambil semua list rumah yang sudah tersimpan di SQLite
        houseList = dbHelper.getAllHouses();

        // Memasukkan ke adapter
        adapter = new HouseAdapter(this, houseList);
        rvRecentItems.setAdapter(adapter);
    }
}