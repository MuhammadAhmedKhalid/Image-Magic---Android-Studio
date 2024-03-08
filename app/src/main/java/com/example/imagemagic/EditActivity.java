package com.example.imagemagic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.example.imagemagic.databinding.ActivityEditBinding;

import controller.EditActivityController;
import listener.AlertDialogListener;

public class EditActivity extends AppCompatActivity implements AlertDialogListener {

    private ActivityEditBinding binding;
    private EditActivityController controller;
    public static Bitmap editBitmap;
    boolean canGoBack=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditBinding.inflate(getLayoutInflater());
        controller = new EditActivityController(this, this, this);

        View view = binding.getRoot();
        setContentView(view);

        if (editBitmap!=null) {
            binding.editImage.setImageBitmap(editBitmap);
        }

        binding.back.setOnClickListener(v -> controller.backToHome());

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