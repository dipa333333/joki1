package com.example.test;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivDetailImage;
    private TextView tvDetailTitle, tvDetailPrice, tvDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivDetailImage = findViewById(R.id.ivDetailImage);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);

        // Menangkap objek House yang dikirim
        House house = (House) getIntent().getSerializableExtra("EXTRA_HOUSE");

        if (house != null) {
            ivDetailImage.setImageResource(house.getImageResId());
            tvDetailTitle.setText(house.getTitle());
            tvDetailPrice.setText(house.getPrice());
            tvDetailDescription.setText(house.getDescription());
        }
    }
}