package util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BitmapUtil {

    public static Bitmap uriToBitmap(Uri imageUri, Context context) throws IOException {
        return MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
    }

    public static void saveImageToDevice(Context context, Bitmap bitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm ss", Locale.getDefault()).format(new Date());
        String fileName = "ImageMagic_" + timeStamp + ".jpg";

        ContentResolver contentResolver = context.getContentResolver();

        ContentValues imageDetails = new ContentValues();
        imageDetails.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        imageDetails.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        imageDetails.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/ImageMagic");

        try {
            Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageDetails);
            if (uri != null) {
                OutputStream imageOutputStream = contentResolver.openOutputStream(uri);
                if (imageOutputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOutputStream);
                    AppUtil.showToastMessage(context, "Image saved to device.");
                    imageOutputStream.close();
                }
            }
        } catch (Exception e) {
            AppUtil.showToastMessage(context, "Image failed to save.");
        }
    }


}
