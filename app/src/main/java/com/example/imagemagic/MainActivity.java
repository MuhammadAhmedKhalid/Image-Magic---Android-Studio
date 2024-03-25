package com.example.imagemagic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.imagemagic.databinding.ActivityMainBinding;

import java.io.IOException;

import util.AppUtil;
import util.BitmapUtil;
import util.PermissionUtil;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Bitmap selectedBitmap;
    Uri selectedBitmapUri;
    boolean exitApp = false;
    public final int PICK_IMAGE_REQUEST = 1;
    public static final int REQUEST_PERMISSION_CODE_1 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        // request permissions
        binding.openGalleryButton.setOnClickListener(v -> openGallery());
        binding.makeCollageButton.setOnClickListener(v -> AppUtil.showToastMessage(this, "Make Collage."));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri imageUri = data.getData();
                try {
                    selectedBitmapUri = imageUri;
                    selectedBitmap = BitmapUtil.uriToBitmap(imageUri, this);
                    startEditActivity(selectedBitmap, selectedBitmapUri);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION_CODE_1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                AppUtil.showToastMessage(this, "Permission denied.");
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
        AppUtil.showToastMessage(this, "Press again to exit.");
        new Handler().postDelayed(() -> exitApp=false, 2000);
    }

    public void openGallery() {
        if (PermissionUtil.checkPermissions(this)) {
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            openGallery.setType("image/*");
            this.startActivityForResult(openGallery, PICK_IMAGE_REQUEST);
        } else {
            PermissionUtil.requestPermissions(this, REQUEST_PERMISSION_CODE_1);
        }
    }

    public void startEditActivity(Bitmap editBitmap, Uri selectedBitmapUri) {
        Intent editActivity = new Intent(this, EditActivity.class);
        EditActivity.editBitmapUri = selectedBitmapUri;
        EditActivity.editBitmap = editBitmap;
        this.startActivity(editActivity);
    }
    
}