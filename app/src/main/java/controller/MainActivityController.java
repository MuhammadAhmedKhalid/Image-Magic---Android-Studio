package controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.example.imagemagic.EditActivity;

import util.PermissionUtil;

public class MainActivityController {

    public final int PICK_IMAGE_REQUEST = 1;
    public static final int REQUEST_PERMISSION_CODE_1 = 2;
    Activity activity;
    Context context;

    public MainActivityController(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
    }

    public void openGallery() {
        if (PermissionUtil.checkPermissions(context)) {
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            openGallery.setType("image/*");
            activity.startActivityForResult(openGallery, PICK_IMAGE_REQUEST);
        } else {
            PermissionUtil.requestPermissions(activity, REQUEST_PERMISSION_CODE_1);
        }
    }

    public void startEditActivity(Bitmap editBitmap) {
        Intent editActivity = new Intent(context, EditActivity.class);
        EditActivity.editBitmap = editBitmap;
        context.startActivity(editActivity);
    }

}
