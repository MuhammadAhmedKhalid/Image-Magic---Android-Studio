package com.example.imagemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

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
        binding.border.setOnClickListener(v -> handleBorder());
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
        if (updatedBitmap!=null) {
            updatedBitmap = BitmapUtil.rotateBitmap(updatedBitmap);
            binding.editImage.setImageBitmap(updatedBitmap);
        } else {
            AppUtil.showToastMessage(this, "No image to rotate.");
        }
    }

    public void handleBorder() {
        SeekBar borderSeekBar = binding.borderSeekBar;
        if (borderSeekBar.getVisibility() == View.VISIBLE) {
            borderSeekBar.setVisibility(View.GONE);
        } else {
            borderSeekBar.setVisibility(View.VISIBLE);
            changeBitmapBorder();
        }
    }

    public void changeBitmapBorder() {
        binding.borderSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Border", String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}