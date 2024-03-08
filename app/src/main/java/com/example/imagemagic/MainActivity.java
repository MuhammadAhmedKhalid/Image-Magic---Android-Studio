package com.example.imagemagic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imagemagic.databinding.ActivityMainBinding;

import java.io.IOException;

import controller.MainActivityController;
import utils.BitmapUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityController controller;
    Bitmap selectedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        controller = new MainActivityController();

        View view = binding.getRoot();
        setContentView(view);
        binding.openGalleryButton.setOnClickListener(v -> controller.openGallery(this));
        binding.makeCollageButton.setOnClickListener(v -> Toast.makeText(this, "Make Collage", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == controller.PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri imageUri = data.getData();
                try {
                    selectedBitmap = BitmapUtils.uriToBitmap(imageUri, this);
                    // pass the selected bitmap to edit image screen
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

}