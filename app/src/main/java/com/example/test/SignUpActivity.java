package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    // Deklarasi variabel
    private EditText etRegUsername, etRegFullname, etRegPassword, etRegEmail, etRegPhone;
    private Button btnSubmitSignUp;
    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Inisialisasi komponen dari XML
        etRegUsername = findViewById(R.id.etRegUsername);
        etRegFullname = findViewById(R.id.etRegFullname);
        etRegPassword = findViewById(R.id.etRegPassword);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPhone = findViewById(R.id.etRegPhone);

        btnSubmitSignUp = findViewById(R.id.btnSubmitSignUp);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        // Logic ketika tombol SIGN UP diklik
        btnSubmitSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil nilai teks dari masing-masing form
                String username = etRegUsername.getText().toString().trim();
                String fullname = etRegFullname.getText().toString().trim();
                String password = etRegPassword.getText().toString().trim();
                String email = etRegEmail.getText().toString().trim();
                String phone = etRegPhone.getText().toString().trim();

                // Validasi sederhana: pastikan tidak ada kolom yang kosong
                if (username.isEmpty() || fullname.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Nanti di sini kamu bisa tambahkan kode untuk simpan ke Database lokal (SQLite) atau API (Retrofit)

                    Toast.makeText(SignUpActivity.this, "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show();

                    // Kembali ke halaman Login setelah pura-puranya berhasil daftar
                    finish();
                }
            }
        });

        // Logic ketika teks "Log In" di paling bawah diklik
        tvBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perintah finish() akan menutup SignUpActivity ini
                // dan mengembalikan user ke activity sebelumnya (LoginActivity)
                finish();
            }
        });
    }
}