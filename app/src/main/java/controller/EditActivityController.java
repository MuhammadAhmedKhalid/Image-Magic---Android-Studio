package controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import listener.AlertDialogListener;
import util.AlertUtil;
import util.AppUtil;

public class EditActivityController {

    Activity activity;
    Context context;
    AlertDialogListener listener;

    public EditActivityController(Activity activity, Context context, AlertDialogListener listener) {
        this.activity = activity;
        this.context = context;
        this.listener = listener;
    }

    public void backToHome() {
        AlertUtil.showAlertDialog(context, "Confirm Exit", "Are you sure you want to exit?", "Yes", "No", listener);
    }

}
