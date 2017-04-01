package com.mindtree.fsmmindtree.common;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class BaseActivity extends AppCompatActivity {
    private ProgressDialog pd;

    public void showProgress(String title, String message) {
        pd = new ProgressDialog(this);
        pd.setTitle(title);
        pd.setMessage(message);
        pd.show();
    }

    public void hideProgress() {
        if (pd != null) {
            pd.dismiss();
        }
    }
}
