package controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.example.imagemagic.EditActivity;

public class MainActivityController {

    public final int PICK_IMAGE_REQUEST = 1;
    Activity activity;
    Context context;

    public MainActivityController(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public void openGallery() {
        Intent openGallery = new Intent(Intent.ACTION_PICK);
        openGallery.setType("image/*");
        activity.startActivityForResult(openGallery, PICK_IMAGE_REQUEST);
    }

    public void startEditActivity(Bitmap editBitmap) {
        Intent editActivity = new Intent(context, EditActivity.class);
        EditActivity.editBitmap = editBitmap;
        context.startActivity(editActivity);
    }

}
