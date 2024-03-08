package utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

public class BitmapUtils {

    public static Bitmap uriToBitmap(Uri imageUri, Context context) throws IOException {
        return MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
    }

}
