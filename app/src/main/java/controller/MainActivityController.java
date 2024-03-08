package controller;

import android.app.Activity;
import android.content.Intent;

public class MainActivityController {

    public final int PICK_IMAGE_REQUEST = 1;

    public void openGallery(Activity activity) {
        Intent openGallery = new Intent(Intent.ACTION_PICK);
        openGallery.setType("image/*");
        activity.startActivityForResult(openGallery, PICK_IMAGE_REQUEST);
    }

}
