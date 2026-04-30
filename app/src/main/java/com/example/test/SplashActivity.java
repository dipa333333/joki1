package com.example.test; // SESUAIKAN DENGAN PACKAGE KAMU

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    // Durasi Splash Screen (3000 ms = 3 detik)
    private static final int SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 1. Menghilangkan Action Bar untuk tampilan Full Screen
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Inisialisasi ImageView logo
        ImageView ivSplashLogo = findViewById(R.id.ivSplashLogo);

        // 2. Memuat dan menjalankan animasi dari xml
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        ivSplashLogo.startAnimation(animation);

        // 3. Logic untuk pindah ke LoginActivity setelah durasi berakhir
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Pindah dari SplashActivity ke LoginActivity
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);

                // Menutup SplashActivity agar saat tombol 'Back' ditekan, tidak kembali ke sini
                finish();
            }
        }, SPLASH_DURATION);
    }
}