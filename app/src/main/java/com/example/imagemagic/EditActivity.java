package com.example.imagemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.example.imagemagic.databinding.ActivityEditBinding;

import controller.EditActivityController;
import listener.AlertDialogListener;
import util.AppUtil;
import util.BitmapUtil;

public class EditActivity extends AppCompatActivity implements AlertDialogListener {

    private ActivityEditBinding binding;
    private EditActivityController controller;
    public static Bitmap editBitmap;
    boolean canGoBack=false;
    Bitmap updatedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditBinding.inflate(getLayoutInflater());
        controller = new EditActivityController(this, this, this);

        View view = binding.getRoot();
        setContentView(view);

        if (editBitmap!=null) {
            updatedBitmap=editBitmap;
            binding.editImage.setImageBitmap(editBitmap);
        }

        binding.back.setOnClickListener(v -> controller.backToHome());
        binding.save.setOnClickListener(v -> {
            BitmapUtil.saveImageToDevice(this, updatedBitmap);
            finish();
        });
        binding.crop.setOnClickListener(v -> AppUtil.showToastMessage(this, "Crop"));
        binding.rotate.setOnClickListener(v -> AppUtil.showToastMessage(this, "Rotate"));
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
            controller.backToHome();
        }
    }

    @Override
    public void onPositiveButtonClick() {
        canGoBack=true;
        onBackPressed();
    }

}