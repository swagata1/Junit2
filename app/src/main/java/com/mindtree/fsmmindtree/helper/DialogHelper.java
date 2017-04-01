package com.mindtree.fsmmindtree.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;
import com.mindtree.fsmmindtree.R;

/**
 * Created by sunilbagunji on 20/12/15.
 */
public class DialogHelper {
    /**
     * Method to show custom error dialog.
     *
     * @param context
     *            - Activity context.
     * @param title
     *            - Dialog title.
     * @param message
     *            - message.
     */
    public static void showErrorDialog(Context context, String title,
                                       String message) {
        if (context != null && context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title. no title.
                alertDialogBuilder.setTitle(title);

                // set dialog message
                alertDialogBuilder
                        .setMessage(message)
                        .setCancelable(false)
                        .setNegativeButton(
                                context.getString(R.string.ok_button),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.dismiss();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        }

    }
}
