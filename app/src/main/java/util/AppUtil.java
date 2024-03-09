package util;

import android.content.Context;
import android.widget.Toast;

public class AppUtil {

    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
