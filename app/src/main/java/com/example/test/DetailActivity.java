package com.example.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivDetailImage;
    private TextView tvDetailTitle, tvDetailPrice, tvDetailDescription;
    private Button btnEditDetail, btnDeleteDetail;
    private DatabaseHelper dbHelper;
    private House house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivDetailImage = findViewById(R.id.ivDetailImage);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        btnEditDetail = findViewById(R.id.btnEditDetail);
        btnDeleteDetail = findViewById(R.id.btnDeleteDetail);

        dbHelper = new DatabaseHelper(this);

        house = (House) getIntent().getSerializableExtra("EXTRA_HOUSE");

        if (house != null) {
            tvDetailTitle.setText(house.getTitle());
            tvDetailPrice.setText(house.getPrice());
            tvDetailDescription.setText(house.getDescription());

            // Tampilkan gambar dari URI jika ada
            if (house.getImageUri() != null && !house.getImageUri().isEmpty()) {
                ivDetailImage.setImageURI(Uri.parse(house.getImageUri()));
            } else {
                ivDetailImage.setImageResource(R.mipmap.ic_launcher);
            }
        }

        btnEditDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, FormHouseActivity.class);
                intent.putExtra("EXTRA_HOUSE", house);
                startActivity(intent);
                finish();
            }
        });

        btnDeleteDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog();
            }
        });
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data");
        builder.setMessage("Yakin mau menghapus properti ini?");

        builder.setPositiveButton("Ya, Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (house != null) {
                    dbHelper.deleteHouse(house);
                    Toast.makeText(DetailActivity.this, "Data berhasil dihapus!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}