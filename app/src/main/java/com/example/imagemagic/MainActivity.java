package com.example.imagemagic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.imagemagic.databinding.ActivityMainBinding;

import java.io.IOException;

import controller.MainActivityController;
import utils.AppUtils;
import utils.BitmapUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityController controller;
    Bitmap selectedBitmap;
    boolean exitApp=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        controller = new MainActivityController(this, this);

        View view = binding.getRoot();
        setContentView(view);
        binding.openGalleryButton.setOnClickListener(v -> controller.openGallery());
        binding.makeCollageButton.setOnClickListener(v -> AppUtils.showToastMessage(this, "Make Collage."));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == controller.PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri imageUri = data.getData();
                try {
                    selectedBitmap = BitmapUtils.uriToBitmap(imageUri, this);
                    controller.startEditActivity(selectedBitmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onBackPressed() {
        if (exitApp) {
            super.onBackPressed();
        }
        exitApp=true;
        AppUtils.showToastMessage(this, "Press again to exit.");
        new Handler().postDelayed(() -> exitApp=false, 2000);
    }
}