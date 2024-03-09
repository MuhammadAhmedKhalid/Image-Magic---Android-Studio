package util;

import android.app.AlertDialog;
import android.content.Context;

import listener.AlertDialogListener;

public class AlertUtil {

    public static void showAlertDialog(Context context, String title, String message,
                                       String positiveButtonText, String negativeButtonText, AlertDialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message)
                .setNegativeButton(positiveButtonText, (dialog, which) -> {
                    listener.onPositiveButtonClick();
                    dialog.dismiss();
                })
                .setPositiveButton(negativeButtonText, ((dialog, which) -> dialog.dismiss()));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
