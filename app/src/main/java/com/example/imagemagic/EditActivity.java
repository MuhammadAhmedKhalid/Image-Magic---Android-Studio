package com.example.imagemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.imagemagic.databinding.ActivityEditBinding;

import listener.AlertDialogListener;
import util.AlertUtil;
import util.AppUtil;
import util.BitmapUtil;

public class EditActivity extends AppCompatActivity implements AlertDialogListener {

    private ActivityEditBinding binding;
    public static Bitmap editBitmap;
    public static Uri editBitmapUri;
    boolean canGoBack=false;
    public Bitmap updatedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        if (editBitmap!=null) {
            updatedBitmap=editBitmap;
            binding.editImage.setImageBitmap(editBitmap);
        }

        binding.back.setOnClickListener(v -> backToHome());
        binding.save.setOnClickListener(v -> {
            if (BitmapUtil.saveImageToDevice(this, updatedBitmap)) {
                AppUtil.showToastMessage(this, "Image saved to device.");
                finish();
            } else {
                AppUtil.showToastMessage(this, "Image failed to save.");
            }
        });
        binding.crop.setOnClickListener(v -> AppUtil.showToastMessage(this, "Crop"));
        binding.rotate.setOnClickListener(v -> rotateBitmap());
        binding.border.setOnClickListener(v -> AppUtil.showToastMessage(this, "Border"));
        binding.background.setOnClickListener(v -> AppUtil.showToastMessage(this, "Background"));
        binding.filter.setOnClickListener(v -> AppUtil.showToastMessage(this, "Filter"));
        binding.adjust.setOnClickListener(v -> AppUtil.showToastMessage(this, "Adjust"));
        binding.draw.setOnClickListener(v -> AppUtil.showToastMessage(this, "Draw"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }

    @Override
    public void onBackPressed() {
        if (canGoBack){
            super.onBackPressed();
        } else {
            backToHome();
        }
    }

    @Override
    public void onPositiveButtonClick() {
        canGoBack=true;
        onBackPressed();
    }

    public void backToHome() {
        AlertUtil.showAlertDialog(this, "Confirm Exit", "Are you sure you want to exit?", "Yes", "No", this);
    }

    public void rotateBitmap() {
        updatedBitmap = BitmapUtil.rotateBitmap(updatedBitmap);
        binding.editImage.setImageBitmap(updatedBitmap);
    }

}