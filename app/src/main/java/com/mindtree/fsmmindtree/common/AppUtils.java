package com.mindtree.fsmmindtree.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sunilbagunji on 04/11/15.
 */
public class AppUtils {
    private static Typeface mFontNormal;
    private static Typeface mFontBold;

    public static void saveStringSetting(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(AppConstants.APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringSetting(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(AppConstants.APP_PREFERENCE, Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }

    public static void saveIntSetting(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(AppConstants.APP_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getIntSetting(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(AppConstants.APP_PREFERENCE, Context.MODE_PRIVATE);
        return settings.getInt(key, -1);
    }

    /**
     * Method to get custom fonts.
     *
     * @param context
     * @param font
     * @return
     */
    public static Typeface getCustomFont(Context context, int font) {
        String fontName = null;

        if (font == AppConstants.Fonts.FONT_NORMAL) {
            if (mFontNormal == null) {
                fontName = "fonts/Roboto-Regular.ttf";
                mFontNormal = Typeface.createFromAsset(context.getAssets(),
                        fontName);
            }
            return mFontNormal;
        } else if (font == AppConstants.Fonts.FONT_BOLD) {
            if (mFontBold == null) {
                fontName = "fonts/Roboto-Bold.ttf";
                mFontBold = Typeface.createFromAsset(context.getAssets(),
                        fontName);
            }
            return mFontBold;

        }
        return mFontNormal;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return target!=null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Method to show the progress dialog.
     *
     * @param mProgressDialog
     * @param mMessage
     */
    public static void showProgressDialog(ProgressDialog mProgressDialog, String mMessage) {
        mProgressDialog.setMessage(mMessage);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setIndeterminate(true);

        mProgressDialog.setProgressStyle(android.R.attr.progressBarStyleSmall);

        try {
            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to dismiss progress dialog.
     */
    public static void dismissProgress(ProgressDialog mProgressDialog) {
        if (mProgressDialog != null && mProgressDialog.isShowing()
                && mProgressDialog.getContext() != null) {
            try {
                mProgressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Method to check if a device is connected to internet or not
     *
     * @param context
     * @return true - if the device connected to internet; false - otherwise
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
