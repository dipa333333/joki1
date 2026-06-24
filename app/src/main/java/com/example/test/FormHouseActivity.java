package com.example.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class FormHouseActivity extends AppCompatActivity {

    private EditText etFormTitle, etFormPrice, etFormDesc;
    private Button btnSaveHouse, btnPickImage;
    private ImageView ivFormPreview;

    private DatabaseHelper dbHelper;
    private House houseToEdit = null;

    // Variabel untuk nyimpen alamat (URI) gambar yang dipilih
    private String selectedImageUri = "";

    // Launcher buat buka Galeri
    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    if (imageUri != null) {
                        // Agar aplikasi tetep diizinkan akses gambar ini kedepannya
                        getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        selectedImageUri = imageUri.toString(); // Simpan jadi String
                        ivFormPreview.setImageURI(imageUri); // Tampilkan preview
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_house);

        etFormTitle = findViewById(R.id.etFormTitle);
        etFormPrice = findViewById(R.id.etFormPrice);
        etFormDesc = findViewById(R.id.etFormDesc);
        btnSaveHouse = findViewById(R.id.btnSaveHouse);
        btnPickImage = findViewById(R.id.btnPickImage);
        ivFormPreview = findViewById(R.id.ivFormPreview);

        dbHelper = new DatabaseHelper(this);

        // Jika mode EDIT
        if (getIntent().hasExtra("EXTRA_HOUSE")) {
            houseToEdit = (House) getIntent().getSerializableExtra("EXTRA_HOUSE");
            if (houseToEdit != null) {
                etFormTitle.setText(houseToEdit.getTitle());
                etFormPrice.setText(houseToEdit.getPrice());
                etFormDesc.setText(houseToEdit.getDescription());
                selectedImageUri = houseToEdit.getImageUri(); // Ambil URI lama

                // Tampilkan gambar lama jika ada
                if (!selectedImageUri.isEmpty()) {
                    ivFormPreview.setImageURI(Uri.parse(selectedImageUri));
                }
                btnSaveHouse.setText("UPDATE DATA");
            }
        }

        // Tombol Pilih Gambar
        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                pickImageLauncher.launch(intent);
            }
        });

        // Tombol Simpan
        btnSaveHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etFormTitle.getText().toString().trim();
                String price = etFormPrice.getText().toString().trim();
                String desc = etFormDesc.getText().toString().trim();

                if (title.isEmpty() || price.isEmpty() || desc.isEmpty()) {
                    Toast.makeText(FormHouseActivity.this, "Isi semua form!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (houseToEdit == null) {
                    // CREATE
                    House newHouse = new House(title, price, desc, selectedImageUri);
                    dbHelper.addHouse(newHouse);
                    Toast.makeText(FormHouseActivity.this, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
                } else {
                    // UPDATE
                    houseToEdit.setTitle(title);
                    houseToEdit.setPrice(price);
                    houseToEdit.setDescription(desc);
                    houseToEdit.setImageUri(selectedImageUri); // Update gambar juga
                    dbHelper.updateHouse(houseToEdit);
                    Toast.makeText(FormHouseActivity.this, "Berhasil Diupdate", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}